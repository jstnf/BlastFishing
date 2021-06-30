package com.jstnf.blastfishing.loottable;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class LootTableManager
{
	public int totalWeight;
	public ArrayList<LootTableItem> table;

	public LootTableManager()
	{
		table = new ArrayList<LootTableItem>();
		totalWeight = 0;
	}

	public boolean add(ItemStack item, int weight)
	{
		try
		{
			LootTableItem toAdd = new LootTableItem(item, weight);
			table.add(toAdd);
			totalWeight += weight;
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean remove(int index)
	{
		try
		{
			table.remove(index);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public ItemStack getAtWeight(int weightIndex)
	{
		if (isEmpty()) // Check if loot table is empty
		{
			return null;
		}
		if (weightIndex < 1 || weightIndex > totalWeight) // Check if invalid index
		{
			throw new ArrayIndexOutOfBoundsException("weightIndex: " + weightIndex + ", totalWeight: " + totalWeight);
		}
		int selector = 0, parser = 0;
		while (parser < table.size() && selector < weightIndex)
		{
			selector += table.get(parser).getWeight();
			parser++;
		}
		return table.get(parser - 1).getItemStack();
	}

	public ItemStack getAtIndex(int index)
	{
		if (isEmpty())
		{
			return null; // No items
		}
		else
		{
			return table.get(index).getItemStack();
		}

	}

	public boolean isEmpty()
	{
		return table.isEmpty() && totalWeight == 0;
	}

	public int size()
	{
		return table.size();
	}
}
