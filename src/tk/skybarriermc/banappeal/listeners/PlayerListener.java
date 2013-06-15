package tk.skybarriermc.banappeal.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.skybarriermc.banappeal.BanAppeal;

public class PlayerListener implements Listener {

	BanAppeal plugin;
	
	public PlayerListener(BanAppeal plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ArrayList<String> bannedPlayers = this.plugin.getDriver().getBannedPlayers();
		if(player.hasPermission("banappeals.check")) {
			if(bannedPlayers != null) {
				player.sendMessage(ChatColor.DARK_GREEN + "These players have submitted an appeal:");
				sendList(player, bannedPlayers);
			}
		}
	}

	private void sendList(Player player, ArrayList<String> bannedPlayers) {
		for(String s : bannedPlayers) {
			player.sendMessage(ChatColor.DARK_AQUA + s);
		}
	}
}
