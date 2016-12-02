package us.plpl.planets.plus.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public Main plugin;

	@Override
	public void onEnable() {
		@SuppressWarnings("unused")
		PluginManager pm = Bukkit.getServer().getPluginManager();

	}

	@Override
	public void onDisable() {

	}

	public static String color(String uncoloredtext) {
		String color = ChatColor.translateAlternateColorCodes('&', uncoloredtext);
		return color;
	}

}
