package com.jstnf.blastfishing;

import org.bukkit.ChatColor;

public enum HardCodedLang
{
	COMMAND_HELP1("&c/bf &fCommand Help &a(page 1/3)&f:"
			+ "\n&6&l/bf &fDisplays BlastFishing credits."
			+ "\n&6&l/bf help &a<#> &fCommand help for &6/bf&f."
			+ "\n&6&l/bf options &fGUI options for BlastFishing."
			+ "\n&6&l/bf reload &fReload the BlastFishing configs."
			+ "\n&6&l/bf toggle help &fCommand help for &6/bf toggle&f."
			+ "\n&6&l/bf toggle blastfishing &fEnable/disable blast fishing."
			+ "\n&fDo &6&l/bf help 2 &ffor page 2."),
	COMMAND_HELP2("&c/bf &fCommand Help &a(page 2/3)&f:"
			+ "\n&6&l/bf toggle playercheck &fEnable/disable the player check."
			+ "\n&6&l/bf toggle permissioncheck &fEnable/disable the permission check."
			+ "\n&6&l/bf loottable help &fCommand help for &6/bf loottable&f."
			+ "\n&6&l/bf loottable items &fDisplays a GUI with all loot table items."
			+ "\n&6&l/bf loottable list &fLists all items in the loot table in chat."
			+ "\n&6&l/bf loottable get &a<index>&l &fGives the item at &aindex &fin the loot table."
			+ "\n&fDo &6&l/bf help 3 &ffor page 3."),
	COMMAND_HELP3("&c/bf &fCommand Help &a(page 3/3)&f:"
			+ "\n&6&l/bf loottable add &a<weight>&l &fAdds the held item into the loot table with the given &aweight&f."
			+ "\n&6&l/bf loottable remove &a<index>&l &fRemoves the item at &aindex &fin the loot table."
			+ "\n&6&l/bf loottable random &fGives a random item from the loot table based on its weight."),
	TOGGLE_HELP("&c/bf toggle &fCommand Help:"
			+ "\n&6&l/bf toggle help &fCommand help for &6/bf toggle&f."
			+ "\n&6&l/bf toggle blastfishing &fEnable/disable blast fishing."
			+ "\n&6&l/bf toggle playercheck &fEnable/disable the player check."
			+ "\n&6&l/bf toggle permissioncheck &fEnable/disable the permission check."),
	LOOTTABLE_HELP("&c/bf loottable &fCommand Help"
			+ "\n&6&l/bf loottable help &fCommand help for &6/bf loottable&f."
			+ "\n&6&l/bf loottable items &fDisplays a GUI with all loot table items."
			+ "\n&6&l/bf loottable list &fLists all items in the loot table in chat."
			+ "\n&6&l/bf loottable get &a<index>&l &fGives the item at &aindex &fin the loot table."
			+ "\n&6&l/bf loottable add &a<weight>&l &fAdds the held item into the loot table with the given &aweight&f."
			+ "\n&6&l/bf loottable remove &a<index>&l &fRemoves the item at &aindex &fin the loot table."
			+ "\n&6&l/bf loottable random &fGives a random item from the loot table based on its weight."),
	CREDITS("&6&lSpigot &6pokeball92870"
			+ "\n&c&lY&f&lT &cMCPokeball"
			+ "\n&b&lTwitter &b@pokeball92870"
			+ "\n&5&lGitHub &5jstnf"
			+ "\n ");
	
	private String message;
	
	private HardCodedLang(String msg) {
		this.message = msg;
	}
	
	public String toString()
	{
		return ChatColor.translateAlternateColorCodes('&', this.message);
	}
}
