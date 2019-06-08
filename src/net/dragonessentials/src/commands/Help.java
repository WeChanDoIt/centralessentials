package net.dragonessentials.src.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;

public class Help implements CommandExecutor {

	private Main plugin;

	public Help(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("help").setExecutor(this);
	}

	@SuppressWarnings("unchecked")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&c[!] &7» &fOnly players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;
		List<String> help = (List<String>) plugin.getConfig().getList("help");
		for (String message : help) {
			p.sendMessage(ChatUtils.chat(message.replace("%prefix%", plugin.getConfig().getString("prefix"))));
		}
		return true;
	}
}
