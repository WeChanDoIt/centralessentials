package net.dragonessentials.src;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.swanis.boosters.BoostersAPI;
import net.dragonessentials.src.boosters.Mcmmo;
import net.dragonessentials.src.commands.Centralitems;
import net.dragonessentials.src.commands.Discord;
import net.dragonessentials.src.commands.Fix;
import net.dragonessentials.src.commands.Help;
import net.dragonessentials.src.commands.SmeltPick;
import net.dragonessentials.src.commands.nv;
import net.dragonessentials.src.commands.emotes.Annoyed;
import net.dragonessentials.src.commands.emotes.Dab;
import net.dragonessentials.src.commands.emotes.Dub;
import net.dragonessentials.src.commands.emotes.L;
import net.dragonessentials.src.commands.emotes.Shrug;
import net.dragonessentials.src.commands.emotes.Smak;
import net.dragonessentials.src.commands.emotes.Triggered;
import net.dragonessentials.src.commands.emotes.Yeet;
import net.dragonessentials.src.customenchants.Rainbow;
import net.dragonessentials.src.customenchants.Rename;
import net.dragonessentials.src.customenchants.SmeltingPickaxe;
import net.dragonessentials.src.events.ChatEvents;
import net.dragonessentials.src.events.DragAndDropEvents;
import net.dragonessentials.src.events.GUIEvents;
import net.dragonessentials.src.events.PlantEvents;
import net.dragonessentials.src.events.SmeltingPickaxeEvents;
import net.dragonessentials.src.events.WeatherEvents;

public class Main extends JavaPlugin implements Listener {

	private static Main plugin;

	public Rainbow rainbow = new Rainbow(501);
	public SmeltingPickaxe smelt = new SmeltingPickaxe(502);
	public Rename rename = new Rename(503);

	public HashMap<UUID, Integer> cdtime = new HashMap<UUID, Integer>();
	public HashMap<UUID, Integer> fixcdtime = new HashMap<UUID, Integer>();
	public HashMap<UUID, ItemStack> renamecheck = new HashMap<UUID, ItemStack>();

	public void onEnable() {

		plugin = this;

		loadEnchantments();
		loadEvents();
		loadBoosters();
		loadCommands();
		loadConfig();
		loadRecipes();
		runnablerunner();
		setUpDirectories();

		getLogger().info("Plugin enabled.");
	}

	public void runnablerunner() {
		new BukkitRunnable() {

			@Override
			public void run() {

				if (cdtime.isEmpty()) {
					return;
				}

				for (UUID uuid : cdtime.keySet()) {
					int timeleft = cdtime.get(uuid);
					if (timeleft <= 0)
						cdtime.remove(uuid);
					else
						cdtime.put(uuid, timeleft - 1);

				}
				for (UUID uuid : fixcdtime.keySet()) {
					int fixtimeleft = fixcdtime.get(uuid);
					if (fixtimeleft <= 0)
						cdtime.remove(uuid);
					else
						cdtime.put(uuid, fixtimeleft - 1);
				}

			}

		}.runTaskTimer(this, 0, 20);
	}

	private void loadEvents() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new ChatEvents(), this);
		this.getServer().getPluginManager().registerEvents(new DragAndDropEvents(), this);
		this.getServer().getPluginManager().registerEvents(new GUIEvents(), this);
		this.getServer().getPluginManager().registerEvents(new SmeltingPickaxeEvents(), this);
		this.getServer().getPluginManager().registerEvents(new PlantEvents(), this);
		this.getServer().getPluginManager().registerEvents(new WeatherEvents(), this);

	}

	private void loadBoosters() {
		BoostersAPI.getBoosterManager().register(new Mcmmo());
	}

	private void loadCommands() {
		// basic commands
		new nv(this);
		new Help(this);
		new Discord(this);
		new Centralitems(this);
		new SmeltPick(this);
		new Fix(this);

		// emotes
		new Annoyed(this);
		new Dab(this);
		new Dub(this);
		new L(this);
		new Shrug(this);
		new Smak(this);
		new Triggered(this);
		new Yeet(this);

	}

	private void loadRecipes() {
		Iterator<Recipe> it = getServer().recipeIterator();
		Recipe recipe;
		while (it.hasNext()) {
			recipe = it.next();
			if (recipe != null && recipe.getResult().getType() == Material.HOPPER) {
				it.remove();
			}
		}
	}

	private void loadEnchantments() {
		try {
			try {
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Enchantment.registerEnchantment(rainbow);
				Enchantment.registerEnchantment(smelt);
				Enchantment.registerEnchantment(rename);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadConfig() {
		try {
			if (!plugin.getConfig().contains("prefix")) {
				plugin.getConfig().set("prefix", "&a&l[!]&r");
			}

			if (!plugin.getConfig().contains("help")) {
				List<String> help = new ArrayList<String>();
				help.add("&a&l*------- DRAGON&b&lMC &a&l-------*");
				help.add("%prefix% &a/island help &7» List of all island commands.");
				help.add("%prefix% &a/warps &7» List all warps available.");
				help.add("%prefix% &a/kits &7» List all kits available.");
				help.add("%prefix% &a/auction &7» Auction your items.");
				help.add("%prefix% &a/buy &7» Lists all packages you can purchase.");
				help.add("%prefix% &a/trade <name> &7» Trade with another player.");
				help.add("%prefix% &a/shop &7» Buy/Sell items in the shop.");
				help.add("%prefix% &a/transfer &7» Buy items in the shop with mobcoins");
				help.add("&a&l*------------------------*");
				help.add("%prefix% &aWebsite: &7Coming Soon!");
				help.add("%prefix% &aWebstore: &7&nhttp://OfficialDragonMC.buycraft.net");
				plugin.getConfig().set("help", help);
			}

			if (!plugin.getConfig().contains("nv.on")) {
				plugin.getConfig().set("nv.on", "%prefix%  &7» &fYou have been given night vision!");
			}
			if (!plugin.getConfig().contains("nv.off")) {
				plugin.getConfig().set("nv.off", "%prefix%  &7» &fNight vision removed!");
			}
			if (!plugin.getConfig().contains("discord")) {
				plugin.getConfig().set("discord", "%prefix%  &7» &f&nhttps://discord.gg/sZGgxx2");
			}

			plugin.saveConfig();
		} catch (Exception e) {
		}
	}

	private void setUpDirectories() {
		File players = new File(this.getDataFolder().getAbsolutePath(), "players.yml");

		if (!players.exists()) {
			try {
				players.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onDisable() {
		getLogger().info("Plugin disabled.");
	}

	public static Main getPlugin() {
		return plugin;
	}

}
