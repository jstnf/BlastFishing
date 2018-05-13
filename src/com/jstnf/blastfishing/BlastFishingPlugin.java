package com.jstnf.blastfishing;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BlastFishingPlugin extends JavaPlugin
{
	private File configf, lootf, langf;
	private FileConfiguration config, loot, lang;

	@Override
	public void onEnable()
	{
		this.getLogger().info("Initializing configs...");
		createConfig();
		loadConfig();
		this.getLogger().info("Setting up explosion listener...");
		getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
		this.getCommand("blastfishing").setExecutor(new BlastFishingCommand(this));
		this.getLogger().info("BlastFishing successfully enabled.");
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
		try
		{
			if (!configf.exists())
			{
				configf.mkdirs();
				configf.createNewFile();
			}
			if (!lootf.exists())
			{
				lootf.mkdirs();
				lootf.createNewFile();
			}
			if (!langf.exists())
			{
				langf.mkdirs();
				langf.createNewFile();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	}

	private void loadLang()
	{

	}

	private void loadLoot()
	{

	}
}
