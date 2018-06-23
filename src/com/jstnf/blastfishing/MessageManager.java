package com.jstnf.blastfishing;

import org.bukkit.command.CommandSender;

import com.jstnf.blastfishing.lang.Lang;

public class MessageManager
{
	public static void sendWithPrefix(CommandSender sender, Lang value)
	{
		sender.sendMessage(Lang.PREFIX.toString() + value.toString());
	}
}
