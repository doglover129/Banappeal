package tk.skybarriermc.banappeal.mysql;

import java.util.ArrayList;

public class MySqlDriver {

	public boolean start() {
		boolean connection = true;
		//TODO: setup a database connection here and return connection set to true when the connection is live.
		return connection;
	}
	
	public void stop() {
		//TODO: save database and disconnect
	}
	
	public ArrayList<String> getBannedPlayers() {
		//TODO: do some weird sql stuff here to get all the banned players in a list and return that list.
		return null;
	}
}
