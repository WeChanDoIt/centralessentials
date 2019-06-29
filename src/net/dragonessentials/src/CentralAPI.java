package net.dragonessentials.src;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CentralAPI {
	public static File playerFile;
	public static CentralAPI instance = new CentralAPI();

	public static CentralAPI getInstance() {
		return instance;
	}

	public void addSmeltingPoints(Player player, int points) {
		playerFile = new File(Main.getPlugin().getDataFolder(), "players.yml"); // The file (you can name it what you
																				// want)
		if (!playerFile.exists()) {
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileConfiguration p = YamlConfiguration.loadConfiguration(playerFile);
		if (!((p.getInt(player.getUniqueId().toString()) + points) > 999999999))
			p.set(player.getUniqueId().toString(), p.getInt(player.getUniqueId().toString()) + points);

		try {
			p.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getSmeltingPoints(Player player) {
		int amount = 0;
		playerFile = new File(Main.getPlugin().getDataFolder(), "players.yml"); // The file (you can name it what you
																				// want)
		if (!playerFile.exists()) {
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileConfiguration p = YamlConfiguration.loadConfiguration(playerFile);
		if (p.contains(player.getUniqueId().toString())) {
			amount = p.getInt(player.getUniqueId().toString());
		} else {
			p.set(player.getUniqueId().toString(), 0);
		}
		try {
			p.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}

}
