package org.pinkprison.pinkcore.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.api.command.exceptions.NoPermissionException;
import org.pinkprison.pinkcore.api.command.exceptions.NoSuchSubCommandException;
import org.pinkprison.pinkcore.api.command.exceptions.WrongCommandUsageException;
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
 * @author WildTooth
 */
public abstract class Command {

    private final ArrayList<SubCommand> commands = new ArrayList<>();
    private final JavaPlugin plugin;

    public Command(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    protected void addSubCommand(SubCommand command) {
        this.commands.add(command);
    }

    protected boolean containsAlias(String alias) {
        for (SubCommand command : this.commands) {
            if (command.containsAlias(alias)) {
                return true;
            }
        }

        return false;
    }

    protected void execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) throws NoSuchSubCommandException, NoPermissionException, WrongCommandUsageException {
        if (args.length == 0) {
            throw new NoSuchSubCommandException("No sub command specified");
        }

        /*
         * Hvis vi skal gå performancemæssigt op i det, så bør vi nok erstatte Exceptions med en CommandResult Enum
         */
        for (SubCommand subCommand : this.commands) {
            for (String alias : subCommand.getAliases()) {
                if (!args[0].equalsIgnoreCase(alias)) {
                    continue;
                }

                if (!hasPermission(sender, subCommand.getPermission())) {
                    throw new NoPermissionException("No permission");
                    //return true;
                }

                String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
                if (!subCommand.execute(sender, newArgs)) {
                    //sender.sendMessage(ColorUtils.color(this.plugin.getPrefix() + " Brug: §b" + subCommand.getUsage(label)));
                    //return false;
                    throw new WrongCommandUsageException(subCommand.getUsage(label));
                }
                return;
                //return true;
            }
        }

        throw new NoSuchSubCommandException("No such sub command");
    }

    /**
     * Check if the {@link CommandSender} is a {@link Player}
     *
     * @param sender The {@link CommandSender} to check
     *
     * @return true if the sender is a player, false otherwise
     * (always sends a message to the sender if false)
     */
    protected boolean isPlayer(CommandSender sender) {
        return isPlayer(sender, true);
    }

    /**
     * Check if the {@link CommandSender} is a {@link Player}
     *
     * @param sender The {@link CommandSender} to check
     * @param notify Whether to send a message to the sender if false
     *
     * @return true if the sender is a player, false otherwise
     */
    protected boolean isPlayer(CommandSender sender, boolean notify) {
        if (sender instanceof Player) return true;
        if (notify) sender.sendMessage("Du skal være en spiller for at bruge denne kommando.");
        return false;
    }

    /**
     * Check if the {@link CommandSender} has the specified permission
     *
     * @param sender The {@link CommandSender} to check
     * @param permission The permission to check
     *
     * @return true if the sender has the permission, false otherwise
     * (always sends a message to the sender if false)
     */
    protected boolean hasPermission(CommandSender sender, String permission) {
        return hasPermission(sender, permission, true);
    }

    /**
     * Check if the {@link CommandSender} has the specified permission
     *
     * @param sender The {@link CommandSender} to check
     * @param permission The permission to check
     * @param notify Whether to send a message to the sender if false
     *
     * @return true if the sender has the permission, false otherwise
     */
    protected boolean hasPermission(CommandSender sender, String permission, boolean notify) {
        if (sender.hasPermission(permission)) return true;
        if (notify) sender.sendMessage("Du har ikke adgang til denne kommando.");
        return false;
    }

    /**
     * Get the plugin
     *
     * @return The plugin
     */
    protected JavaPlugin getPlugin() {
        return plugin;
    }
}
