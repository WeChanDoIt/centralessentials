package net.dragonessentials.src.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.dragonessentials.src.Main;

public class ConfigUtils {

	private Main plugin;
	public static File main = Main.getPlugin().getDataFolder();

	public static void playerSettings(UUID member) {
		File UUID = new File(main, "playersettings.yml");
		if (!UUID.exists()) {
			try {
				UUID.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileConfiguration config = YamlConfiguration.loadConfiguration(UUID);
		if (!config.contains(member + ".username")) {
			config.set(member + ".username", Bukkit.getOfflinePlayer(member).getName());
		}
		if (!config.contains(member + ".settings.breakspawner")) {
			config.set(member + ".settings.breakspawner", true);
		}
		if (!config.contains(member + ".settings.breakblock")) {
			config.set(member + ".settings.breakblock", true);
		}
		if (!config.contains(member + ".settings.placeblock")) {
			config.set(member + ".settings.placeblock", true);
		}
		if (!config.contains(member + ".settings.openchest")) {
			config.set(member + ".settings.openchest", true);
		}
		if (!config.contains(member + ".settings.breaksign")) {
			config.set(member + ".settings.breaksign", true);
		}
		if (!config.contains(member + ".settings.placesign")) {
			config.set(member + ".settings.placesign", true);
		}
		if (!config.contains(member + ".settings.useblock")) {
			config.set(member + ".settings.useblock", true);
		}
		if (!config.contains(member + ".coopmembers")) {
			config.set(member + ".settings.coopmembers", true);
		}
		if (!config.contains(member + ".shiftplant")) {
			config.set(member + ".settings.shiftplant", true);
		}
		try {
			config.save(UUID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
