package com.jstnf.blastfishing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.jstnf.blastfishing.commands.LootTable;
import com.jstnf.blastfishing.commands.Menu;
import com.jstnf.blastfishing.commands.Reload;
import com.jstnf.blastfishing.commands.SubCommand;
import com.jstnf.blastfishing.commands.Test;
import com.jstnf.blastfishing.commands.Toggle;
import com.jstnf.blastfishing.lang.HardCodedLang;
import com.jstnf.blastfishing.lang.Lang;

import net.md_5.bungee.api.ChatColor;

public class BlastFishingCommand implements CommandExecutor
{
	private BlastFishingPlugin plugin;
	private HashMap<String, SubCommand> commands;

	public BlastFishingCommand(BlastFishingPlugin bfp)
	{
		this.plugin = bfp;
		commands = new HashMap<String,SubCommand>();
		loadCommands();
	}

	private void loadCommands()
	{
		Toggle toggleSub = new Toggle();
		toggleSub.setup(this.plugin);
		commands.put("toggle", toggleSub);
		commands.put("options", new Menu());
		commands.put("loottable", new LootTable());
		commands.put("test", new Test());
		commands.put("reload", new Reload());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args == null || args.length < 1) // no subcommands
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"\n&fBlastFishing &6v" + plugin.pdf.getVersion() + " &fby &6pokeball92870 &f/ &6jstnf"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HardCodedLang.CREDITS.toString()));
			return true;
		}
		
		int numArgs = args.length;
		
		if (numArgs > 0)
		{
			if (args[0].equalsIgnoreCase("help")) // bf help ...
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
							MessageManager.sendWithPrefix(sender, Lang.INVALID_HELP_SUBCOMMAND);
							break;
					}
				}
				else
				{
					sender.sendMessage(HardCodedLang.COMMAND_HELP1.toString());
				}
				return true;
			}
			
			String sub = args[0];
			Vector<String> l = new Vector<String>();
			l.addAll(Arrays.asList(args));
			l.remove(0);
			args = (String[]) l.toArray(new String[0]);
			
			if (!commands.containsKey(sub)) // invalid subcommand
			{
				MessageManager.sendWithPrefix(sender, Lang.INVALID_SUBCOMMAND);
				return true;
			}
			
			try
			{
				commands.get(sub).onCommand(sender, args);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				sender.sendMessage("There was an error executing this command.");
				sender.sendMessage("Report this!");
			}
		}
		return true;
	}
}
