package com.jstnf.blastfishing;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BlastFishingCommand implements CommandExecutor
{
	private BlastFishingPlugin plugin;
	
	public BlastFishingCommand(BlastFishingPlugin bfp)
	{
		this.plugin = bfp;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
