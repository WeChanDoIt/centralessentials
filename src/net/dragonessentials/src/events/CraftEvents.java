package net.dragonessentials.src.events;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import net.dragonessentials.src.recipes.Recipes;
import net.dragonessentials.src.utils.RecipeUtils;

public class CraftEvents implements Listener {

	@EventHandler
	public void preCraft(PrepareItemCraftEvent event) {
		if (RecipeUtils.areEqual(event.getRecipe(), Recipes.sugarcookie())) {
			HumanEntity player = event.getView().getPlayer();
			if (!(player.hasPermission("dragonmc.craft.sugarcookie"))) {
				event.getInventory().setResult(null);
			}
		} else if (RecipeUtils.areEqual(event.getRecipe(), Recipes.potatostew())) {
			HumanEntity player = event.getView().getPlayer();
			if (!(player.hasPermission("dragonmc.craft.potatostew"))) {
				event.getInventory().setResult(null);
			}
		} else if (RecipeUtils.areEqual(event.getRecipe(), Recipes.goldcarrot())) {
			HumanEntity player = event.getView().getPlayer();
			if (!(player.hasPermission("dragonmc.craft.goldcarrot"))) {
				event.getInventory().setResult(null);
			}
		} else if (RecipeUtils.areEqual(event.getRecipe(), Recipes.goldmelon())) {
			HumanEntity player = event.getView().getPlayer();
			if (!(player.hasPermission("dragonmc.craft.goldmelon"))) {
				event.getInventory().setResult(null);
			}
		} else if (RecipeUtils.areEqual(event.getRecipe(), Recipes.nethercandy())) {
			HumanEntity player = event.getView().getPlayer();
			if (!(player.hasPermission("dragonmc.craft.nethercandy"))) {
				event.getInventory().setResult(null);
			}
		}
	}

}
