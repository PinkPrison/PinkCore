package org.pinkprison.pinkcore.core.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class Loader {

    //server-messages.join
    private String joinMessage;
    //server-messages.leave
    private String leaveMessage;

    //auto-broadcast.interval
    private int autoBroadcastInterval;
    //auto-broadcast.broadcast-messages
    private List<String> autoBroadcastMessages = new ArrayList<>();

    //anti-craft.message
    private String antiCraftMessage;
    //anti-craft.blacklisted-items
    private List<Integer> antiCraftBlacklistedItems = new ArrayList<>();

    //anti-place.message
    private String antiPlaceMessage;
    //anti-place.blacklisted-blocks
    private List<Integer> antiPlaceBlacklistedBlocks = new ArrayList<>();

    //blocked-commands.message
    private String blockedCommandsMessage;
    //blocked-commands.allowed-players
    private List<String> blockedCommandsAllowedPlayers = new ArrayList<>();
    //blocked-commands.blacklisted-commands
    private List<String> blockedCommandsBlacklistedCommands = new ArrayList<>();

    //canceller-toggling.allow-portal-creation
    private boolean allowPortalCreation;
    //canceller-toggling.allow-explosions
    private boolean allowExplosions;
    //canceller-toggling.allow-weather-changes
    private boolean allowWeatherChanges;
    //canceller-toggling.allow-lightning-strikes
    private boolean allowLightningStrikes;
    //canceller-toggling.send-activity-message
    private boolean sendActivityMessage;

    //damage-exploit-disabler.enabled
    private boolean damageExploitDisabler;
    //damage-exploit-disabler.error-message
    private String damageExploitDisablerErrorMessage;
    //damage-exploit-disabler.fix-message
    private String damageExploitDisablerFixMessage;



    public void load(FileConfiguration config) {
        //server-messages.join
        joinMessage = config.getString("server-messages.join");
        //server-messages.leave
        leaveMessage = config.getString("server-messages.leave");

        //auto-broadcast.interval
        autoBroadcastInterval = config.getInt("auto-broadcast.interval");
        //auto-broadcast.broadcast-messages
        autoBroadcastMessages.clear();
        for (String key : config.getConfigurationSection("auto-broadcast.broadcast-messages").getKeys(false)) {
            List<String> tmp = config.getStringList("auto-broadcast.broadcast-messages." + key);
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
        //anti-craft.message
        antiCraftMessage = config.getString("anti-craft.message");
        //anti-craft.blacklisted-items
        antiCraftBlacklistedItems.clear();
        antiCraftBlacklistedItems = config.getIntegerList("anti-craft.blacklisted-items");

        //anti-place.message
        antiPlaceMessage = config.getString("anti-place.message");
        //anti-place.blacklisted-blocks
        antiPlaceBlacklistedBlocks.clear();
        antiPlaceBlacklistedBlocks = config.getIntegerList("anti-place.blacklisted-blocks");

        //blocked-commands.message
        blockedCommandsMessage = config.getString("blocked-commands.message");
        //blocked-commands.allowed-players
        blockedCommandsAllowedPlayers.clear();
        blockedCommandsAllowedPlayers = config.getStringList("blocked-commands.allowed-players");

        //blocked-commands.blacklisted-commands
        blockedCommandsBlacklistedCommands.clear();
        blockedCommandsBlacklistedCommands = config.getStringList("blocked-commands.blacklisted-commands");

        //canceller-toggling.allow-portal-creation
        allowPortalCreation = config.getBoolean("canceller-toggling.allow-portal-creation");
        //canceller-toggling.allow-explosions
        allowExplosions = config.getBoolean("canceller-toggling.allow-explosions");
        //canceller-toggling.allow-weather-changes
        allowWeatherChanges = config.getBoolean("canceller-toggling.allow-weather-changes");
        //canceller-toggling.allow-lightning-strikes
        allowLightningStrikes = config.getBoolean("canceller-toggling.allow-lightning-strikes");
        //canceller-toggling.send-activity-message
        sendActivityMessage = config.getBoolean("canceller-toggling.send-activity-message");

        //damage-exploit-disabler.enabled
        damageExploitDisabler = config.getBoolean("damage-exploit-disabler.enabled");
        //damage-exploit-disabler.error-message
        damageExploitDisablerErrorMessage = config.getString("damage-exploit-disabler.error-message");
        //damage-exploit-disabler.fix-message
        damageExploitDisablerFixMessage = config.getString("damage-exploit-disabler.fix-message");
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public int getAutoBroadcastInterval() {
        return autoBroadcastInterval;
    }

    public List<String> getAutoBroadcastMessages() {
        return autoBroadcastMessages;
    }

    public String getAntiCraftMessage() {
        return antiCraftMessage;
    }

    public List<Integer> getAntiCraftBlacklistedItems() {
        return antiCraftBlacklistedItems;
    }

    public String getAntiPlaceMessage() {
        return antiPlaceMessage;
    }

    public List<Integer> getAntiPlaceBlacklistedBlocks() {
        return antiPlaceBlacklistedBlocks;
    }

    public String getBlockedCommandsMessage() {
        return blockedCommandsMessage;
    }

    public List<String> getAllowedPlayers() {
        return blockedCommandsAllowedPlayers;
    }

    public List<String> getBlockedCommands() {
        return blockedCommandsBlacklistedCommands;
    }

    public boolean allowPortalCreation() {
        return allowPortalCreation;
    }

    public boolean allowExplosions() {
        return allowExplosions;
    }

    public boolean allowWeatherChanges() {
        return allowWeatherChanges;
    }

    public boolean allowLightningStrikes() {
        return allowLightningStrikes;
    }

    public boolean sendJoinLeaveMessages() {
        return sendActivityMessage;
    }

    public boolean enableDamageExploitFixer() {
        return damageExploitDisabler;
    }

    public String getDamageExploitDisablerErrorMessage() {
        return damageExploitDisablerErrorMessage;
    }

    public String getDamageExploitDisablerFixMessage() {
        return damageExploitDisablerFixMessage;
    }
}
