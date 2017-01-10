package us.plpl.planets.plus.items;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;
import us.plpl.planets.plus.main.Main;

public class CustomItems implements Listener {

	////////////////////////////////////////
	/////////// Add Glow to Item ///////////
	////////////////////////////////////////
	
	public static ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null)
			tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		return CraftItemStack.asCraftMirror(nmsStack);
	}

	////////////////////////////////////////
	///////////// Item Creator /////////////
	////////////////////////////////////////
	
	public static ItemStack setMeta(ItemStack material, String name, List<String> lore) {
		if (((material == null) || material.getType() == Material.AIR) || ((name == null) && lore == null))
			return null;

		ItemMeta im = material.getItemMeta();
		if (name != null)
			im.setDisplayName(name);
		if (lore != null)
			im.setLore(lore);

		material.setItemMeta(im);
		return material;

	}

	////////////////////////////////////////
	///////////// Space Helmet /////////////
	////////////////////////////////////////
	
	public static ItemStack getSpaceHelmet() {
		ItemStack spaceHelm = setMeta(new ItemStack(Material.IRON_HELMET), Main.color("&f&l// &7&lSpace Helmet &f&l//"),
				Arrays.asList(Main.color("&eFor the more experienced traveller")));

		spaceHelm = addGlow(spaceHelm);
		return spaceHelm;
	}

}
