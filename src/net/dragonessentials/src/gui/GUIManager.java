package net.dragonessentials.src.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ConfigUtils;

public class GUIManager {

	private Main plugin;
	public static File main = Main.getPlugin().getDataFolder();

	public static void openSettings(Player player, int i) {

		UUID playerUUID = player.getUniqueId();

		UUID owner = ASkyBlockAPI.getInstance().getTeamLeader(playerUUID);
		List<UUID> members = ASkyBlockAPI.getInstance().getTeamMembers(playerUUID);

		Inventory gui = Bukkit.createInventory(player, 27,
				ChatColor.translateAlternateColorCodes('&', "&2&l[!] &2Island Management"));

		ItemStack playerskull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta playerskullMeta = (SkullMeta) playerskull.getItemMeta();
		playerskullMeta.setOwner(player.getName());
		playerskullMeta.setDisplayName(ChatUtils.chat("&2&l[!] &2Manage &aMember &2Permissions"));

		playerskull.setItemMeta(playerskullMeta);

		gui.setItem(11, playerskull);

		ItemStack global = new ItemStack(Material.EMPTY_MAP, 1);
		ItemMeta globalMeta = global.getItemMeta();
		globalMeta.setDisplayName(ChatUtils.chat("&2&l[!] &2Manage &aGlobal &2Actions"));

		global.setItemMeta(globalMeta);

		gui.setItem(15, global);

		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);

		ItemMeta fillerMeta = filler.getItemMeta();
		fillerMeta.setDisplayName(" ");
		filler.setItemMeta(fillerMeta);

		int x = 0;

		for (ItemStack itemStack : gui.getContents()) {
			if (itemStack == null)
				x++;
		}

		for (int j = 0; j < x; j++) {
			gui.setItem(gui.firstEmpty(), filler);
		}

		player.openInventory(gui);
		return;
	}

	public static void openIslandMemberList(Player player, int i) {

		UUID playerUUID = player.getUniqueId();
		UUID owner;

		if (ASkyBlockAPI.getInstance().hasIsland(playerUUID)) {
			owner = playerUUID;
		} else {
			owner = ASkyBlockAPI.getInstance().getTeamLeader(playerUUID);
		}
		List<UUID> members = ASkyBlockAPI.getInstance().getTeamMembers(playerUUID);
		members.add(owner);

		Inventory gui = Bukkit.createInventory(player, 27,
				ChatColor.translateAlternateColorCodes('&', "&2&l[!] &2Island Members"));
		Integer count = 0;
		for (UUID member : members) {
			count += 1;

			List<String> lore = new ArrayList<String>();

			String memberName = Bukkit.getOfflinePlayer(member).getName();

			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			skullMeta.setOwner(memberName);

			skullMeta.setDisplayName(ChatUtils.chat("&2&l[!] &2Manage &a" + memberName));

			lore.add(ChatUtils.chat("&7Click to manage this"));
			lore.add(ChatUtils.chat("&7player's permissions"));

			skullMeta.setLore(lore);

			skull.setItemMeta(skullMeta);
			gui.setItem(count - 1, skull);

		}

		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);

		ItemMeta fillerMeta = filler.getItemMeta();
		fillerMeta.setDisplayName(" ");
		filler.setItemMeta(fillerMeta);

		int x = 0;

		for (ItemStack itemStack : gui.getContents()) {
			if (itemStack == null)
				x++;
		}

		for (int j = 0; j < x; j++) {
			gui.setItem(gui.firstEmpty(), filler);
		}

		player.openInventory(gui);
		return;
	}

	public static void openPlayerIslandSettings(Player player, UUID member) {

		File UUID = new File(main, "playersettings.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(UUID);
		UUID playerUUID = player.getUniqueId();
		String memberName = Bukkit.getOfflinePlayer(member).getName();
		UUID owner;
		List<String> lore = new ArrayList<String>();
		ItemStack allowed = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
		ItemStack denied = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);

		if (ASkyBlockAPI.getInstance().hasIsland(playerUUID)) {
			owner = playerUUID;
		} else {
			owner = ASkyBlockAPI.getInstance().getTeamLeader(playerUUID);
		}
		List<UUID> members = ASkyBlockAPI.getInstance().getTeamMembers(playerUUID);
		members.add(owner);

		for (UUID m : members) {
			ConfigUtils.playerSettings(m);
		}

		Inventory gui = Bukkit.createInventory(player, 27,
				ChatColor.translateAlternateColorCodes('&', "&2&l[!] &2Managing " + memberName));
		
		// break spawners
		
		ItemStack breakspawner = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
		lore = new ArrayList<String>();
		lore.add(ChatUtils.chat("&7Current State:"));
		if (config.getBoolean(member + ".settings.breakspawner")) {
			breakspawner = allowed;
			lore.add(ChatUtils.chat("&a&lALLOWED"));
		} else {
			breakspawner = denied;
			lore.add(ChatUtils.chat("&4&lDENIED"));
		}

		ItemMeta breakspawnerMeta = breakspawner.getItemMeta();
		
		breakspawnerMeta.setLore(lore);
		breakspawnerMeta.setDisplayName(ChatUtils.chat("&2&l[!] &2Permission: &aBreak Spawners"));
		breakspawner.setItemMeta(breakspawnerMeta);

		gui.setItem(9, breakspawner);

		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);

		ItemMeta fillerMeta = filler.getItemMeta();
		fillerMeta.setDisplayName(" ");
		filler.setItemMeta(fillerMeta);

		int x = 0;

		for (ItemStack itemStack : gui.getContents()) {
			if (itemStack == null)
				x++;
		}

		for (int j = 0; j < x; j++) {
			gui.setItem(gui.firstEmpty(), filler);
		}

		player.openInventory(gui);
		return;
	}

}
