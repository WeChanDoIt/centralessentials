package net.dragonessentials.src.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.customenchants.Rainbow;
import net.dragonessentials.src.customenchants.Rename;
import net.dragonessentials.src.customenchants.SmeltingPickaxe;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ItemUtils;
import net.dragonessentials.src.utils.MiscUtils;

public class DragAndDropEvents implements Listener {

	@EventHandler
	public void BlackMarketDragDropEvent(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		ItemStack cursor = event.getCursor();
		if (event.getClick().isLeftClick() && cursor.getAmount() == 1
				&& (MiscUtils.toolTable().contains(Material.getMaterial(item.getType().toString()))
						|| MiscUtils.armorTable().contains(Material.getMaterial(item.getType().toString()))
						|| MiscUtils.weaponTable().contains(Material.getMaterial(item.getType().toString())))
				&& !item.containsEnchantment(new Rainbow(501)) && !item.containsEnchantment(new SmeltingPickaxe(502))
				&& !item.containsEnchantment(new Rename(503))) {
			if (cursor.isSimilar(ItemUtils.getRainbowOrb())) {
				if (player.getGameMode().equals(GameMode.CREATIVE)) { // conflicts with cratesreloaded
					player.sendMessage(
							ChatUtils.chat("&c&l[!] &cYou cannot be in creative mode when applying a rainbow orb!"));
				} else if (!item.equals(null) && !cursor.equals(null)) { // applies the orb
					event.setCursor(null);
					ItemStack item2 = new ItemStack(item.getType(), item.getAmount(), item.getDurability());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatUtils.chat(ChatUtils.rainbow(ItemUtils.getFriendlyName(item, true))));
					item2.setItemMeta(meta);
					item2.addUnsafeEnchantment(new Rainbow(501), 1);
					event.setCancelled(true);
					player.getInventory().setItem(event.getSlot(), item2);
				} else {
					event.setCancelled(true); // event is triggered 3 times so this is to delete the item
				}
			} else if (cursor.isSimilar(ItemUtils.getRenameScroll())) {
				if (player.getGameMode().equals(GameMode.CREATIVE)) { // conflicts with cratesreloaded
					player.sendMessage(
							ChatUtils.chat("&c&l[!] &cYou cannot be in creative mode when applying a rename scroll!"));
				} else if (!item.equals(null) && !cursor.equals(null)) {
					event.setCursor(null);
					player.sendMessage(ChatUtils.chat("&6&l[!] &eType the new name for your item in chat: &7(Type cancel to cancel)"));
					Main.getPlugin().renamecheck.put(player.getUniqueId(), item);
					player.getInventory().setItem(event.getSlot(), null);
					event.setCancelled(true);
				}
			}
		}
	}

}
