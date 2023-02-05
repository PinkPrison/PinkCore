package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.core.config.Loader;

public class WeatherListener implements Listener {

    private final boolean allowWeatherChanges;
    private final boolean allowLightningStrikes;

    public WeatherListener(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.allowWeatherChanges = loader.allowWeatherChanges();
        this.allowLightningStrikes = loader.allowLightningStrikes();
    }

    /**
     * Disable changing weather.
     *
     * @param event {@link WeatherChangeEvent}
     */
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (!allowWeatherChanges) {
            event.setCancelled(true);
        }
    }

    /**
     * Disable lightning strikes.
     *
     * @param event {@link LightningStrikeEvent}
     */
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        if (!allowLightningStrikes) {
            event.setCancelled(true);
        }
    }
}