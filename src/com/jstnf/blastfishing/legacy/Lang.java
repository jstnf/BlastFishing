
package com.jstnf.blastfishing.legacy;

import net.md_5.bungee.api.ChatColor;

public enum Lang
{
	PREFIX("&8[&cBlastFishing&8] "),
	TOGGLE_ON("&fBlastFishing has been toggled &aON&f."),
	TOGGLE_OFF("&fBlastFishing has been toggled &cOFF&f."),
	TOGGLE_NO_PERMISSION("&fYou do not have permission to toggle BlastFishing."),
	PLAYERCHECK_ON("&fBlastFishing will &aCHECK &fif a player detonated the TNT."),
	PLAYERCHECK_OFF("&fBlastFishing will &cNOT CHECK &fif a player detonated the TNT."),
	PLAYERCHECK_NO_PERMISSION("&fYou do not have permission to toggle the player check."),
	PERMISSIONCHECK_ON("&fBlastFishing will &aCHECK &fif players have permission."),
	PERMISSIONCHECK_OFF("&fBlastFishing will &cNOT CHECK &fif players have permission."),
	PERMISSIONCHECK_NO_PERMISSION("&fYou do not have permission to toggle the permission check."),
	RELOAD_SUCCESS("&fReloaded configuration."),
	RELOAD_NO_PERMISSION("&fYou do not have permission to reload BlastFishing."),
	INVALID_SUBCOMMAND("&fInvalid command usage. Use &6/blastfishing &ffor help.");

	private String message;

	private Lang(String msg)
	{
		this.message = msg;
	}

	public String get()
	{
		return ChatColor.translateAlternateColorCodes('&', message);

	}
}
