package net.dragonessentials.src.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.gui.SmeltPickGUI;
import net.dragonessentials.src.utils.ChatUtils;

public class SmeltPick implements CommandExecutor {

	private Main plugin;

	public File playerFile;

	public SmeltPick(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("smeltpick").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&c[!] Only players may execute this command!"));
			return true;
		}
		Player player = (Player) sender;
		SmeltPickGUI.getInstance().open(player);

		return true;
	}
}
