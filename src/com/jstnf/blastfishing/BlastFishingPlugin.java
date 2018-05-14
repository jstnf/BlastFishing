package com.jstnf.blastfishing;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlastFishingPlugin extends JavaPlugin
{
	private File configf, lootf, langf;
	private FileConfiguration config, loot, lang;
	public PluginDescriptionFile pdf;
	
	// config.yml defaults
	public boolean isToggled = true, playerCheck = false, permissionCheck = false, pushToConfig = true;
	public int spawnChancePerBlock = 30, radiusCheck = 2;
	
	private final int CONFIG_VERSION = 1;

	@Override
	public void onEnable()
	{
		this.pdf = this.getDescription();
		this.getLogger().info("Initializing configs...");
		createConfig();
		loadConfig();
		loadLang();
		loadLoot();
		this.getLogger().info("Setting up explosion listener...");
		this.getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
		this.getLogger().info("Setting up commands...");
		this.getCommand("blastfishing").setExecutor(new BlastFishingCommand(this));
		this.getLogger().info("BlastFishing v" + pdf.getVersion() + " successfully enabled.");
	}

	@Override
	public void onDisable()
	{
		this.getLogger().info("BlastFishing successfully disabled.");
	}

	private void createConfig()
	{
		configf = new File(getDataFolder(), "config.yml");
		lootf = new File(getDataFolder(), "loot.yml");
		langf = new File(getDataFolder(), "lang.yml");
		if (!configf.exists())
		{
			configf.getParentFile().mkdirs();
			saveResource("config.yml", false);
		}
		if (!lootf.exists())
		{
			lootf.getParentFile().mkdirs();
			saveResource("loot.yml", false);
		}
		if (!langf.exists())
		{
			langf.getParentFile().mkdirs();
			saveResource("lang.yml", false);
		}
		config = new YamlConfiguration();
		loot = new YamlConfiguration();
		lang = new YamlConfiguration();
		try
		{
			config.load(configf);
			loot.load(lootf);
			lang.load(langf);
		}
		catch (IOException | InvalidConfigurationException e)
		{
			this.getLogger().info("Something went wrong loading the config files. Report this!");
			e.printStackTrace();
		}
	}

	public FileConfiguration getConfig()
	{
		return this.config;
	}

	public FileConfiguration getLangConfig()
	{
		return this.lang;
	}

	public FileConfiguration getLootConfig()
	{
		return this.loot;
	}

	private void loadConfig()
	{
		isToggled = this.getConfig().getBoolean("enabled-on-server");
		playerCheck = this.getConfig().getBoolean("player-check");
		permissionCheck = this.getConfig().getBoolean("permission-check");
		pushToConfig = this.getConfig().getBoolean("push-to-config");
		radiusCheck = this.getConfig().getInt("blast-radius");
		spawnChancePerBlock = this.getConfig().getInt("chance-per-block");
		this.saveConfig();
	}

	private void loadLang()
	{
		try
		{
			Lang.setConfig((YamlConfiguration) lang);
		}
		catch (Exception e)
		{
			this.getLogger().info("Something went wrong with the lang file. Using default messages. Report this!");
			e.printStackTrace();
		}
	}

	private void loadLoot()
	{

	}
	
	public boolean toggleBF()
	{
		this.isToggled = !this.isToggled;
		return this.isToggled;
	}
	
	public boolean togglePlayerCheck()
	{
		this.playerCheck = !this.playerCheck;
		return this.playerCheck;
	}
	
	public boolean togglePermissionCheck()
	{
		this.permissionCheck = !this.permissionCheck;
		return this.permissionCheck;
	}
}
