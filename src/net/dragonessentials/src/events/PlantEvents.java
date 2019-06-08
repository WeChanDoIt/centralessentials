package net.dragonessentials.src.events;

import java.io.File;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

import net.dragonessentials.src.Main;
import net.dragonessentials.src.utils.ItemUtils;
import net.dragonessentials.src.utils.MiscUtils;

public class PlantEvents implements Listener {
	
	private Main plugin;
	public static File main = Main.getPlugin().getDataFolder();
	List<Material> plantBlockTable = MiscUtils.plantBlockTable();
	List<Material> plantCropTable = MiscUtils.plantCropTable();

	@EventHandler
	public void noUproot(PlayerInteractEvent event) {
		if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL)
			event.setCancelled(true);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		if (!ASkyBlockAPI.getInstance().playerIsOnIsland(player)) {
			return;
		}

		if (block.getType().equals(Material.NETHER_WARTS) && item.getType().equals(Material.BLAZE_POWDER) && !item.hasItemMeta()) {
			byte data = block.getData();
			byte newData = (byte) (data + MiscUtils.getRandomNumberFrom(1, 2));
			if (newData > 3) {
				newData = (byte) 3;
			} else {
				player.playEffect(block.getLocation(), Effect.FLAME, 10);
				if (player.getGameMode() != GameMode.CREATIVE) {
					ItemUtils.setAmount(item, 1, player);
				}
			}
			block.setData(newData);
		}

	}
	
	 @EventHandler
	    public void onCropGrow(BlockGrowEvent e) {
	        Island island = ASkyBlockAPI.getInstance().getIslandAt(e.getBlock().getLocation());
	        if (island != null) {
	            //if (island.getFarmingBoosterActive()) {
	                e.getBlock().setData((byte) (e.getBlock().getData() + 1));
	            //}
	        }
	    }

	@EventHandler
	public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		Block crop = player.getLocation().getBlock();
		Block soil = crop.getLocation().subtract(0, 1, 0).getBlock();
		Block netherwart = player.getLocation().add(0, 1, 0).getBlock();

		if (ASkyBlockAPI.getInstance().playerIsOnIsland(player) && player.isSneaking()) {
			for (Material plant : plantCropTable) {
				if ((soil.getType() == Material.SOIL && crop.getType() == Material.AIR)
						|| (netherwart.getType() == Material.SOIL && soil.getType() == Material.AIR)) {
					if (item.getType() == plant && !item.hasItemMeta()) {
						crop.setType(plantBlockTable.get(plantCropTable.indexOf(plant)));
						if (player.getGameMode() != GameMode.CREATIVE) {
							ItemUtils.setAmount(item, 1, player);
						}
					}
				}
			}
			for (Material block : plantBlockTable) {
				if (crop.getType() == block) {
					if (item.getType() == Material.INK_SACK && item.getDurability() == 15 && !item.hasItemMeta()) {
						byte data = crop.getData();
						byte newData = (byte) (data + MiscUtils.getRandomNumberFrom(2, 5));
						if (newData > 7) {
							newData = (byte) 7;
						} else {
							player.playEffect(crop.getLocation().add(0.5, 0.5, 0.5), Effect.HAPPY_VILLAGER, 10);
							if (player.getGameMode() != GameMode.CREATIVE) {
								ItemUtils.setAmount(item, 1, player);
							}
						}
						crop.setData(newData);
					}
				}
			}
			if (crop.getType().equals(Material.SOUL_SAND)) {
				if (netherwart.getType().equals(Material.AIR) && item.getType().equals(Material.NETHER_STALK)
						&& !item.hasItemMeta()) {
					netherwart.setType(Material.NETHER_WARTS);
					if (player.getGameMode() != GameMode.CREATIVE) {
						ItemUtils.setAmount(item, 1, player);
					}
				} else if (netherwart.getType().equals(Material.NETHER_WARTS)
						&& item.getType().equals(Material.BLAZE_POWDER) && !item.hasItemMeta()) {
					byte data = netherwart.getData();
					byte newData = (byte) (data + MiscUtils.getRandomNumberFrom(1, 2));
					if (newData > 3) {
						newData = (byte) 3;
					} else {
						player.playEffect(netherwart.getLocation().add(0.5, 0.5, 0.5), Effect.FLAME, 10);
						if (player.getGameMode() != GameMode.CREATIVE) {
							ItemUtils.setAmount(item, 1, player);
						}
					}
					netherwart.setData(newData);
				}

			}
			player.updateInventory();
		}
	}
}
