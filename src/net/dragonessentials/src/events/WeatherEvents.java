package net.dragonessentials.src.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvents implements Listener {
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event){
	  event.setCancelled(event.toWeatherState());
	}
}
