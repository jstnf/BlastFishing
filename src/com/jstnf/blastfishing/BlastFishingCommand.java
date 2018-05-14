package com.jstnf.blastfishing;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class BlastFishingCommand implements CommandExecutor
{
	private BlastFishingPlugin plugin;

	public BlastFishingCommand(BlastFishingPlugin bfp)
	{
		this.plugin = bfp;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		int numArgs = args.length;

		if (numArgs >= 1)
		{
			switch (args[0].toLowerCase())
			{
				case "help":
					if (sender.hasPermission("blastfishing.help"))
					{
						if (numArgs > 1)
						{
							switch (args[1])
							{
								case "1":
									sender.sendMessage(HardCodedLang.COMMAND_HELP1.toString());
									break;
								case "2":
									sender.sendMessage(HardCodedLang.COMMAND_HELP2.toString());
									break;
								case "3":
									sender.sendMessage(HardCodedLang.COMMAND_HELP3.toString());
									break;
								default:
									this.sendWithPrefix(sender, Lang.INVALID_HELP_SUBCOMMAND);
									break;
							}
						}
						else
						{
							sender.sendMessage(HardCodedLang.COMMAND_HELP1.toString());
						}
					}
					else
					{
						this.sendWithPrefix(sender, Lang.HELP_NO_PERMISSION);
					}
					break;
				case "options":
					sender.sendMessage("To-do: Implement GUI!");
					break;
				case "toggle":
					if (numArgs > 1)
					{
						switch (args[1].toLowerCase())
						{
							case "blastfishing":
								if (sender.hasPermission("blastfishing.toggle.blastfishing"))
								{
									if (plugin.toggleBF())
									{
										this.sendWithPrefix(sender, Lang.BFTOGGLE_ON);
									}
									else
									{
										this.sendWithPrefix(sender, Lang.BFTOGGLE_OFF);
									}
								}
								else
								{
									this.sendWithPrefix(sender, Lang.BFTOGGLE_NO_PERMISSION);
								}
								break;
							case "playercheck":
								if (sender.hasPermission("blastfishing.toggle.playercheck"))
								{
									if (plugin.togglePlayerCheck())
									{
										this.sendWithPrefix(sender, Lang.PLAYERCHECK_ON);
									}
									else
									{
										this.sendWithPrefix(sender, Lang.PLAYERCHECK_OFF);
									}
								}
								else
								{
									this.sendWithPrefix(sender, Lang.PLAYERCHECK_NO_PERMISSION);
								}
								break;
							case "permissioncheck":
								if (sender.hasPermission("blastfishing.toggle.permissioncheck"))
								{
									if (plugin.togglePermissionCheck())
									{
										this.sendWithPrefix(sender, Lang.PERMISSIONCHECK_ON);
									}
									else
									{
										this.sendWithPrefix(sender, Lang.PERMISSIONCHECK_OFF);
									}
								}
								else
								{
									this.sendWithPrefix(sender, Lang.PERMISSIONCHECK_NO_PERMISSION);
								}
								break;
							case "help":
								if (sender.hasPermission("blastfishing.toggle.help"))
								{
									sender.sendMessage(HardCodedLang.TOGGLE_HELP.toString());
								}
								else
								{
									this.sendWithPrefix(sender, Lang.TOGGLE_HELP_NO_PERMISSION);
								}
								break;
							default:
								this.sendWithPrefix(sender, Lang.INVALID_TOGGLE_SUBCOMMAND);
								break;
						}
					}
					else
					{
						this.sendWithPrefix(sender, Lang.INVALID_TOGGLE_SUBCOMMAND);
					}
					break;
				case "loottable":
					sender.sendMessage("<<Usually you'd be able to edit the loot table...>>");
					break;
				case "testmessages":
					for (Lang lang : Lang.values())
					{
						sender.sendMessage(lang.getDefault());
						sender.sendMessage(lang.toString());
					}
					break;
				case "reload":
					if (sender.hasPermission("blastfishing.reload"))
					{
						this.sendWithPrefix(sender, Lang.ON_RELOAD);
						this.sendWithPrefix(sender, Lang.RELOAD_SUCCESS);
						this.sendWithPrefix(sender, Lang.RELOAD_FAILURE);
					}
					else
					{
						this.sendWithPrefix(sender, Lang.RELOAD_NO_PERMISSION);
					}
					break;
				default:
					this.sendWithPrefix(sender, Lang.INVALID_SUBCOMMAND);
					break;
			}
		}
		else
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"\n&fBlastFishing &6v" + plugin.pdf.getVersion() + " &fby &6pokeball92870 &f/ &6jstnf"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HardCodedLang.CREDITS.toString()));
		}
		return true;
	}

	private void sendWithPrefix(CommandSender sender, Lang value)
	{
		sender.sendMessage(Lang.PREFIX.toString() + value.toString());
	}
}
