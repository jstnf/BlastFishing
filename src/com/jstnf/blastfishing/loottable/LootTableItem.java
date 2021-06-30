package com.jstnf.blastfishing.loottable;

import org.bukkit.inventory.ItemStack;

public class LootTableItem
{
	private ItemStack item;
	private int weight;
	
	public LootTableItem(ItemStack item, int weight)
	{
		this.item = item;
		this.weight = weight;
	}
	
	public ItemStack getItemStack()
	{
		return item;
	}
	
	public int getWeight()
	{
		return weight;
	}
}
