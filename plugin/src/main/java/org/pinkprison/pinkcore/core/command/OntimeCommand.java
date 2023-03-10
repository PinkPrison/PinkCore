package org.pinkprison.pinkcore.core.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.Command;

public class OntimeCommand extends Command implements CommandExecutor {

    private final PinkCore plugin;

    public OntimeCommand(PinkCore plugin) {
        super(plugin);
        plugin.getCommand("ontime").setExecutor(this);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {


        /*
        OfflinePlayer player = null;
        if (args.length == 0) {
            if (sender instanceof Player) {
                player = (Player) sender;
            } else {
                sender.sendMessage("Du skal være en spiller for at bruge denne kommando.");
                return true;
            }
        } else {
            String playerName = args[0];
            player = Bukkit.getOfflinePlayer(playerName);
            if (player == null) {
                sender.sendMessage("Spilleren " + playerName + " findes ikke.");
                return true;
            }


        }
         */
        return true;
    }
}