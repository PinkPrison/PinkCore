package org.pinkprison.pinkcore.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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

        if (!this.hasPermission(sender, subCommand.getPermissions())) {
            return CommandResult.noPermission(subCommand);
        }

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
        return subCommand.execute(sender, newArgs);
    }

    /**
     * Check if the {@link CommandSender} is a {@link Player}
     *
     * @param sender The {@link CommandSender} to check
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
        if (this.isPlayer(sender)) {
            return true;
        }

        sender.sendMessage(notPlayerMessage);
        return false;
    }

    /**
     * Check if the {@link CommandSender} has the specified permission
     *
     * @param sender The {@link CommandSender} to check
     * @param permissions The permissions to check
     *
     * @return true if the sender has the permission, false otherwise
     */
    protected boolean hasPermission(CommandSender sender, String... permissions) {
        for (String perm : permissions) {
            if (sender.hasPermission(perm)) {
                return true;
            }
        }

        return permissions.length == 0;
    }

    /**
     * Check if the {@link CommandSender} has the specified permission
     * and send a message if not
     *
     * @param sender The {@link CommandSender} to check
     * @param noPermissionMessage The message to send if the sender does not have the permission
     * @param permissions The permissions to check
     * @return true if the sender has the permission, false otherwise
     */
    protected boolean hasPermission(CommandSender sender, String noPermissionMessage, String... permissions) {
        if (this.hasPermission(sender, permissions)) {
            return true;
        }

        sender.sendMessage(noPermissionMessage);
        return false;
    }

    /**
     * Get a sub command from an alias
     *
     * @param alias The alias to check
     * @return The sub command if found, null otherwise
     */
    protected SubCommand getSubCommandFromAliasOrNull(String alias) {
        for (SubCommand command : this.commands) {
            if (command.containsAlias(alias)) {
                return command;
            }
        }

        return null;
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
}