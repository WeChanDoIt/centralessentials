package net.dragonessentials.src.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;

public class Triggered implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;

	public Triggered(Main Main) {
		this.plugin = Main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may execute this command!");
			return true;
		}

		Player p = (Player) sender;

		if (p.hasPermission("onworld.triggered")) {
			for (Player player : Bukkit.getOnlinePlayers()) {
			    player.sendMessage(ChatUtils.chat("&b" + p.getName() + "&7 is triggered &b&lಠ_ಠ"));
			    return true;
			}

		}

		p.sendMessage(ChatUtils.chat("&c&l[!] &cYou do not have permission to use this command!"));

		return true;
	}
}
