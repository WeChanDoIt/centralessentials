package net.dragonessentials.src.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;

public class Discord implements CommandExecutor {

	private Main plugin;

	public Discord(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("discord").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&c[!] Only players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;
		String message = plugin.getConfig().getString("discord");
		p.sendMessage(ChatUtils.chat(message.replace("%prefix%", plugin.getConfig().getString("prefix"))));
		return true;
	}
}
