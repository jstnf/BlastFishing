package com.jstnf.blastfishing.legacy;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BlastFishingListener implements Listener
{

	private BlastFishing bf;
	private Random rand;

	public BlastFishingListener(BlastFishing bf)
	{
		this.bf = bf;
		this.rand = new Random();
	}

	@EventHandler
	public void onExplosion(EntityExplodeEvent explosion)
	{
		if (bf.isToggled)
		{
			Entity e = explosion.getEntity();
			Location location = e.getLocation();

			if (e instanceof org.bukkit.entity.TNTPrimed
					&& isWater(location.getBlock().getRelative(org.bukkit.block.BlockFace.UP).getType()))
			{

				Entity detonator = ((org.bukkit.entity.TNTPrimed) e).getSource();

				/**
				 * If player check is enabled, then check if the detonator of the TNT is a
				 * Player. If conditions aren't met, return.
				 */
				if (bf.playerCheck)
				{
					if (!(detonator != null && detonator instanceof Player))
					{
						return;
					}
				}

				/**
				 * If permission check is enabled, then check if the detonator of the TNT has
				 * the permission blastfishing.use. If conditions aren't met, return.
				 */
				if (bf.permissionCheck)
				{
					if (detonator != null)
					{
						if (detonator instanceof Player && !detonator.hasPermission("blastfishing.use"))
						{
							return;
						}
					}
				}

				/**
				 * Define range based on radius and check all blocks around explosion.
				 */
				int startX = location.getBlockX() - bf.radius, endX = location.getBlockX() + bf.radius;
				int startY = location.getBlockY() - bf.radius, endY = location.getBlockY() + bf.radius;
				int startZ = location.getBlockZ() - bf.radius, endZ = location.getBlockZ() + bf.radius;

				for (int posX = startX; posX <= endX; posX++)
				{
					for (int posY = startY; posY <= endY; posY++)
					{
						for (int posZ = startZ; posZ <= endZ; posZ++)
						{
							Location fishSpawn = new Location(e.getWorld(), posX, posY, posZ);
							if (isWater(fishSpawn.getBlock().getType()))
							{
								if (rand.nextInt(100) < bf.spawnChance)
								{
									double xScale = (fishSpawn.getBlockX() - location.getBlockX()) * 0.25;
									double zScale = (fishSpawn.getBlockZ() - location.getBlockZ()) * 0.25;
									double yScale = rand.nextDouble() * 0.5 + 0.5;
									Vector dir = new Vector(xScale, yScale, zScale);

									ItemStack toSpawn = getLoot();
									if (toSpawn != null)
										e.getWorld().dropItemNaturally(fishSpawn, toSpawn).setVelocity(dir);
								}
							}
						}
					}
				}

			}
		}
	}

	private boolean isWater(Material mat)
	{
		return mat == Material.WATER || mat == Material.STATIONARY_WATER;
	}

	private ItemStack getLoot()
	{
		int sel = rand.nextInt(bf.lootHandler.totalWeight) + 1;
		String manip = bf.lootHandler.get(sel);
		try
		{
			String itemName;
			int data;
			ItemStack result;

			int colon = manip.indexOf(":");
			if (colon != -1)
			{
				itemName = manip.substring(0, colon);
				data = Integer.parseInt(manip.substring(colon + 1, manip.length()));
				result = new ItemStack(Material.getMaterial(itemName), 1, (short) data);
			}
			else
			{
				itemName = manip;
				result = new ItemStack(Material.getMaterial(itemName));
			}
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
