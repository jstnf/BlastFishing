package com.jstnf.blastfishing;

public enum Lang
{
	PREFIX("prefix", "&7[&cBlastFishing&7] &f"),
	
	ON_RELOAD("on-reload", "&fReloading BlastFishing..."),
	RELOAD_SUCCESS("reload-success", "&fBlastFishing was successfully reloaded."),
	RELOAD_FAILURE("reload-failure", "&fBlastFishing could not reload. Please see the console for errors."),

	USE_NO_PERMISSION("use-no-permission", "&fYou do not have permission to blast fish."),
	MENU_NO_PERMISSION("menu-no-permission", "&fYou do not have permission to use the options menu."),
	RELOAD_NO_PERMISSION("reload-no-permission", "&fYou do not have permission to reload BlastFishing."),
	HELP_NO_PERMISSION("help-no-permission", "&fYou do not have permission to see command help."),
	
	BFTOGGLE_NO_PERMISSION("bftoggle-no-permission", "&fYou do not have permission to toggle BlastFishing."),
	PERMISSIONCHECK_NO_PERMISSION("permissioncheck-no-permission", "&fYou do not have permission to toggle the permission check."),
	PLAYERCHECK_NO_PERMISSION("playercheck-no-permission", "&fYou do not have permission to toggle the player check."),
	
	LOOTTABLE_ITEMS_NO_PERMISSION("loottable-items-no-permission", "&fYou do not have permission to view the loot table."),
	LOOTTABLE_LIST_NO_PERMISSION("loottable-list-no-permission", "&fYou do not have permission to list the loot table."),
	LOOTTABLE_GET_NO_PERMISSION("loottable-get-no-permission", "&fYou do not have permission to get items from the loot table."),
	LOOTTABLE_ADD_NO_PERMISSION("loottable-add-no-permission", "&fYou do not have permission to add items to the loot table."),
	LOOTTABLE_REMOVE_NO_PERMISSION("loottable-remove-no-permission", "&fYou do not have permission to remove items from the loot table."),
	LOOTTABLE_RANDOM_NO_PERMISSION("loottable-random-no-permission", "&fYou do not have permission to spawn items from the loot table."),

	BFTOGGLE_ON("bftoggle-on", "&fBlastFishing is now &aENABLED&f."),
	BFTOGGLE_OFF("bftoggle-off", "&fBlastFishing is now &cDISABLED&f."),
	PERMISSIONCHECK_ON("permissioncheck-on", "&fPermission check is now &aENABLED&f. Players will need the &6blastfishing.use&f permission in order to use BlastFishing."),
	PERMISSIONCHECK_OFF("permissioncheck-off", "&fPermission check is now &cDISABLED&f. All players can use BlastFishing."),
	PLAYERCHECK_ON("playercheck-on", "&fPlayer check is now &aENABLED&f. Items will only drop if a player detonates the TNT."),
	PLAYERCHECK_OFF("playercheck-off", "&fPlayer check is now &cDISABLED&f."),
	
	INVALID_SUBCOMMAND("invalid-subcommand", "&fInvalid command usage. Please use &6/bf help &ffor help."),
	INVALID_TOGGLE_SUBCOMMAND("invalid-toggle-subcommand", "&fInvalid command usage. Please use &6/bf toggle help &ffor help."),
	INVALID_LOOTTABLE_SUNCOMMAND("invalid-loottable-subcommand", "&fInvalid command usage. Please use &6/bf loottable help &ffor help.");

	private String path, message;

	private Lang(String path, String msg)
	{
		this.path = path;
		this.message = msg;
	}

	public String get()
	{
		String toReturn = this.message;
		if (this == PREFIX)
		{
			toReturn += " ";
		}
		return toReturn;
	}
}