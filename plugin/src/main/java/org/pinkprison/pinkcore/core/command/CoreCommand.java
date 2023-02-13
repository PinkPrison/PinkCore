package org.pinkprison.pinkcore.core.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.Command;
import org.pinkprison.pinkcore.api.command.CommandResult;
import org.pinkprison.pinkcore.api.command.SubCommand;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.command.subcore.ReloadCommand;
import org.pinkprison.pinkcore.core.command.subcore.UniqueIdentifierCommand;

public class CoreCommand extends Command implements CommandExecutor {

    private final PinkCore plugin;

    public CoreCommand(PinkCore plugin) {
        super(plugin);
        plugin.getCommand("pinkcore").setExecutor(this);
        this.plugin = plugin;
        super.addSubCommand(new ReloadCommand(plugin));
        super.addSubCommand(new UniqueIdentifierCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        CommandResult result = super.execute(sender, args);
        switch (result.getResult()) {
            case SUCCESS:
                return true;
            case WRONG_USAGE:
                sender.sendMessage(ColorUtils.color(this.plugin.getPrefix()) + " Brug: §b" + result.getCommand().getUsage(label));
                return true;
            case NO_PERMISSION:
                sender.sendMessage(ColorUtils.color(this.plugin.getPrefix()) + " Du har ikke adgang til at bruge denne kommando.");
                return true;
            default:
                sendHelpMessage(sender, label);
                return true;
        }
    }

    /**
     * Send the help message to the sender.
     *
     * @param sender The sender to send the message to.
     * @param label  The label of the command.
     */
    private void sendHelpMessage(CommandSender sender, String label) {
        sender.sendMessage("§7§m----------------------------------------");
        sender.sendMessage("");
        sender.sendMessage(ColorUtils.color(this.plugin.getPrefix()) + " §bCommands:");
        for (SubCommand command : super.getSubCommands()) {
            if (!hasPermission(sender, command.getPermissions())) continue;
            sender.sendMessage(" §f- §b" + command.getUsage(label) + " §f" + command.getDescription());
        }
        sender.sendMessage("");
        sender.sendMessage("§7§m----------------------------------------");
    }
}
