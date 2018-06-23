package com.jstnf.blastfishing.commands;

import org.bukkit.command.CommandSender;

public class LootTable implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		// ADD THINGS HERE!
		sender.sendMessage("<<Usually you'd be able to edit the loot table...>>");
		return true;
	}

	@Override
	public String permission()
	{
		return "";
	}
}