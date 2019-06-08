package net.dragonessentials.src.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ChatUtils;

public class nv implements CommandExecutor {

	private Main plugin;

	public nv(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("nv").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.chat("&c[!] &7» &fOnly players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;
		if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			String message = plugin.getConfig().getString("nv.off");
			p.sendMessage(ChatUtils.chat(message.replace("%prefix%", plugin.getConfig().getString("prefix"))));
		} else {
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
			String message = plugin.getConfig().getString("nv.on");
			p.sendMessage(ChatUtils.chat(message.replace("%prefix%", plugin.getConfig().getString("prefix"))));
		}
		return true;
	}

}
