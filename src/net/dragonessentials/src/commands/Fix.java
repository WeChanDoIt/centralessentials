package net.dragonessentials.src.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ItemUtils;
import net.dragonessentials.src.utils.MiscUtils;

public class Fix implements CommandExecutor {

	private Main plugin;

	public Fix(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("fix").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&c&l[!] &cOnly players may execute this command!"));
			return true;
		}

		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();
		if (!plugin.cdtime.containsKey(uuid) || player.isOp()) {
			if (args.length == 0) {
				if (player.hasPermission("centralessentials.fix") || player.isOp()) {
					plugin.fixcdtime.put(uuid, 120);
					ItemStack item = player.getItemInHand();
					if (MiscUtils.weaponTable().contains(item.getType())
							|| MiscUtils.armorTable().contains(item.getType())
							|| MiscUtils.toolTable().contains(item.getType()) && !(item.getDurability() == 0)) {
						item.setDurability((short) 0);
						player.sendMessage(ChatUtils
								.chat("&6&l[!] &eYou have repaired your " + ItemUtils.getFriendlyName(item, true)));
					} else {
						player.sendMessage(ChatUtils
								.chat("&c&l[!] " + ItemUtils.getFriendlyName(item, true) + "&c cannot be repaired!"));
					}
				} else {
					player.sendMessage(ChatUtils.chat("&c&l[!] &cYou need permission to use this command!"));
				}
			} else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
				plugin.fixcdtime.put(uuid, 120);
				if (player.hasPermission("centralessentials.fixall") || player.isOp()) {
					player.sendMessage(ChatUtils
							.chat("&6&l[!] &eYou have repaired every tool/weapon/armor item in your inventory!"));
					for (ItemStack item : player.getInventory()) {
						if (item == null)
							continue;
						if (MiscUtils.weaponTable().contains(item.getType())
								|| MiscUtils.armorTable().contains(item.getType())
								|| MiscUtils.toolTable().contains(item.getType()) && !(item.getDurability() == 0)) {
							item.setDurability((short) 0);
							player.sendMessage(ChatUtils
									.chat("&6&l[!] &eYou have repaired your " + ItemUtils.getFriendlyName(item, true)));
						}
					}
				} else {
					player.sendMessage(ChatUtils.chat("&c&l[!] &cYou need permission to use this command!"));
				}
			}

		} else {
			player.sendMessage(ChatUtils.chat("&c&l[!] &cYou have " + plugin.fixcdtime.get(uuid)
					+ " seconds left until you can fix your tools/armor/weapons!"));
		}
		return true;
	}
}
