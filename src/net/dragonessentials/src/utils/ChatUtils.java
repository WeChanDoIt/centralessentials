package net.dragonessentials.src.utils;

import java.util.List;

import org.bukkit.ChatColor;

public class ChatUtils {

	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static String rainbow(String s) {
		s = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', s)); // remove all instances of & (color)

		String newstring = "";
		String[] chars = s.split("");
		int count = 0;
		List<String> rainbowchars = MiscUtils.rainbowList();
		for (String character : chars) {
			if (!character.equals(" ")) {
				newstring += rainbowchars.get(count);
				if (!(count + 1 >= rainbowchars.size()))
					count += 1;
				else
					count = 0;
			}
			newstring += character;
		}
		return newstring;
	}

}
