package com.jstnf.blastfishing.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jstnf.blastfishing.MessageManager;
import com.jstnf.blastfishing.lang.Lang;

public class Menu implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if (sender.hasPermission(permission()))
			{
				// ADD THINGS HERE!
				sender.sendMessage("To implement!");
			}
			else
			{
				MessageManager.sendWithPrefix(sender, Lang.MENU_NO_PERMISSION);
			}
		}
		else
		{
			MessageManager.sendWithPrefix(sender, Lang.MUST_BE_PLAYER);
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "blastfishing.menu";
	}
}