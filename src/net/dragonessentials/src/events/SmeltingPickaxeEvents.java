package net.dragonessentials.src.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.dragonessentials.src.CentralAPI;
import net.dragonessentials.src.customenchants.SmeltingPickaxe;
import net.dragonessentials.src.utils.ChatUtils;
import net.dragonessentials.src.utils.MiscUtils;

public class SmeltingPickaxeEvents implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		int fortune = player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
		int number = MiscUtils.getRandomNumberFrom(1, 1 + fortune);
		if (player.getItemInHand().containsEnchantment(new SmeltingPickaxe(502))) {
			for (Material id : MiscUtils.smeltTable().keySet()) {
				if (block.getType().equals(id)) {
					if (!((CentralAPI.getInstance().getSmeltingPoints(player) - 1) < 0)) {

						Material item = MiscUtils.smeltTable().get(id);
						event.setCancelled(true);
						block.setType(Material.AIR);
						;
						block.getWorld().dropItem(block.getLocation(), new ItemStack(item, number));
						CentralAPI.getInstance().addSmeltingPoints(player, -1);
					} else {
						player.sendMessage(
								ChatUtils.chat("&c&l[!] &cYou ran out of smelting points! Add more with /smeltpick!"));
					}

				}
			}
		}

	}

}
