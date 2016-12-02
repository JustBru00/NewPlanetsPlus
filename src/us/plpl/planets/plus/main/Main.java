package us.plpl.planets.plus.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import us.plpl.planets.plus.planets.Sun;
import us.plpl.planets.plus.planets.Zespoanov;

public class Main extends JavaPlugin {
	
	public static Sun sun = new Sun ();
	public static Zespoanov zes = new Zespoanov();
	

	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		pm.registerEvents(sun, this);
		pm.registerEvents(zes, this);
	}

	@Override
	public void onDisable() {

	}

	public static String color(String uncoloredtext) {
		String color = ChatColor.translateAlternateColorCodes('&', uncoloredtext);
		return color;
	}

}
