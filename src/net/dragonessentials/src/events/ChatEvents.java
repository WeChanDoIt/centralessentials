package net.dragonessentials.src.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ItemUtils;

public class ChatEvents implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (Main.getPlugin().renamecheck.containsKey(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		} else {
			return;
		}
		Player player = e.getPlayer();

		ItemStack item = Main.getPlugin().renamecheck.get(player.getUniqueId());
		ItemMeta meta = item.getItemMeta();

		String message = e.getMessage();
		if (e.getMessage().equals("cancel")) {
			player.sendMessage(ChatUtils.chat("&c&l[!] &cYour item has been returned to you."));
			Main.getPlugin().renamecheck.remove(player.getUniqueId());
			if (player.getInventory().firstEmpty() == -1)
				player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
			else
				player.getInventory().addItem(item);
			if (player.getInventory().firstEmpty() == -1)
				player.getLocation().getWorld().dropItemNaturally(player.getLocation(), ItemUtils.getRenameScroll());
			else
				player.getInventory().addItem(ItemUtils.getRenameScroll());
			return;
		}
		message = ChatUtils.chat(message);
		meta.setDisplayName(message);
		item.setItemMeta(meta);
		if (player.getInventory().firstEmpty() == -1)
			player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
		else
			player.getInventory().addItem(item);
		Main.getPlugin().renamecheck.remove(player.getUniqueId());
		player.sendMessage(ChatUtils.chat("&6&l[!] &eYour item has been renamed to " + e.getMessage()));
	}

}
