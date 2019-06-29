package net.dragonessentials.src.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;

public class MiscUtils {
	
	public static int getRandomNumberFrom(int min, int max) {
		Random r = new Random();
		int Low = min;
		int High = max + 1;
		int R = r.nextInt(High - Low) + Low;
		return R;
	}

	public static boolean getRandomChance(int chance, int denominator) {
		Random gen = new Random();
		int r = gen.nextInt(denominator);
		int chance1 = chance;
		if (r <= chance1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<Material> plantBlockTable() {
		List<Material> plantBlockTable = new ArrayList<>();

		plantBlockTable.add(Material.CROPS);
		plantBlockTable.add(Material.POTATO);
		plantBlockTable.add(Material.CARROT);
		plantBlockTable.add(Material.MELON_STEM);
		plantBlockTable.add(Material.PUMPKIN_STEM);

		return plantBlockTable;
		
	}
	
	public static List<Material> plantCropTable() {
		List<Material> plantCropTable = new ArrayList<>();

		plantCropTable.add(Material.SEEDS);
		plantCropTable.add(Material.POTATO_ITEM);
		plantCropTable.add(Material.CARROT_ITEM);
		plantCropTable.add(Material.MELON_SEEDS);
		plantCropTable.add(Material.PUMPKIN_SEEDS);
		
		return plantCropTable;
		
	}
	
	public static List<String> rainbowList() {
		List<String> rainbowchars = new ArrayList<>();
		rainbowchars.add("&c");
		rainbowchars.add("&c");
		rainbowchars.add("&e");
		rainbowchars.add("&e");
		rainbowchars.add("&a");
		rainbowchars.add("&a");
		rainbowchars.add("&b");
		rainbowchars.add("&9");
		rainbowchars.add("&d");
		
		return rainbowchars;
	}
	
	public static List<Material> toolTable() {
		List<Material> toolTable = new ArrayList<>();

		toolTable.add(Material.WOOD_HOE);
		toolTable.add(Material.WOOD_PICKAXE);
		toolTable.add(Material.WOOD_SPADE);
		toolTable.add(Material.FISHING_ROD);
		toolTable.add(Material.BOW);
		
		toolTable.add(Material.STONE_HOE);
		toolTable.add(Material.STONE_PICKAXE);
		toolTable.add(Material.STONE_SPADE);
		
		toolTable.add(Material.GOLD_HOE);
		toolTable.add(Material.GOLD_PICKAXE);
		toolTable.add(Material.GOLD_SPADE);

		toolTable.add(Material.IRON_HOE);
		toolTable.add(Material.IRON_PICKAXE);
		toolTable.add(Material.IRON_SPADE);
		
		toolTable.add(Material.DIAMOND_HOE);
		toolTable.add(Material.DIAMOND_PICKAXE);
		toolTable.add(Material.DIAMOND_SPADE);
		
		return toolTable;
		
	}
	
	public static List<Material> weaponTable() {
		List<Material> weaponTable = new ArrayList<>();
		
		weaponTable.add(Material.WOOD_AXE);
		weaponTable.add(Material.WOOD_SWORD);
		
		weaponTable.add(Material.STONE_AXE);
		weaponTable.add(Material.STONE_SWORD);
		
		weaponTable.add(Material.GOLD_AXE);
		weaponTable.add(Material.GOLD_SWORD);
		
		weaponTable.add(Material.IRON_AXE);
		weaponTable.add(Material.IRON_SWORD);
		
		weaponTable.add(Material.DIAMOND_AXE);
		weaponTable.add(Material.DIAMOND_SWORD);
		return weaponTable;
	}
	
	public static List<Material> armorTable() {
		List<Material> armorTable = new ArrayList<>();

		armorTable.add(Material.LEATHER_HELMET);
		armorTable.add(Material.LEATHER_CHESTPLATE);
		armorTable.add(Material.LEATHER_LEGGINGS);
		armorTable.add(Material.LEATHER_BOOTS);

		armorTable.add(Material.CHAINMAIL_HELMET);
		armorTable.add(Material.CHAINMAIL_CHESTPLATE);
		armorTable.add(Material.CHAINMAIL_LEGGINGS);
		armorTable.add(Material.CHAINMAIL_BOOTS);

		armorTable.add(Material.GOLD_HELMET);
		armorTable.add(Material.GOLD_CHESTPLATE);
		armorTable.add(Material.GOLD_LEGGINGS);
		armorTable.add(Material.GOLD_BOOTS);

		armorTable.add(Material.IRON_HELMET);
		armorTable.add(Material.IRON_CHESTPLATE);
		armorTable.add(Material.IRON_LEGGINGS);
		armorTable.add(Material.IRON_BOOTS);

		armorTable.add(Material.DIAMOND_HELMET);
		armorTable.add(Material.DIAMOND_CHESTPLATE);
		armorTable.add(Material.DIAMOND_LEGGINGS);
		armorTable.add(Material.DIAMOND_BOOTS);
		
		return armorTable;
		
	}
	
	public static LinkedHashMap<Material, Integer> fuelTable() {
		LinkedHashMap<Material, Integer> fuelTable = new LinkedHashMap<Material, Integer>();
		
		fuelTable.put(Material.LOG, 12);
		fuelTable.put(Material.WOOD, 3);
		fuelTable.put(Material.COAL_ORE, 12);
		fuelTable.put(Material.COAL, 16); // coal and charcoal have the same material value
		fuelTable.put(Material.COAL_BLOCK, 144);
		fuelTable.put(Material.BLAZE_ROD, 16);
		fuelTable.put(Material.LAVA_BUCKET, 288);
	
		return fuelTable;
	}
	
	public static HashMap<Material, Material> smeltTable() {
		HashMap<Material, Material> smeltTable = new HashMap<Material, Material>();
		
		smeltTable.put(Material.COBBLESTONE, Material.STONE);
		smeltTable.put(Material.STONE, Material.STONE);
		smeltTable.put(Material.IRON_ORE, Material.IRON_INGOT);
		smeltTable.put(Material.GOLD_ORE, Material.GOLD_INGOT);
		smeltTable.put(Material.NETHERRACK, Material.NETHER_BRICK_ITEM);
		
		return smeltTable;
	}
}
