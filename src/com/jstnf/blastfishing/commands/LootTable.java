package com.jstnf.blastfishing.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.jstnf.blastfishing.BlastFishingPlugin;
import com.jstnf.blastfishing.MessageManager;
import com.jstnf.blastfishing.lang.HardCodedLang;
import com.jstnf.blastfishing.lang.Lang;
import com.jstnf.blastfishing.loottable.LootTableItem;

public class LootTable implements SubCommand
{
	private BlastFishingPlugin plugin;

	public LootTable(BlastFishingPlugin bfp)
	{
		this.plugin = bfp;
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		if (args == null || args.length < 1)
		{
			MessageManager.sendWithPrefix(sender, Lang.INVALID_LOOTTABLE_SUBCOMMAND);
			return true;
		}
		switch (args[0].toLowerCase())
		{
			case "items":
				if (!(sender instanceof Player)) // Check if player
				{
					MessageManager.sendWithPrefix(sender, Lang.MUST_BE_PLAYER);
					return true;
				}
				if (!sender.hasPermission("blastfishing.loottable.items")) // Check permission
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ITEMS_NO_PERMISSION);
					return true;
				}
				/**
				 *  ***************
				 *  *  ADD THIS!  *
				 *  ***************
				 */
				MessageManager.sendWithPrefix(sender, Lang.TO_IMPLEMENT);
				break;
			case "add":
				if (!(sender instanceof Player)) // Check if player
				{
					MessageManager.sendWithPrefix(sender, Lang.MUST_BE_PLAYER);
					return true;
				}
				if (!sender.hasPermission("blastfishing.loottable.add")) // Check permission
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_NO_PERMISSION);
					return true;
				}
				if (args.length > 2) // Check if too many args
				{
					MessageManager.sendWithPrefix(sender, Lang.TOO_MANY_ARGUMENTS);
					MessageManager.sendWithPrefix(sender, Lang.INVALID_LOOTTABLE_ADD_SUBCOMMAND);
					return true;
				}
				if (args.length < 2) // Check if too little args
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_NO_WEIGHT);
					MessageManager.sendWithPrefix(sender, Lang.INVALID_LOOTTABLE_ADD_SUBCOMMAND);
					return true;
				}
				if (!isInt(args[1])) // Check if the weight given is an int
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_INVALID_WEIGHT);
					MessageManager.sendWithPrefix(sender, Lang.INVALID_LOOTTABLE_ADD_SUBCOMMAND);
					return true;
				}
				
				PlayerInventory pInv = ((Player) sender).getInventory();
				int weight = Integer.parseInt(args[1]);
				ItemStack heldItem = pInv.getItemInMainHand();
				
				if (weight < 1) // Check if the weight given is less than 1
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_INVALID_WEIGHT);
					return true;
				}
				if (heldItem == null || heldItem.getType().equals(Material.AIR)) // Check if the 
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_NO_ITEM);
					return true;
				}

				boolean success = plugin.getLootMan().add(new ItemStack(heldItem), weight);
				if (success)
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_SUCCESS);
				}
				else
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_ADD_FAILURE);
				}
				break;
			case "remove":
				/**
				 *  ***************
				 *  *  ADD THIS!  *
				 *  ***************
				 */
				MessageManager.sendWithPrefix(sender, Lang.TO_IMPLEMENT);
				break;
			case "get":
				/**
				 *  ***************
				 *  *  ADD THIS!  *
				 *  ***************
				 */
				MessageManager.sendWithPrefix(sender, Lang.TO_IMPLEMENT);
				break;
			case "list":
				ArrayList<LootTableItem> loot = plugin.getLootMan().table;
				for (int i = 0; i < loot.size(); i++)
				{
					sender.sendMessage(MessageManager.lootListEntry(loot.get(i), i + 1));
				}
				break;
			case "help":
				if (!sender.hasPermission("blastfishing.loottable.help"))
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_HELP_NO_PERMISSION);
					return true;
				}
				sender.sendMessage(HardCodedLang.LOOTTABLE_HELP.toString());
				break;
			case "random":
				if (!(sender instanceof Player)) // Check if player
				{
					MessageManager.sendWithPrefix(sender, Lang.MUST_BE_PLAYER);
					return true;
				}
				if (!sender.hasPermission("blastfishing.loottable.random")) // Check permission
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_RANDOM_NO_PERMISSION);
					return true;
				}
				if (plugin.getLootMan().isEmpty()) // Check if loot table is empty
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_GIVE_FAILURE_EMPTY);
					return true;
				}
				if (((Player)sender).getInventory().firstEmpty() == -1) // Check if inventory is full
				{
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_GIVE_FAILURE_INVFULL);
					return true;
				}
				try
				{
					int random = new Random().nextInt(plugin.getLootMan().totalWeight) + 1;
					sender.sendMessage(random + " / " + plugin.getLootMan().totalWeight);
					sender.sendMessage(plugin.getLootMan().getAtWeight(random).getAmount() + " in the stack.");
					PlayerInventory inv = ((Player)sender).getInventory();
					inv.setItem(inv.firstEmpty(), plugin.getLootMan().getAtWeight(random));
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_GIVE_RANDOM_SUCCESS);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					MessageManager.sendWithPrefix(sender, Lang.LOOTTABLE_GIVE_FAILURE);
				}
				break;
			default:
				MessageManager.sendWithPrefix(sender, Lang.INVALID_LOOTTABLE_SUBCOMMAND);
				break;
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "";
	}

	private boolean isInt(String str)
	{
		try
		{
			Integer.parseInt(str);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}