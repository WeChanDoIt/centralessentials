package net.dragonessentials.src.recipes;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.dragonessentials.src.utils.ChatUtils;

public class Recipes implements Listener {
	
	public static ShapelessRecipe sugarcookie() {
		ItemStack item = new ItemStack(Material.COOKIE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&fSugar Cookie"));
		item.setItemMeta(meta);

		ShapelessRecipe slr = new ShapelessRecipe(item);

		slr.addIngredient(1, Material.WHEAT);
		slr.addIngredient(1, Material.SUGAR);
		
		return slr;
	}

	public static ShapelessRecipe potatostew() {
		ItemStack item = new ItemStack(Material.MUSHROOM_SOUP, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&6Potato Stew"));
		item.setItemMeta(meta);

		ShapelessRecipe slr = new ShapelessRecipe(item);

		slr.addIngredient(1, Material.POTATO_ITEM);
		slr.addIngredient(1, Material.MILK_BUCKET);
		
		return slr;
	}
	
	public static ShapelessRecipe goldcarrot() {
		ItemStack item = new ItemStack(Material.GOLDEN_CARROT, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&eGold Carrot"));
		item.setItemMeta(meta);

		ShapelessRecipe slr = new ShapelessRecipe(item);

		slr.addIngredient(1, Material.GOLD_INGOT);
		slr.addIngredient(1, Material.CARROT_ITEM);
		
		return slr;
	}
	
	public static ShapelessRecipe goldmelon() {
		ItemStack item = new ItemStack(Material.SPECKLED_MELON, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&eGold Melon"));
		item.setItemMeta(meta);

		ShapelessRecipe slr = new ShapelessRecipe(item);

		slr.addIngredient(1, Material.GOLD_BLOCK);
		slr.addIngredient(1, Material.MELON);
		
		return slr;
	}
	
	public static ShapelessRecipe nethercandy() {
		ItemStack item = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&cNether Candy"));
		item.setItemMeta(meta);

		ShapelessRecipe slr = new ShapelessRecipe(item);

		slr.addIngredient(1, Material.BLAZE_POWDER);
		slr.addIngredient(1, Material.NETHER_STALK);
		
		return slr;
	}

}
