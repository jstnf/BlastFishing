package com.jstnf.blastfishing.legacy;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlastFishing extends JavaPlugin {

	private Map<String, Object> defLoot = getDefaultLoot();
	public PluginDescriptionFile pdf;
	public LootHandler lootHandler;
	
	// Defaults in config.yml
	public boolean isToggled = true, playerCheck = true, permissionCheck = true, autoSaveConfig = true;
	public int spawnChance = 30, radius = 2;
	public Map<String, Object> lootTable = defLoot;

	@Override
	public void onEnable() {
		pdf = this.getDescription();

		// config.yml
		try {
			File fileconfig = new File(getDataFolder(), "config.yml");
			if (!fileconfig.exists()) {
				getDataFolder().mkdir();
				this.getConfig().addDefault("check-permissions", true);
				this.getConfig().addDefault("check-if-player", true);
				this.getConfig().addDefault("enabled-on-server", true);
				this.getConfig().addDefault("auto-save-config", true);
				this.getConfig().addDefault("blast-fishing-radius", 2);
				this.getConfig().addDefault("chance-per-block", 30);
				this.getConfig().createSection("loot-table", defLoot);
				this.getConfig().options().copyDefaults(true);
				this.getConfig().options().header(
						"BlastFishing Configuration File\n"
						+ "\ncheck-permissions - If true, only spawn fish from blast fishing if player has the permission blastfishing.use."
						+ "\ncheck-if-player - If true, only spawn fish if the TNT was detonated by a player."
						+ "\nenabled-on-server - If true, blast fishing will be enabled."
						+ "\nauto-save-config - If true, any changes made to the above using in-game commands will automatically be saved in the config file."
						+ "\nblast-fishing-radius - Amount of blocks out from TNT that BlastFishing will check for water and (potentially) spawn a fish."
						+ "\nchance-per-block - Percentage change that a fish will spawn in a checked block. If set to 30, there is a 30% chance a fish will spawn."
						+ "\nloot-table - The item(s) gained from fishing followed by its weight. The higher the number, the more likely it is to be chosen."
						+ "\n"
						+ "\nloot-table syntax: <id>:<data-value>: <weight>"
						+ "\n                   <id>: <weight>"
						+ "\n"
						+ "\nExample:"
						+ "\n"
						+ "\nloot-table:"
						+ "\n  RAW_FISH: 2"
						+ "\n  COOKED_FISH: 1"
						+ "\n  RAW_FISH:1: 2"
						+ "\n  STICK: 1"
						+ "\n"
						+ "\nThis loot table will cause the likelihood of RAW_FISH to be 2 times that of COOKED_FISH."
						+ "\nAdditionally, the likelihood that RAW_FISH:1 (Raw Salmon) will spawn is equal to that of RAW_FISH."
						+ "\nYou can also add other items besides fish!"
						+ "\n"
						+ "\nA list of all item names can be found here:"
						+ "\nhttps://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html"
						+ "\n");
				this.getConfig().options().copyHeader(true);
				this.saveConfig();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		pullFromConfig();
		this.getLogger().info("Configuration loaded.");
		
		this.getCommand("blastfishing").setExecutor(new CommandBlastFishing(this));
		getServer().getPluginManager().registerEvents(new BlastFishingListener(this), this);
		this.getLogger().info("BlastFishing v" + pdf.getVersion() + " has been successfully enabled.");
	}

	@Override
	public void onDisable() {
		if (autoSaveConfig) {
			pushToConfig();
		}
		this.getLogger().info("BlastFishing v" + pdf.getVersion() + " has been successfully disabled.");
	}

	public boolean toggle() {
		isToggled = !isToggled;
		return isToggled;
	}

	public boolean togglePlayerCheck() {
		playerCheck = !playerCheck;
		return playerCheck;
	}

	public boolean togglePermissionCheck() {
		permissionCheck = !permissionCheck;
		return permissionCheck;
	}

	public void pullFromConfig() {
		permissionCheck = this.getConfig().getBoolean("check-permissions");
		playerCheck = this.getConfig().getBoolean("check-if-player");
		isToggled = this.getConfig().getBoolean("enabled-on-server");
		autoSaveConfig = this.getConfig().getBoolean("auto-save-config");
		radius = this.getConfig().getInt("blast-fishing-radius");
		spawnChance = this.getConfig().getInt("chance-per-block");
		try {
			lootTable = this.getConfig().getConfigurationSection("loot-table").getValues(true);
		} catch(Exception e) {
			e.printStackTrace();
			this.getLogger().info("Unable to get loot table from config!");
			this.getLogger().info("Please check your config file for any mistakes and report this stack trace to the author.");
			this.getLogger().info("Grabbing defualt loot table...");
			lootTable = getDefaultLoot();
		}
		lootHandler = initLootTable();
	}
	
	public void pushToConfig() {
		this.getConfig().set("check-permissions", permissionCheck);
		this.getConfig().set("check-if-player", playerCheck);
		this.getConfig().set("enabled-on-server", isToggled);
		this.getConfig().set("auto-save-config", autoSaveConfig);
		this.getConfig().set("blast-fishing-radius", radius);
		this.getConfig().set("chance-per-block", spawnChance);
		this.saveConfig();
	}
	
	private Map<String, Object> getDefaultLoot() {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("RAW_FISH", 40);
		res.put("COOKED_FISH", 20);
		res.put("RAW_FISH:1", 16);
		res.put("COOKED_FISH:1", 9);
		res.put("RAW_FISH:2", 2);
		res.put("RAW_FISH:3", 13);
		return res;
	}
	
	@SuppressWarnings("rawtypes")
	private LootHandler initLootTable() {
		LootHandler table = new LootHandler();
		Iterator it = lootTable.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			table.add((String)pair.getKey(), (int)pair.getValue());
			it.remove();
		}
		return table;
	}
	
}