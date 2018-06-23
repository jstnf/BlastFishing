package com.jstnf.blastfishing.commands;

import org.bukkit.command.CommandSender;

import com.jstnf.blastfishing.MessageManager;
import com.jstnf.blastfishing.lang.Lang;

public class Reload implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		if (sender.hasPermission(permission()))
		{
			// ADD THINGS HERE!
			MessageManager.sendWithPrefix(sender, Lang.ON_RELOAD);
			MessageManager.sendWithPrefix(sender, Lang.RELOAD_SUCCESS);
			MessageManager.sendWithPrefix(sender, Lang.RELOAD_FAILURE);
		}
		else
		{
			MessageManager.sendWithPrefix(sender, Lang.RELOAD_NO_PERMISSION);
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "blastfishing.reload";
	}
}
