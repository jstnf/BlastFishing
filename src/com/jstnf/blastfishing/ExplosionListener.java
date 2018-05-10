package com.jstnf.blastfishing;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
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
	 * @param explosion
	 */
	@EventHandler
	public void onExplosion(EntityExplodeEvent explosion)
	{
		Entity e = explosion.getEntity();
		Location location = e.getLocation();
		
		if (conditionCheck(e, location)) {
			
		}
	}

	private boolean isWater(Material mat)
	{
		return mat == Material.WATER || mat == Material.STATIONARY_WATER;
	}

	public boolean conditionCheck(Entity e, Location l)
	{
		boolean isTNT = e instanceof org.bukkit.entity.TNTPrimed;
		boolean submerged = isWater(l.getBlock().getRelative(org.bukkit.block.BlockFace.UP).getType());
		
		return isTNT && submerged;
	}
}
