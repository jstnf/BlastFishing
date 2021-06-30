package com.jstnf.blastfishing.lang;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang
{
	PREFIX("prefix", "&c&lBlast&f&lFishing &7>>>"),
	
	ON_RELOAD("on-reload", "&fReloading BlastFishing..."),
	RELOAD_SUCCESS("reload-success", "&aBlastFishing was successfully reloaded."),
	RELOAD_FAILURE("reload-failure", "&cBlastFishing could not reload. Please see the console for errors."),

	USE_NO_PERMISSION("use-no-permission", "&cYou do not have permission to blast fish."),
	MENU_NO_PERMISSION("menu-no-permission", "&cYou do not have permission to use the options menu."),
	RELOAD_NO_PERMISSION("reload-no-permission", "&cYou do not have permission to reload BlastFishing."),
	HELP_NO_PERMISSION("help-no-permission", "&cYou do not have permission to see command help."),
	TOGGLE_HELP_NO_PERMISSION("toggle-help-no-permission", "&cYou do not have permission to see command help."),
	LOOTTABLE_HELP_NO_PERMISSION("loottable-help-no-permission", "&cYou do not have permission to see command help."),
	
	BFTOGGLE_NO_PERMISSION("bftoggle-no-permission", "&cYou do not have permission to toggle BlastFishing."),
	PERMISSIONCHECK_NO_PERMISSION("permissioncheck-no-permission", "&cYou do not have permission to toggle the permission check."),
	PLAYERCHECK_NO_PERMISSION("playercheck-no-permission", "&cYou do not have permission to toggle the player check."),
	
	LOOTTABLE_ITEMS_NO_PERMISSION("loottable-items-no-permission", "&cYou do not have permission to view the loot table."),
	LOOTTABLE_LIST_NO_PERMISSION("loottable-list-no-permission", "&cYou do not have permission to list the loot table."),
	LOOTTABLE_GET_NO_PERMISSION("loottable-get-no-permission", "&cYou do not have permission to get items from the loot table."),
	LOOTTABLE_ADD_NO_PERMISSION("loottable-add-no-permission", "&cYou do not have permission to add items to the loot table."),
	LOOTTABLE_REMOVE_NO_PERMISSION("loottable-remove-no-permission", "&cYou do not have permission to remove items from the loot table."),
	LOOTTABLE_RANDOM_NO_PERMISSION("loottable-random-no-permission", "&cYou do not have permission to spawn items from the loot table."),

	BFTOGGLE_ON("bftoggle-on", "&fBlastFishing is now &aENABLED&f."),
	BFTOGGLE_OFF("bftoggle-off", "&fBlastFishing is now &cDISABLED&f."),
	PERMISSIONCHECK_ON("permissioncheck-on", "&fPermission check is now &aENABLED&f. Players will need the &6blastfishing.use&f permission in order to use BlastFishing."),
	PERMISSIONCHECK_OFF("permissioncheck-off", "&fPermission check is now &cDISABLED&f. All players can use BlastFishing."),
	PLAYERCHECK_ON("playercheck-on", "&fPlayer check is now &aENABLED&f. Items will only drop if a player detonates the TNT."),
	PLAYERCHECK_OFF("playercheck-off", "&fPlayer check is now &cDISABLED&f. Items will drop regardless if a player detonates the TNT."),
	
	INVALID_SUBCOMMAND("invalid-subcommand", "&fInvalid command usage. Please use &6/bf help &ffor help."),
	INVALID_TOGGLE_SUBCOMMAND("invalid-toggle-subcommand", "&fInvalid command usage. Please use &6/bf toggle help&f for help."),
	INVALID_LOOTTABLE_SUBCOMMAND("invalid-loottable-subcommand", "&fInvalid command usage. Please use &6/bf loottable help&f for help."),
	INVALID_HELP_SUBCOMMAND("invalid-help-subcommand", "&cUsage: /bf help 1-3"),
	
	MUST_BE_PLAYER("must-be-player", "&cYou must be a player to execute this command!"),
	TOO_MANY_ARGUMENTS("too-many-arguments", "&cYou inserted too many arguments!"),
	
	INVALID_LOOTTABLE_ADD_SUBCOMMAND("invalid-loottable-add-subcommand", "&cUsage: /bf loottable add <weight>"),
	LOOTTABLE_ADD_NO_ITEM("loottable-add-no-item", "&cYou must be holding an item to add to the loot table."),
	LOOTTABLE_ADD_INVALID_ITEM("loottable-add-invalid-item", "&cThe item you tried to add was invalid."),
	LOOTTABLE_ADD_NO_WEIGHT("loottable-add-no-weight", "&cYou must specify a weight."),
	LOOTTABLE_ADD_INVALID_WEIGHT("loottable-add-invalid-weight", "&cThe weight you entered was invalid. Valid weights include integers greater than 0."),
	LOOTTABLE_ADD_SUCCESS("loottable-add-success", "&aThe item was successfully added."),
	LOOTTABLE_ADD_FAILURE("loottable-add-failure", "&cThere was an error adding the item."),
	LOOTTABLE_REMOVE_SUCCESS("loottable-remove-success", "&aThe item was successfully removed."),
	LOOTTABLE_REMOVE_FAILURE("loottable-remove-failure", "&cThere was an error removing the item."),
	LOOTTABLE_GIVE_FAILURE_EMPTY("loottable-give-failure-empty", "&cThe loot table is empty."),
	LOOTTABLE_GIVE_FAILURE_INVFULL("loottable-give-failure-invfull", "&cYour inventory is full."),
	LOOTTABLE_GIVE_FAILURE("loottable-give-failure", "&cThere was an error giving you an item."),
	LOOTTABLE_GIVE_RANDOM_SUCCESS("loottable-give-random-success", "&aGiven a random item from the loot table based on item weight."),
	LOOTTABLE_GIVE_SUCCESS("loottable-give-success", "&aGiven item %i% in the loot table."),

	TO_IMPLEMENT("to-implement", "&fTo implement!");
	
	private String path, def;
	private static YamlConfiguration langFile;

	/**
	 * Lang enum constructor.
	 * 
	 * @param path Path in config file.
	 * @param msg The default message.
	 */
	private Lang(String path, String msg)
	{
		this.path = path;
		this.def = msg;
	}
	
	/**
	 * Set the {@code YamlConfiguration} to use.
	 * 
	 * @param file The lang file.
	 */
	public static void setConfig(YamlConfiguration file)
	{
		langFile = file;
	}

	@Override
	/**
	 * Gets the config message of the path.
	 * 
	 * @return The config message.
	 */
	public String toString()
	{
		if (this == PREFIX)
		{
			return ChatColor.translateAlternateColorCodes('&', langFile.getString(this.path, this.def)) + " ";
		}
		return ChatColor.translateAlternateColorCodes('&', langFile.getString(this.path, this.def));
	}

	/**
	 * Gets the default message of the path.
	 * 
	 * @return The default message.
	 */
	public String getDefault()
	{
		if (this == PREFIX)
		{
			return ChatColor.translateAlternateColorCodes('&', this.def) + " ";
		}
		return ChatColor.translateAlternateColorCodes('&', this.def);
	}
	
	/**
	 * Gets the path to the String.
	 * 
	 * @return The path to the String.
	 */
	public String getPath()
	{
		return this.path;
	}
}