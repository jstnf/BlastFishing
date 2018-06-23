package com.jstnf.blastfishing.commands;

import org.bukkit.command.CommandSender;

import com.jstnf.blastfishing.BlastFishingPlugin;
import com.jstnf.blastfishing.MessageManager;
import com.jstnf.blastfishing.lang.HardCodedLang;
import com.jstnf.blastfishing.lang.Lang;

public class Toggle implements SubCommand
{
	private BlastFishingPlugin plugin;

	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		
		if (args == null || args.length < 1)
		{
			MessageManager.sendWithPrefix(sender, Lang.INVALID_TOGGLE_SUBCOMMAND);
		}
		else
		{
			switch (args[0].toLowerCase())
			{
				case "blastfishing":
					if (sender.hasPermission("blastfishing.toggle.blastfishing"))
					{
						if (plugin.toggleBF())
						{
							MessageManager.sendWithPrefix(sender, Lang.BFTOGGLE_ON);
						}
						else
						{
							MessageManager.sendWithPrefix(sender, Lang.BFTOGGLE_OFF);
						}
					}
					else
					{
						MessageManager.sendWithPrefix(sender, Lang.BFTOGGLE_NO_PERMISSION);
					}
					break;
				case "playercheck":
					if (sender.hasPermission("blastfishing.toggle.playercheck"))
					{
						if (plugin.togglePlayerCheck())
						{
							MessageManager.sendWithPrefix(sender, Lang.PLAYERCHECK_ON);
						}
						else
						{
							MessageManager.sendWithPrefix(sender, Lang.PLAYERCHECK_OFF);
						}
					}
					else
					{
						MessageManager.sendWithPrefix(sender, Lang.PLAYERCHECK_NO_PERMISSION);
					}
					break;
				case "permissioncheck":
					if (sender.hasPermission("blastfishing.toggle.permissioncheck"))
					{
						if (plugin.togglePermissionCheck())
						{
							MessageManager.sendWithPrefix(sender, Lang.PERMISSIONCHECK_ON);
						}
						else
						{
							MessageManager.sendWithPrefix(sender, Lang.PERMISSIONCHECK_OFF);
						}
					}
					else
					{
						MessageManager.sendWithPrefix(sender, Lang.PERMISSIONCHECK_NO_PERMISSION);
					}
					break;
				case "help":
					if (sender.hasPermission("blastfishing.toggle.help"))
					{
						sender.sendMessage(HardCodedLang.TOGGLE_HELP.toString());
					}
					else
					{
						MessageManager.sendWithPrefix(sender, Lang.TOGGLE_HELP_NO_PERMISSION);
					}
					break;
				default:
					MessageManager.sendWithPrefix(sender, Lang.INVALID_TOGGLE_SUBCOMMAND);
					break;
			}
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "";
	}

	public void setup(BlastFishingPlugin plugin)
	{
		this.plugin = plugin;
	}
}