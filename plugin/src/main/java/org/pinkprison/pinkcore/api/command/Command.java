package org.pinkprison.pinkcore.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.api.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A command class that can be extended to create commands
 * with some basic functionality built in.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public abstract class Command {

    private final ArrayList<SubCommand> commands = new ArrayList<>();
    private final JavaPlugin plugin;

    public Command(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Add a sub command to the command
     *
     * @param command The sub command to add
     */
    protected void addSubCommand(SubCommand command) {
        this.commands.add(command);
    }

    /**
     * Executes the command
     *
     * @param sender The sender of the command
     * @param args The arguments of the command
     * @return The CommandResult after the execution of the method
     */
    protected CommandResult execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            return CommandResult.noSubCommandFound();
        }

        SubCommand subCommand = getSubCommandFromAliasOrNull(args[0]);
        if (subCommand == null) {
            return CommandResult.noSubCommandFound();
        }

        if (!hasPermission(sender, subCommand.getPermission())) {
            return CommandResult.noPermission(subCommand);
        }

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
        return subCommand.execute(sender, newArgs);
    }

    /**
     * Check if the {@link CommandSender} is a {@link Player}
     *
     * @param sender The {@link CommandSender} to check
     *
     * @return true if the sender is a player, false otherwise
     */
    protected boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    /**
     * Check if the {@link CommandSender} is a {@link Player}
     * and send a message if not
     *
     * @param sender The {@link CommandSender} to check
     * @param notPlayerMessage The message to send if the sender is not a player
     * @return true if the sender is a player, false otherwise
     */
    protected boolean isPlayer(CommandSender sender, String notPlayerMessage) {
        if (this.isPlayer(sender)) return true;
        sender.sendMessage(ColorUtils.color(notPlayerMessage));
        return false;
    }

    /**
     * Check if the {@link CommandSender} has the specified permission
     *
     * @param sender The {@link CommandSender} to check
     * @param permission The permission to check
     *
     * @return true if the sender has the permission, false otherwise
     */
    protected boolean hasPermission(CommandSender sender, String permission) {
        if (permission.equals("")) return true;
        return sender.hasPermission(permission);
    }

    /**
     * Check if the {@link CommandSender} has the specified permission
     * and send a message if not
     *
     * @param sender The {@link CommandSender} to check
     * @param permission The permission to check
     * @param noPermissionMessage The message to send if the sender does not have the permission
     * @return true if the sender has the permission, false otherwise
     */
    protected boolean hasPermission(CommandSender sender, String permission, String noPermissionMessage) {
        if (this.hasPermission(sender, permission)) return true;
        sender.sendMessage(ColorUtils.color(noPermissionMessage));
        return false;
    }

    /**
     * Get the sub commands
     *
     * @return The sub commands
     */
    protected ArrayList<SubCommand> getSubCommands() {
        return this.commands;
    }

    /**
     * Get the plugin
     *
     * @return The plugin
     */
    protected JavaPlugin getPlugin() {
        return this.plugin;
    }


    /**
     * Get a sub command from an alias
     *
     * @param alias The alias to check
     * @return The sub command if found, null otherwise
     */
    private SubCommand getSubCommandFromAliasOrNull(String alias) {
        for (SubCommand command : this.commands) {
            if (command.containsAlias(alias)) {
                return command;
            }
        }

        return null;
    }
}