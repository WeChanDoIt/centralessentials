package net.dragonessentials.src.events;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.dragonessentials.src.CentralAPI;
import net.dragonessentials.src.gui.SmeltPickGUI;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ItemUtils;
import net.dragonessentials.src.utils.MiscUtils;

public class GUIEvents implements Listener {

	public static File playerFile;

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		try {
			if (event.getClickedInventory() != null && event.getClickedInventory().getTitle()
					.equals(ChatColor.translateAlternateColorCodes('&', "&8&lADD SMELTING RESOURCES"))) {
				event.setCancelled(true);
				if (event.getCurrentItem().hasItemMeta()) {
					if (event.getCurrentItem().getItemMeta().hasLore()) {
						if (!event.getCurrentItem().getItemMeta().getLore()
								.contains(ChatUtils.chat("&7You don't have any of these!"))) {
							Material id = event.getCurrentItem().getType();
							Player player = (Player) event.getWhoClicked();
							if (event.isRightClick()) {
								// add points
								event.getWhoClicked()
										.sendMessage(ChatUtils.chat("&6&l[!] &eAdded &b"
												+ (MiscUtils.fuelTable().get(id) * ItemUtils.getAmount(player, id))
												+ " &epoints!"));
								if (!(ItemUtils.getAmount(player, id) == 0))
									CentralAPI.getInstance().addSmeltingPoints(player,
											MiscUtils.fuelTable().get(id) * ItemUtils.getAmount(player, id));
								ItemUtils.removeItemAmount(id, ItemUtils.getAmount(player, id), player);

							} else if (event.isLeftClick()) {
								// add points
								event.getWhoClicked().sendMessage(ChatUtils
										.chat("&6&l[!] &eAdded &b" + MiscUtils.fuelTable().get(id) + " &epoints!"));
								CentralAPI.getInstance().addSmeltingPoints(player, MiscUtils.fuelTable().get(id));
								ItemUtils.removeItemAmount(id, 1, player);
							}
							player.updateInventory();
							SmeltPickGUI.getInstance().open(player);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
