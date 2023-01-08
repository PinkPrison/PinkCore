package org.pinkprison.pinkcore.core.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.Command;
import org.pinkprison.pinkcore.api.command.SubCommand;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.command.subcore.ReloadCommand;
import org.pinkprison.pinkcore.core.command.subcore.UniqueIdentifierCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class CoreCommand extends Command implements CommandExecutor {
    private final ArrayList<SubCommand> commands = new ArrayList<>();
    public CoreCommand(PinkCore plugin) {
        super(plugin);
        plugin.getCommand("pinkcore").setExecutor(this);
        commands.add(new ReloadCommand(plugin));
        commands.add(new UniqueIdentifierCommand(plugin));

    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length == 0) {
            sendHelpMessage(sender, label);
            return true;
        }

        for (SubCommand subCommand : commands) {
            for (String alias : subCommand.getAliases()) {
                if (!args[0].equalsIgnoreCase(alias)) {
                    continue;
                }

                if (!hasPermission(sender, subCommand.getPermission())) {
                    return true;
                }

                String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
                if (!subCommand.execute(sender, newArgs)) {
                    sender.sendMessage(ColorUtils.getColored(getPlugin().getPrefix()) + " Brug: §b" + subCommand.getUsage(label));
                }
                return true;
            }
        }

        sendHelpMessage(sender, label);
        return true;
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
        sender.sendMessage(ColorUtils.getColored(getPlugin().getPrefix()) + " §bCommands:");
        for (SubCommand command : commands) {
            if (!hasPermission(sender, command.getPermission(), false)) continue;
            sender.sendMessage(" §f- §b" + command.getUsage(label) + " §f" + command.getDescription());
        }
        sender.sendMessage("");
        sender.sendMessage("§7§m----------------------------------------");
    }
}
