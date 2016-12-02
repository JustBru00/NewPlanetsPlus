package us.plpl.planets.plus.planets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.plpl.planets.plus.main.Main;

public class Planet implements Listener {

	private String name;
	private int number;
	private boolean survivable;
	private boolean hasOxygen;
	private boolean allowTown;
	private boolean allowSethome;
	private boolean hasAnimals;
	private boolean canBreak;

	public String getName() {
		return name;
	}

	private void setName(String _name) {
		name = _name;
	}

	public int getNumber() {
		return number;
	}

	private void setNumber(int _number) {
		number = _number;
	}

	public boolean isSurvivable() {
		return survivable;
	}

	private void setSurvivable(boolean _survivable) {
		survivable = _survivable;
	}

	public boolean doesHaveOxygen() {
		return hasOxygen;
	}

	private void setHasOxygen(boolean _hasOxygen) {
		hasOxygen = _hasOxygen;
	}

	public boolean doesAllowTown() {
		return allowTown;
	}

	private void setAllowTown(boolean _allowTown) {
		allowTown = _allowTown;
	}

	public boolean doesAllowSethome() {
		return allowSethome;
	}

	private void setAllowSethome(boolean _allowSethome) {
		allowSethome = _allowSethome;
	}

	public boolean doesHaveAnimals() {
		return hasAnimals;
	}

	private void setHasAnimals(boolean _hasAnimals) {
		hasAnimals = _hasAnimals;
	}

	public boolean playerCanBreak() {
		return canBreak;
	}

	private void setCanBreak(boolean _canBreak) {
		canBreak = _canBreak;
	}

	public Planet(String name, int number, boolean canLive, boolean hasOxygen, boolean allowTown,
			boolean allowSethome, boolean hasAnimals, boolean canBreak) {

		setAllowSethome(allowSethome);
		setAllowTown(allowTown);
		setCanBreak(canBreak);
		setSurvivable(canLive);
		setHasAnimals(hasAnimals);
		setHasOxygen(hasOxygen);
		setName(name);
		setNumber(number);
		
	}

	@EventHandler
	public void onPlayerSwitchWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
			player.sendMessage(Main.color("&7&l[&c&lPlus+&7&l] &3Tardis landed on planet " + getName()));
		if(hasOxygen == false) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 900000, 1));
		} else {
			player.removePotionEffect(PotionEffectType.POISON);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(canBreak == false) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(canBreak == false) e.setCancelled(true);
	}

	
}
