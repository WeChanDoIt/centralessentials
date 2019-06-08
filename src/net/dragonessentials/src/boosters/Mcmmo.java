package net.dragonessentials.src.boosters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;

import me.swanis.boosters.booster.Booster;
import net.dragonessentials.src.utils.ChatUtils;

public class Mcmmo implements Booster, Listener {

	@Override
	public List<String> getActivateLore() {
		List<String> actLore = new ArrayList<String>();
		actLore.add(ChatUtils.chat("&7&oThis doubles all McMMO exp received"));
		actLore.add(ChatUtils.chat("&7&oon the server for %minutes% minutes"));
		actLore.add(ChatUtils.chat(""));
		actLore.add(ChatUtils.chat("&3You have: &b%amount%"));
		return actLore;
	}

	@Override
	public List<String> getBoostersLore() {
		List<String> boostLore = new ArrayList<String>();
		boostLore.add(ChatUtils.chat("&7&oThis doubles all McMMO exp received"));
		boostLore.add(ChatUtils.chat("&7&oon the server for %minutes% minutes"));
		boostLore.add(ChatUtils.chat(""));
		boostLore.add(ChatUtils.chat("&3Activated by: &b%starter%"));
		boostLore.add(ChatUtils.chat("&3Remaining time: &b%time%"));
		return boostLore;
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		return "xprate 2 true";
	}

	@Override
	public short getDurability() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getItemName() {
		// TODO Auto-generated method stub
		return ChatUtils.chat("&3&l[!]&3 Booster: &bMcMMO");
	}

	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return Material.IRON_SWORD;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "McMMO";
	}

	@Override
	public String getResetCommand() {
		// TODO Auto-generated method stub
		return "xprate reset";
	}

	@Override
	public int getSlot() {
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public boolean isGlow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
}
