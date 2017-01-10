package us.plpl.planets.plus.planets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.plpl.planets.plus.items.CustomItems;
import us.plpl.planets.plus.main.Main;

public class Planet implements Listener {

	private final Main plugin;

	public Planet(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerSwitchWorlds(PlayerChangedWorldEvent e) {

		Player player = e.getPlayer();

		// Changed World
		if (!player.getWorld().getName().contains("TARDIS")) {

			String temp = player.getWorld().getName();
			char tempc = temp.charAt(0);
			temp = temp.substring(1);
			String temps = Character.toString(tempc);
			temps = temps.toUpperCase();
			temp = temps + temp;

			player.sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &3You have transported to " + temp + " ("
					+ plugin.getConfig().getString(e.getPlayer().getWorld().getName() + ".number") + ")"));
			Bukkit.getConsoleSender().sendMessage(player.getName() + " went to planet: " + temp);
			// Has Oxygen
			if (plugin.getConfig().getBoolean("planets." + player.getWorld().getName() + ".canBreathe") == false
					&& player.getInventory().getHelmet() != CustomItems.getSpaceHelmet()) {

				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 999999, 0));

			} else {
				player.removePotionEffect(PotionEffectType.POISON);
			}

		}
	}

	@EventHandler
	public void onPlayerDrinkMilk(PlayerInteractEvent e) {
		if (!e.getPlayer().getWorld().getName().contains("TARDIS")) {
			if (plugin.getConfig().getBoolean("planets." + e.getPlayer().getWorld().getName() + ".canBreathe") == false
					&& e.getPlayer().getInventory().getItemInMainHand().getType() == Material.MILK_BUCKET) {

				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &7&oWear a space helmet to have oxygen on this planet!"));
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!e.getPlayer().getWorld().getName().contains("TARDIS")) {
			if (plugin.getConfig()
					.getBoolean("planets." + e.getPlayer().getWorld().getName() + ".canModify") == false) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &7&oYou can't edit this planet!"));
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!e.getPlayer().getWorld().getName().contains("TARDIS")) {
			if (plugin.getConfig()
					.getBoolean("planets." + e.getPlayer().getWorld().getName() + ".canModify") == false) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &7&oYou can't edit this planet!"));
			}
		}
	}

	@EventHandler
	public void onCommandRun(PlayerCommandPreprocessEvent e) {
		if (!e.getPlayer().getWorld().getName().contains("TARDIS")) {

			//Can TPA from Earth (world)
			if(plugin.getConfig().getBoolean(e.getPlayer().getWorld() + ".canTpaTo") && e.getMessage().contains("tpaccept")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &7&oYou can't tp to this planet!"));
			}
			
			// Can Claim
			if (plugin.getConfig().getBoolean("planets." + e.getPlayer().getWorld().getName() + ".canClaim") == false) {
				if (e.getMessage().contains("town add") || e.getMessage().contains("t add")
						|| e.getMessage().contains("t claim") || e.getMessage().contains("town claim")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &7&oYou can't claim on this planet!"));
				}
			}

			// Can Sethome
			if (plugin.getConfig().getBoolean(e.getPlayer().getWorld().getName() + "setHome") == false
					&& e.getMessage().contains("sethome")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &7&oYou can't set home on this planet!"));
			}
		}
	}

	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e) {
		if (!e.getEntity().getWorld().getName().contains("TARDIS") && e.getEntity().getType() != EntityType.PLAYER
				&& plugin.getConfig().getBoolean(e.getEntity().getWorld().getName() + ".supportsLife") == false) {
			e.setCancelled(true);
		}
	}
}
