package org.pinkprison.pinkcore.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A sub command of a {@link Command}.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public abstract class SubCommand extends Command {

    private final String usage;
    private final String description;
    private final String[] permissions;
    private final String[] aliases;

    /**
     * Create a new sub command.
     *
     * @apiNote This constructor is for commands that have one permission.
     *
     * @param plugin The plugin that the command is registered to.
     * @param description The description of the command.
     * @param usage The usage of the command.
     * @param permission The permission of the command, use "" if there should be no permission.
     * @param aliases The aliases of the command.
     */
    public SubCommand(JavaPlugin plugin, String description, String usage, String permission, String... aliases) {
        super(plugin);

        this.description = description;
        this.usage = usage;
        this.permissions = new String[] { permission };
        this.aliases = aliases;
    }

    /**
     * Create a new sub command.
     *
     * @apiNote This constructor is for commands that have multiple permissions.
     *
     * @param plugin The plugin that the command is registered to.
     * @param description The description of the command.
     * @param usage The usage of the command.
     * @param permissions The permissions of the command
     * @param aliases The aliases of the command.
     */
    public SubCommand(JavaPlugin plugin, String description, String usage, String[] permissions, String... aliases) {
        super(plugin);

        this.description = description;
        this.usage = usage;
        this.permissions = permissions;
        this.aliases = aliases;
    }

    /**
     * Check if the sub command contains the given alias.
     *
     * @param alias The alias to check.
     * @return Whether the sub command contains the alias.
     */
    protected boolean containsAlias(String alias) {
        for (String s : this.aliases) {
            if (s.equalsIgnoreCase(alias)) {
                return true;
            }
        }

        return false;
    }

    /**
     * The method that is called when the command is executed.
     *
     * @param sender The sender of the command.
     * @param args The arguments of the command.
     *
     * @return The CommandResult after the execution of the method.
     */
    public abstract CommandResult execute(CommandSender sender, String[] args);

    /**
     * Get the permission of the command.
     *
     * @return The permission of the sub command.
     */
    public String[] getPermissions() {
        return this.permissions;
    }

    /**
     * Get the usage of the command.
     *
     * @return The usage of the sub command.
     */
    public String getUsage(String label) {
        return "/" + label + " " + this.usage;
    }

    /**
     * Get the description of the command.
     *
     * @return The description of the sub command.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the aliases of the command.
     *
     * @return The aliases of the sub command.
     */
    public String[] getAliases() {
        return this.aliases;
    }
}