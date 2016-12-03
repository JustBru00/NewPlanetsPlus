package us.plpl.planets.plus.planets;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import us.plpl.planets.plus.main.Main;

public class Planet implements Listener {

	private final Main plugin;

	public Planet(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerSwitchWorlds(PlayerChangedWorldEvent e) {
		if(!e.getPlayer().getWorld().getName().contains("TARDIS")) {
			
			String temp = e.getPlayer().getWorld().getName();
			char tempc = temp.charAt(0);
			temp = temp.substring(1);
			String temps = Character.toString(tempc);
			temps = temps.toUpperCase();
			temp = temps + temp;
			
		e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &3You have transported to " + temp));
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		if (plugin.getConfig().getBoolean("planets." + e.getPlayer().getWorld().getName() + ".Can-Modify") == false) {
			e.setCancelled(true);
		} 

	}

}
