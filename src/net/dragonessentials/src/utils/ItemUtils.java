package net.dragonessentials.src.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.dragonessentials.src.customenchants.SmeltingPickaxe;

public class ItemUtils {

	public static ItemStack getSkullFromUrl(String url) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		if (url == null || url.isEmpty())
			return skull;
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = skullMeta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		profileField.setAccessible(true);
		try {
			profileField.set(skullMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		skull.setItemMeta(skullMeta);
		return skull;
	}

	public static ItemStack getSkullFromName(String name) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setOwner(name);
		skull.setItemMeta(skullMeta);
		return skull;

	}

	public static void removeItemAmount(Material type, int amount, Player player) {
		Inventory inventory = player.getInventory();
		if (amount <= 0)
			return;
		int size = inventory.getSize();
		for (int slot = 0; slot < size; slot++) {
			ItemStack is = inventory.getItem(slot);
			if (is == null)
				continue;
			if (type == is.getType()) {
				int newAmount = is.getAmount() - amount;
				if (newAmount > 0) {
					is.setAmount(newAmount);
					break;
				} else {
					inventory.clear(slot);
					amount = -newAmount;
					if (amount == 0)
						break;
				}
			}
		}
	}

	public static int getAmount(Player player, Material id) {
		if (id == null)
			return 0;
		int amount = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack slot = player.getInventory().getItem(i);
			if (slot == null || !slot.getType().equals(id))
				continue;
			amount += slot.getAmount();
		}
		return amount;
	}

	public static ItemStack getRainbowOrb() {
		ItemStack rainboworb = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta meta = rainboworb.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&cRa&ein&abo&bw &9Or&db"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatUtils.chat("&7Drag and drop this onto the tool/armor"));
		lore.add(ChatUtils.chat("&7of your choice to turn the"));
		lore.add(ChatUtils.chat("&7name into a &cRa&ein&ab&bo&9w&d!"));
		lore.add("");
		lore.add(ChatUtils.chat("&c&lNOTE: &7Once used you will lose this"));
		lore.add(ChatUtils.chat("&7orb and there will be no way of"));
		lore.add(ChatUtils.chat("&7getting it back"));

		meta.setLore(lore);
		rainboworb.setItemMeta(meta);
		return rainboworb;
	}
	
	public static ItemStack getRenameScroll() {
		ItemStack renamescroll = new ItemStack(Material.NAME_TAG);
		ItemMeta meta = renamescroll.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&d&lRename Scroll"));

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatUtils.chat("&7Drag and drop this onto the item"));
		lore.add(ChatUtils.chat("&7of your choice to rename it!"));
		lore.add("");
		lore.add(ChatUtils.chat("&c&lNOTE: &7Once used you will lose this"));
		lore.add(ChatUtils.chat("&7orb and there will be no way of"));
		lore.add(ChatUtils.chat("&7getting it back"));
		
		meta.setLore(lore);
		renamescroll.setItemMeta(meta);
		return renamescroll;
	}

	public static ItemStack getSmeltingPickaxe() {
		ItemStack smeltingpickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta meta = smeltingpickaxe.getItemMeta();
		meta.setDisplayName(ChatUtils.chat("&b&lSmelting Pickaxe"));
		
		meta.addEnchant(Enchantment.DIG_SPEED, 10, true);
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 8, true);
		meta.addEnchant(new SmeltingPickaxe(502), 1, true);

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatUtils.chat(""));
		lore.add(ChatUtils.chat("&7Blocks drop &bSmelted items &7when you break them!"));
		lore.add(ChatUtils.chat("&7Make sure to fill up on smelt points with &b/smeltpick"));

		meta.setLore(lore);
		smeltingpickaxe.setItemMeta(meta);
		return smeltingpickaxe;
	}

	public static String capitalizeFully(String name) {
		if (name != null) {
			if (name.length() > 1) {
				if (name.contains("_")) {
					StringBuilder sbName = new StringBuilder();
					for (String subName : name.split("_"))
						sbName.append(subName.substring(0, 1).toUpperCase() + subName.substring(1).toLowerCase())
								.append(" ");
					return sbName.toString().substring(0, sbName.length() - 1);
				} else {
					return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
				}
			} else {
				return name.toUpperCase();
			}
		} else {
			return "";
		}
	}

	public static String getFriendlyName(Material material) {
		return material == null ? "Air" : getFriendlyName(new ItemStack(material), false);
	}

	private static Class<?> localeClass = null;
	private static Class<?> craftItemStackClass = null, nmsItemStackClass = null, nmsItemClass = null;
	private static String OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
	private static String NMS_PREFIX = OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");

	public static String getFriendlyName(ItemStack itemStack, boolean checkDisplayName) {
		if (itemStack == null || itemStack.getType() == Material.AIR)
			return "Air";
		try {
			if (craftItemStackClass == null)
				craftItemStackClass = Class.forName(OBC_PREFIX + ".inventory.CraftItemStack");
			Method nmsCopyMethod = craftItemStackClass.getMethod("asNMSCopy", ItemStack.class);

			if (nmsItemStackClass == null)
				nmsItemStackClass = Class.forName(NMS_PREFIX + ".ItemStack");
			Object nmsItemStack = nmsCopyMethod.invoke(null, itemStack);

			Object itemName = null;
			if (checkDisplayName) {
				Method getNameMethod = nmsItemStackClass.getMethod("getName");
				itemName = getNameMethod.invoke(nmsItemStack);
			} else {
				Method getItemMethod = nmsItemStackClass.getMethod("getItem");
				Object nmsItem = getItemMethod.invoke(nmsItemStack);

				if (nmsItemClass == null)
					nmsItemClass = Class.forName(NMS_PREFIX + ".Item");

				Method getNameMethod = nmsItemClass.getMethod("getName");
				Object localItemName = getNameMethod.invoke(nmsItem);

				if (localeClass == null)
					localeClass = Class.forName(NMS_PREFIX + ".LocaleI18n");
				Method getLocaleMethod = localeClass.getMethod("get", String.class);

				Object localeString = localItemName == null ? "" : getLocaleMethod.invoke(null, localItemName);
				itemName = ("" + getLocaleMethod.invoke(null, localeString.toString() + ".name")).trim();
			}
			return itemName != null ? itemName.toString()
					: capitalizeFully(itemStack.getType().name().replace("_", " ").toLowerCase());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return capitalizeFully(itemStack.getType().name().replace("_", " ").toLowerCase());
	}

}
