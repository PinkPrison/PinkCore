package org.pinkprison.pinkcore.core.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.core.task.AutoBroadcastTask;

import java.util.*;

public class Loader {

    private final PinkCore plugin;
    private AutoBroadcastTask autoBroadcastTask;
    private final FileConfiguration config;

    public Loader(PinkCore plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
    }

    public void load() {
        if (autoBroadcastTask != null) {
            autoBroadcastTask.stop();
        }

        if (isAutoBroadcastEnabled()) {
            autoBroadcastTask = new AutoBroadcastTask(getAutoBroadcastMessages().toArray(new String[0]));
            autoBroadcastTask.runTaskTimer(plugin, 0L, getAutoBroadcastInterval() * 20L);
        }
    }

    public String getJoinMessage() {
        return this.config.getString("server-messages.join");
    }

    public String getLeaveMessage() {
        return this.config.getString("server-messages.leave");
    }

    public boolean isAutoBroadcastEnabled() {
        return this.config.getBoolean("auto-broadcast.enabled");
    }

    public int getAutoBroadcastInterval() {
        return this.config.getInt("auto-broadcast.interval");
    }

    public List<String> getAutoBroadcastMessages() {
        List<String> autoBroadcastMessages = new ArrayList<>();
        for (String key : this.config.getConfigurationSection("auto-broadcast.broadcast-messages").getKeys(false)) {
            List<String> tmp = this.config.getStringList("auto-broadcast.broadcast-messages." + key);
            StringBuilder sb = new StringBuilder();
            for (String s : tmp) {
                sb.append(s).append("\n");
            }
            autoBroadcastMessages.add(sb.toString());
        }
        for (String key : config.getConfigurationSection("auto-broadcast.broadcast-toggles-messages").getKeys(false)) {
            if (config.getBoolean("auto-broadcast.broadcast-toggles." + key)) {
                List<String> tmp = config.getStringList("auto-broadcast.broadcast-toggles-messages." + key);
                StringBuilder sb = new StringBuilder();
                for (String s : tmp) {
                    sb.append(s).append("\n");
                }
                autoBroadcastMessages.add(sb.toString());
            }
        }
        return autoBroadcastMessages;
    }

    public String getAntiCraftMessage() {
        return this.config.getString("anti-craft.message");
    }

    public List<Integer> getAntiCraftBlacklistedItems() {
        return this.config.getIntegerList("anti-place.blacklisted-blocks");
    }

    public String getAntiPlaceMessage() {
        return this.config.getString("anti-place.message");
    }

    public List<Integer> getAntiPlaceBlacklistedBlocks() {
        return this.config.getIntegerList("anti-place.blacklisted-blocks");
    }

    public String getBlockedCommandsMessage() {
        return this.config.getString("blocked-commands.message");
    }

    public List<String> getAllowedPlayers() {
        return this.config.getStringList("blocked-commands.allowed-players");
    }

    public List<String> getBlockedCommands() {
        return this.config.getStringList("blocked-commands.blacklisted-commands");
    }

    public boolean allowPortalCreation() {
        return this.config.getBoolean("canceller-toggling.allow-portal-creation");
    }

    public boolean allowExplosions() {
        return this.config.getBoolean("canceller-toggling.allow-explosions");
    }

    public boolean allowWeatherChanges() {
        return this.config.getBoolean("canceller-toggling.allow-weather-changes");
    }

    public boolean allowLightningStrikes() {
        return this.config.getBoolean("canceller-toggling.allow-lightning-strikes");
    }

    public boolean sendJoinLeaveMessages() {
        return this.config.getBoolean("canceller-toggling.send-activity-message");
    }

    public boolean enableDamageExploitFixer() {
        return this.config.getBoolean("damage-exploit-disabler.enabled");
    }

    public String getDamageExploitDisablerErrorMessage() {
        return this.config.getString("damage-exploit-disabler.error-message");
    }

    public String getDamageExploitDisablerFixMessage() {
        return this.config.getString("damage-exploit-disabler.fix-message");
    }
}