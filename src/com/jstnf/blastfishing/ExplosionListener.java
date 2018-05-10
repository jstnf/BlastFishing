package com.jstnf.blastfishing;

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

	@EventHandler
	public void onExplosion(EntityExplodeEvent e)
	{
		// hi!
	}

	private boolean isWater(Material mat)
	{
		return mat == Material.WATER || mat == Material.STATIONARY_WATER;
	}

	public boolean conditionCheck(Entity e)
	{
		return true;
	}
}
