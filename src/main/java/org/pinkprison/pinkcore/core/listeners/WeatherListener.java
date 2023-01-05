package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    /**
     * Disable changing weather.
     *
     * @param event {@link WeatherChangeEvent}
     */
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    /**
     * Disable lightning strikes.
     *
     * @param event {@link LightningStrikeEvent}
     */
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        event.setCancelled(true);
    }
}