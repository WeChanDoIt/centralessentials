package net.dragonessentials.src.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dragonessentials.src.CentralAPI;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ItemUtils;
import net.dragonessentials.src.utils.MiscUtils;

public class SmeltPickGUI {
	public FileConfiguration p;

	private static SmeltPickGUI instance = new SmeltPickGUI();

	public static SmeltPickGUI getInstance() {
		return instance;
	}

	public void open(Player player) {
		Inventory gui = Bukkit.createInventory(player, 27,
				ChatColor.translateAlternateColorCodes('&', "&8&lADD SMELTING RESOURCES"));

		int count = 10;
		for (Material id : MiscUtils.fuelTable().keySet()) {
			int points = MiscUtils.fuelTable().get(id);
			ItemStack fuel = new ItemStack(id, 1);
			ItemMeta fuelMeta = fuel.getItemMeta();
			fuelMeta.setDisplayName(ChatUtils.chat("&b&l" + ItemUtils.getFriendlyName(id)));

			List<String> lore = new ArrayList<>();
			if (player.getInventory().contains(id)) {
				int amount = ItemUtils.getAmount(player, id);
				lore.add(ChatUtils.chat("&7Click to trade one for &b&l" + Integer.toString(points) + "&7 points!"));
				lore.add(ChatUtils.chat("&7Right-click to trade &b" + Integer.toString(amount) + " &7for &b"
						+ Integer.toString(amount * points) + "&7 points!"));
			} else {
				lore.add(ChatUtils.chat("&7You don't have any of these!"));
			}
			lore.add("");
			lore.add(ChatUtils.chat("&7You have &b&l" + CentralAPI.getInstance().getSmeltingPoints(player) + " &7points!"));
			fuelMeta.setLore(lore);
			fuel.setItemMeta(fuelMeta);
			gui.setItem(count, fuel);
			count++;
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

}
