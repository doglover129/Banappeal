package tk.skybarriermc.banappeal;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import tk.skybarriermc.banappeal.config.ConfigHandler;
import tk.skybarriermc.banappeal.listeners.PlayerListener;
import tk.skybarriermc.banappeal.mysql.MySqlDriver;

public class BanAppeal extends JavaPlugin {

	Logger log;
	MySqlDriver driver = new MySqlDriver();
	ConfigHandler config = new ConfigHandler(this);
	
	@Override
	public void onLoad() {
		this.log = this.getLogger();
	}
	
	public void onEnable() {
		initializeMySQL();
		config.setup();
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		this.log.info("Enabled!");
	}
	
	private void initializeMySQL() {
		if(!driver.start()) {
			this.log.warning("Failed to connect to MySQL database!");
		}
	}

	@Override
	public void onDisable() {
		this.driver.stop();
		this.config.save();
		this.log.info("Disabled!");
	}
	
	public MySqlDriver getDriver() {
		return driver;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		if(command.getName().equalsIgnoreCase("ba")) {
			if(args[0].equalsIgnoreCase("check")) {
				if(sender.hasPermission("banappeal.check")) {
					ArrayList<String> bannedPlayers = this.driver.getBannedPlayers();
					if(bannedPlayers != null) {
						sender.sendMessage(ChatColor.DARK_GREEN + "These players have submitted an appeal:");
						sendList(sender, bannedPlayers);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + "There are no submitted appeals!");
						return true;
					}
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command!");
					return true;
				}
			} else if(args[0].equalsIgnoreCase("help")) {
				sendHelpMessage(sender);
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Command not recognized!");
				return true;
			}
		} else {
			return false;
		}
	}
	
	private void sendHelpMessage(CommandSender sender) {
		sender.sendMessage("============BanAppeals help============");
		sender.sendMessage("/ba help: Shows this help message.");
		sender.sendMessage("/ba check: Shows you who has submitted an appeal.");
	}

	private void sendList(CommandSender player, ArrayList<String> bannedPlayers) {
		for(String s : bannedPlayers) {
			player.sendMessage(ChatColor.DARK_AQUA + s);
		}
	}
}
