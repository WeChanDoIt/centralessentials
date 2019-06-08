package net.dragonessentials.src;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.swanis.boosters.BoostersAPI;
import net.dragonessentials.src.boosters.Mcmmo;
import net.dragonessentials.src.commands.Annoyed;
import net.dragonessentials.src.commands.Dab;
import net.dragonessentials.src.commands.Discord;
import net.dragonessentials.src.commands.Dub;
import net.dragonessentials.src.commands.Help;
import net.dragonessentials.src.commands.Ism;
import net.dragonessentials.src.commands.L;
import net.dragonessentials.src.commands.Shrug;
import net.dragonessentials.src.commands.Smak;
import net.dragonessentials.src.commands.Triggered;
import net.dragonessentials.src.commands.Yeet;
import net.dragonessentials.src.commands.nv;
import net.dragonessentials.src.customenchants.Glow;
import net.dragonessentials.src.events.CraftEvents;
import net.dragonessentials.src.events.GUIEvents;
import net.dragonessentials.src.events.PlantEvents;
import net.dragonessentials.src.recipes.Recipes;

public class Main extends JavaPlugin implements Listener {

	private static Main plugin;
	public static String prefix;

	public Glow glow = new Glow(501);

	public List<String> challengeList;

	public File challenges;

	public void onEnable() {

		loadEnchantments();
		loadEvents();
		loadBoosters();
		loadCommands();
		loadConfig();
		loadRecipes();

		getLogger().info("Plugin enabled.");
	}
	
	private void loadEvents() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new GUIEvents(), this);
		this.getServer().getPluginManager().registerEvents(new PlantEvents(), this);
		this.getServer().getPluginManager().registerEvents(new CraftEvents(), this);
	}

	private void loadConfig() {
		if (!getConfig().contains("prefix")) {
			getConfig().set("prefix", "&a&l[!]&r");
		}

		if (!getConfig().contains("help")) {
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
			getConfig().set("help", help);
		}

		if (!getConfig().contains("nv.on")) {
			getConfig().set("nv.on", "%prefix%  &7» &fYou have been given night vision!");
		}
		if (!getConfig().contains("nv.off")) {
			getConfig().set("nv.off", "%prefix%  &7» &fNight vision removed!");
		}
		if (!getConfig().contains("discord")) {
			getConfig().set("discord", "%prefix%  &7» &f&nhttps://discord.gg/sZGgxx2");
		}

		prefix = getConfig().getString("prefix");

		saveConfig();
	}
	
	private void loadBoosters() {
		BoostersAPI.getBoosterManager().register(new Mcmmo());
	}

	private void loadCommands() {
		// basic commands
		new nv(this);
		new Help(this);
		new Discord(this);
		new Ism(this);

		// emotes
		plugin.getCommand("annoyed").setExecutor(new Annoyed(this));
		plugin.getCommand("dab").setExecutor(new Dab(this));
		plugin.getCommand("dub").setExecutor(new Dub(this));
		plugin.getCommand("L").setExecutor(new L(this));
		plugin.getCommand("shrug").setExecutor(new Shrug(this));
		plugin.getCommand("smak").setExecutor(new Smak(this));
		plugin.getCommand("triggered").setExecutor(new Triggered(this));
		plugin.getCommand("yeet").setExecutor(new Yeet(this));
	}

	private void loadRecipes() {
		Bukkit.addRecipe(Recipes.sugarcookie());
		Bukkit.addRecipe(Recipes.potatostew());
		Bukkit.addRecipe(Recipes.goldcarrot());
		Bukkit.addRecipe(Recipes.goldmelon());
		Bukkit.addRecipe(Recipes.nethercandy());
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
				Enchantment.registerEnchantment(glow);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		getLogger().info("Plugin disabled.");
	}

	public static Main getInstance() {
		return plugin;
	}

	public static JavaPlugin getPlugin() {
		return plugin;
	}

}
