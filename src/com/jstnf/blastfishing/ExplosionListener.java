package com.jstnf.blastfishing;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener
{
	private BlastFishingPlugin plugin;

	public ExplosionListener(BlastFishingPlugin bfp)
	{
		this.plugin = bfp;
	}

	/**
	 * Triggers on any entity explosion.
	 * 
	 * @param explosion
	 */
	@EventHandler
	public void onExplosion(EntityExplodeEvent explosion)
	{
		// If BlastFishing isn't toggled on, end the check.
		if (!plugin.isToggled)
			return;
		Entity e = explosion.getEntity();
		Location location = e.getLocation();
		if (conditionCheck(e, location))
		{
			Entity detonator = ((org.bukkit.entity.TNTPrimed) e).getSource();

			// Player check and permission check
			if (plugin.playerCheck && (detonator == null || !(detonator instanceof Player)))
				return;
			if (plugin.permissionCheck && detonator != null)
			{
				if (!detonator.hasPermission("blastfishing.use"))
				{
					plugin.getLogger().info(detonator.getName() + " was denied permission to blast fish.");
					detonator.sendMessage(Lang.PREFIX.toString() + Lang.USE_NO_PERMISSION.toString());
					return;
				}
			}
			
			// actual event here!
		}
	}

	/**
	 * Checks if the given Material is Water or Flowing Water.
	 * 
	 * @param mat
	 *            The Material to check.
	 * @return true if the material is water, false otherwise.
	 */
	private boolean isWater(Material mat)
	{
		return mat == Material.WATER || mat == Material.STATIONARY_WATER;
	}

	/**
	 * Checks if the TNT follows the conditions to trigger BlastFishing.
	 * 
	 * @param e
	 *            The entity being checked.
	 * @param l
	 *            The location of the entity.
	 * @return true if conditions are followed, false otherwise.
	 */
	private boolean conditionCheck(Entity e, Location l)
	{
		boolean isTNT = e instanceof org.bukkit.entity.TNTPrimed;
		boolean submerged = isWater(l.getBlock().getRelative(org.bukkit.block.BlockFace.UP).getType());

		return isTNT && submerged;
	}
}
