package com.jstnf.blastfishing;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import com.jstnf.blastfishing.lang.Lang;
import com.jstnf.blastfishing.loottable.LootTableItem;

public class MessageManager
{
	public static void sendWithPrefix(CommandSender sender, Lang value)
	{
		sender.sendMessage(Lang.PREFIX.toString() + value.toString());
	}
	
	public static void sendWithPrefixIndexMod(CommandSender sender, Lang value, int index)
	{
		sender.sendMessage(Lang.PREFIX.toString() + value.toString().replaceAll("%i%", index + ""));
	}
	
	public static String lootListEntry(LootTableItem item, int index)
	{
		int quantity = item.getItemStack().getAmount();
		Material mat = item.getItemStack().getType();
		int weight = item.getWeight();
		
		String prior = "&a(" + index + ") &f" + mat
				+ "\n&aQuantity: &f" + quantity
				+ "\n&aWeight: &f" + weight
				+ "\n";
		
		return ChatColor.translateAlternateColorCodes('&', prior);
	}
}
