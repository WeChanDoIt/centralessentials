package net.dragonessentials.src.events;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.gui.GUIManager;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ConfigUtils;

public class GUIEvents implements Listener {

	private Main plugin;
	public static File main = Main.getPlugin().getDataFolder();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		try {
			Player player = (Player) event.getWhoClicked();
			UUID playerUUID = player.getUniqueId();
			UUID owner;
			List<UUID> members = ASkyBlockAPI.getInstance().getTeamMembers(playerUUID);
			ConfigUtils.playerSettings(player.getUniqueId());
			File UUID = new File(main, "playersettings.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(UUID);

			if (ASkyBlockAPI.getInstance().hasIsland(playerUUID)) {
				owner = playerUUID;
			} else {
				owner = ASkyBlockAPI.getInstance().getTeamLeader(playerUUID);
			}
			members.add(owner);
			for (UUID member : members) {
				String memberName = Bukkit.getOfflinePlayer(member).getName();
				if (event.getClickedInventory() != null
						&& event.getClickedInventory().getTitle()
								.equals(ChatColor.translateAlternateColorCodes('&', "&2&l[!] &2Island Management"))
						|| event.getClickedInventory().getTitle()
								.equals(ChatColor.translateAlternateColorCodes('&', "&2&l[!] &2Island Members"))
						|| event.getClickedInventory().getTitle().equals(
								ChatColor.translateAlternateColorCodes('&', "&2&l[!] &2Managing " + memberName))) {
					event.setCancelled(true);
					if (event.getCurrentItem().hasItemMeta()) {
						if (event.getCurrentItem().getItemMeta().getDisplayName()
								.equals(ChatUtils.chat("&2&l[!] &2Manage &aMember &2Permissions"))) {
							GUIManager.openIslandMemberList(player, 1);
						}
						if (event.getCurrentItem().getItemMeta().getDisplayName()
								.equals(ChatUtils.chat("&2&l[!] &2Manage &a" + memberName))) {
							GUIManager.openPlayerIslandSettings(player, member);
						}
					}
				}

			}
		} catch (Exception e) {

		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ConfigUtils.playerSettings(player.getUniqueId());

	}
}
