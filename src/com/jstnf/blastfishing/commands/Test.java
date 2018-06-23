package com.jstnf.blastfishing.commands;

import org.bukkit.command.CommandSender;

import com.jstnf.blastfishing.lang.Lang;

public class Test implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		for (Lang lang : Lang.values())
		{
			sender.sendMessage(lang.getDefault());
			sender.sendMessage(lang.toString());
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "";
	}
}
