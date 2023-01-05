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

    public List<String> getBlockedCommandsAllowedPlayers() {
        return blockedCommandsAllowedPlayers;
    }

    public List<String> getBlockedCommandsBlacklistedCommands() {
        return blockedCommandsBlacklistedCommands;
    }
}
