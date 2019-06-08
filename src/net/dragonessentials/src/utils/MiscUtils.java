package net.dragonessentials.src.utils;

import java.util.ArrayList;
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
}
