package net.dragonessentials.src.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.ItemUtils;

public class Centralitems implements CommandExecutor {

	private Main plugin;

	public Centralitems(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("centralitems").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatUtils.chat("&c&l[!] &cBlackmarket Items: rainboworb, smeltpick, renamescroll"));
			} else if (args.length == 3) {
				Player target = Bukkit.getPlayer(args[1]);
				if (args[0].equalsIgnoreCase("give") && (!(target == null))) {
					if (args[2].equalsIgnoreCase("rainboworb")) {
						target.getInventory().addItem(ItemUtils.getRainbowOrb());
						sender.sendMessage(
								ChatUtils.chat("&6&l[!] &eYou gave " + target.getName() + " &ea rainbow orb!"));
						target.sendMessage(
								ChatUtils.chat("&6&l[!] &eCONSOLE has given you a rainbow orb!"));
					} else if (args[2].equalsIgnoreCase("smeltpick")) {
						target.getInventory().addItem(ItemUtils.getSmeltingPickaxe());
						sender.sendMessage(
								ChatUtils.chat("&6&l[!] &eYou gave " + target.getName() + " &ea smelting pickaxe!"));
						target.sendMessage(
								ChatUtils.chat("&6&l[!] &eCONSOLE has given you a smelting pickaxe!"));
					} else if (args[2].equalsIgnoreCase("renamescroll")) {
						target.getInventory().addItem(ItemUtils.getRenameScroll());
						sender.sendMessage(
								ChatUtils.chat("&6&l[!] &eYou gave " + target.getName() + " &ea rename scroll!"));
						target.sendMessage(
								ChatUtils.chat("&6&l[!] &eCONSOLE has given you a rename scroll!"));
					} else {
						sender.sendMessage(ChatUtils.chat("&c&l[!] &cBlackmarket Items: rainboworb, smeltpick, renamescroll"));
					}
				} else {
					sender.sendMessage(
							ChatUtils.chat("&c&l[!] &cDo /blackmarket give <player> <item> to give a player 1 <item>."));
					sender.sendMessage(ChatUtils.chat("&c&l[!] &cBlackmarket Items: rainboworb, smeltpick, renamescroll"));
				}
			} else
				sender.sendMessage(ChatUtils.chat("&c&l[!] &cPlayer is not defined or the command was undefined!"));
			return true;
		}

		Player player = (Player) sender;
		if (player.hasPermission("centralessentials.admin.specialitems") || player.isOp()) {

			if (args.length == 0) {
				player.sendMessage(ChatUtils.chat("&c&l[!] &cBlackmarket Items: rainboworb, smeltpick, renamescroll"));
			} else if (args.length == 3) {
				Player target = Bukkit.getPlayer(args[1]);
				if (args[0].equalsIgnoreCase("give") && (!(target == null))) {
					if (args[2].equalsIgnoreCase("rainboworb")) {
						target.getInventory().addItem(ItemUtils.getRainbowOrb());
						player.sendMessage(
								ChatUtils.chat("&6&l[!] &eYou gave " + target.getName() + " &ea rainbow orb!"));
						target.sendMessage(
								ChatUtils.chat("&6&l[!] &e" + player.getName() + " &ehas given you a rainbow orb!"));
					} else if (args[2].equalsIgnoreCase("smeltpick")) {
						target.getInventory().addItem(ItemUtils.getSmeltingPickaxe());
						player.sendMessage(
								ChatUtils.chat("&6&l[!] &eYou gave " + target.getName() + " &ea smelting pickaxe!"));
						target.sendMessage(
								ChatUtils.chat("&6&l[!] &e" + player.getName() + " &ehas given you a smelting pickaxe!"));
					} else if (args[2].equalsIgnoreCase("renamescroll")) {
						target.getInventory().addItem(ItemUtils.getRenameScroll());
						player.sendMessage(
								ChatUtils.chat("&6&l[!] &eYou gave " + target.getName() + " &ea rename scroll!"));
						target.sendMessage(
								ChatUtils.chat("&6&l[!] &e" + player.getName() + " &ehas given you a rename scroll!"));
					} else {
						player.sendMessage(ChatUtils.chat("&c&l[!] &cBlackmarket Items: rainboworb, smeltpick, renamescroll"));
					}
				} else {
					player.sendMessage(
							ChatUtils.chat("&c&l[!] &cDo /blackmarket give <player> <item> to give a player 1 <item>."));
					player.sendMessage(ChatUtils.chat("&c&l[!] &cBlackmarket Items: rainboworb, smeltpick, renamescroll"));
				}
			} else
				player.sendMessage(ChatUtils.chat("&c&l[!] &cPlayer is not defined or the command was undefined!"));
		} else {
			player.sendMessage(ChatUtils.chat("&c&l[!] &cYou need permission to use this command!"));
		}
		return true;
	}
}
