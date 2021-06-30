package com.jstnf.blastfishing.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jstnf.blastfishing.MessageManager;
import com.jstnf.blastfishing.lang.Lang;

public class Test implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		if (args.length > 0)
		{
			switch (args[0].toLowerCase())
			{
				case "messages":
					for (Lang lang : Lang.values())
					{
						sender.sendMessage(lang.getDefault());
						sender.sendMessage(lang.toString());
					}
					break;
				case "helditems":
					if (!(sender instanceof Player))
					{
						MessageManager.sendWithPrefix(sender, Lang.MUST_BE_PLAYER);
						return true;
					}
					Material mainHand = ((Player)sender).getInventory().getItemInMainHand().getType();
					Material offHand = ((Player)sender).getInventory().getItemInOffHand().getType();
					sender.sendMessage("You are currently holding " + mainHand.toString() + " in your main hand.");
					sender.sendMessage("You are currently holding " + offHand.toString() + " in your off hand.");
					break;
				default:
					sender.sendMessage(new String[] {"/bf test messages", "/bf test helditems"});
					break;
			}
		}
		else
		{
			sender.sendMessage("You ran /bf test with no arguments!");
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "";
	}
}
