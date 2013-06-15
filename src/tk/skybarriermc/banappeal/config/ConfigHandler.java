package tk.skybarriermc.banappeal.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tk.skybarriermc.banappeal.BanAppeal;

public class ConfigHandler {

	BanAppeal plugin;
	FileConfiguration config;
	File configFile;
	
	public ConfigHandler(BanAppeal plugin) {
		this.plugin = plugin;
		configFile = new File(plugin.getDataFolder(), "config.yml");
	}
	
	public void setup() {
		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public void save() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getHost() {
		return this.config.getString("database.host");
	}
	
	public Integer getPort() {
		return this.config.getInt("database.port");
	}
	
	public String getDatabaseName() {
		return this.config.getString("database.database");
	}
	
	public String getUsername() {
		return this.config.getString("database.username");
	}
	
	public String getPassword() {
		return this.config.getString("database.password");
	}
}
