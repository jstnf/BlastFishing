package com.jstnf.blastfishing.legacy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class CommandBlastFishing implements CommandExecutor {

	private String rootMessage;
	private BlastFishing bf;

	public CommandBlastFishing(BlastFishing bf) {
		this.bf = bf;

		// This is messy and hard-coded! Integrate into lang file somehow!
		rootMessage = Lang.PREFIX.get() + ChatColor.WHITE + "BlastFishing v" + this.bf.pdf.getVersion()
				+ " by pokeball92870 / jstnf" + "\n" + this.bf.pdf.getDescription() + "\n" + ChatColor.GOLD
				+ "/blastfishing toggle: " + ChatColor.WHITE + "Toggles BlastFishing for the entire server.\n"
				+ ChatColor.GOLD + "/blastfishing playercheck:" + ChatColor.WHITE
				+ " Toggles whether or not blast fishing will only work if the TNT was set off by a player.\n"
				+ ChatColor.GOLD + "/blastfishing permissioncheck:" + ChatColor.WHITE
				+ " Toggles the permission check for BlastFishing.\n" + ChatColor.GOLD + "/blastfishing reload:"
				+ ChatColor.WHITE + " Reload the BlastFishing configuration and loot table.";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length > 0) {

			String subcommand = args[0];

			// blastfishing toggle
			if (subcommand.equalsIgnoreCase("toggle")) {

				if (sender.hasPermission("blastfishing.toggle")) {
					boolean status = bf.toggle();
					if (status) {
						sender.sendMessage(Lang.PREFIX.get() + Lang.TOGGLE_ON.get());
					} else {
						sender.sendMessage(Lang.PREFIX.get() + Lang.TOGGLE_OFF.get());
					}
					if (bf.autoSaveConfig) {
						bf.pushToConfig();
					}
				} else {
					sender.sendMessage(Lang.PREFIX.get() + Lang.TOGGLE_NO_PERMISSION.get());
				}
			}

			// blastfishing playercheck
			else if (subcommand.equalsIgnoreCase("playercheck")) {
				if (sender.hasPermission("blastfishing.toggle.playercheck")) {
					boolean status = bf.togglePlayerCheck();
					if (status) {
						sender.sendMessage(Lang.PREFIX.get() + Lang.PLAYERCHECK_ON.get());
					} else {
						sender.sendMessage(Lang.PREFIX.get() + Lang.PLAYERCHECK_OFF.get());
					}
					if (bf.autoSaveConfig) {
						bf.pushToConfig();
					}
				} else {
					sender.sendMessage(Lang.PREFIX.get() + Lang.PLAYERCHECK_NO_PERMISSION.get());
				}
			}

			// blastfishing permissioncheck
			else if (subcommand.equalsIgnoreCase("permissioncheck")) {
				if (sender.hasPermission("blastfishing.toggle.permissioncheck")) {
					boolean status = bf.togglePermissionCheck();
					if (status) {
						sender.sendMessage(Lang.PREFIX.get() + Lang.PERMISSIONCHECK_ON.get());
					} else {
						sender.sendMessage(Lang.PREFIX.get() + Lang.PERMISSIONCHECK_OFF.get());
					}
					if (bf.autoSaveConfig) {
						bf.pushToConfig();
					}
				} else {
					sender.sendMessage(Lang.PREFIX.get() + Lang.PERMISSIONCHECK_NO_PERMISSION.get());
				}
			}

			// blastfishing reload
			else if (subcommand.equals("reload")) {
				if (sender.hasPermission("blastfishing.reload")) {
					bf.reloadConfig();
					bf.pullFromConfig();
					sender.sendMessage(Lang.PREFIX.get() + Lang.RELOAD_SUCCESS.get());
				} else {
					sender.sendMessage(Lang.PREFIX.get() + Lang.RELOAD_NO_PERMISSION.get());
				}
			}

			// invalid subcommand
			else {
				sender.sendMessage(Lang.PREFIX.get() + Lang.INVALID_SUBCOMMAND.get());
			}

		} else {
			sender.sendMessage(rootMessage);
		}

		return true;

	}

}
