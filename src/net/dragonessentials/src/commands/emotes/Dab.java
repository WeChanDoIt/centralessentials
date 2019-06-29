package net.dragonessentials.src.commands.emotes;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;

public class Dab implements CommandExecutor {
	private Main plugin;

	public Dab(Main Main) {
		this.plugin = Main;
		plugin.getCommand("dab").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may execute this command!");
			return true;
		}

		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();

		if (!plugin.cdtime.containsKey(uuid)) {
			if (p.hasPermission("centralessentials.dab")) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.sendMessage(ChatUtils.chat("&b" + p.getName() + "&7 dabs &b&l<o/"));

				}
				plugin.cdtime.put(uuid, 120);

			} else {
				p.sendMessage(ChatUtils.chat("&c&l[!] &cYou do not have permission to use this command!"));
			}
		} else {
			p.sendMessage(ChatUtils.chat("&c&l[!] &cYou have " + plugin.cdtime.get(uuid)
			+ " seconds left until you can put an emote in chat!"));
		}

		return true;
	}
}
