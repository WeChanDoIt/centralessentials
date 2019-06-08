package net.dragonessentials.src.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.gui.GUIManager;
import net.dragonessentials.src.utils.ChatUtils;

public class Ism implements CommandExecutor {

	private Main plugin;

	public Ism(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ism").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&c[!] Only players may execute this command!"));
			return true;
		}

		Player player = (Player) sender;
		GUIManager.openSettings(player, 0);
		return true;
	}
}
