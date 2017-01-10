package us.plpl.planets.plus.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import us.plpl.planets.plus.items.CustomItems;

public class Main extends JavaPlugin {

	public Main plugin;

	@Override
	public void onEnable() {
		@SuppressWarnings("unused")
		PluginManager pm = Bukkit.getServer().getPluginManager();

		ShapedRecipe spaceHelmRecipe = new ShapedRecipe(CustomItems.getSpaceHelmet()).shape("III", "IGI", "GGG").setIngredient('I', Material.IRON_BLOCK).setIngredient('G', Material.GLASS);
		getServer().addRecipe(spaceHelmRecipe);
		
	}

	@Override
	public void onDisable() {

	}

	public static String color(String uncoloredtext) {
		String color = ChatColor.translateAlternateColorCodes('&', uncoloredtext);
		return color;
	}

}
