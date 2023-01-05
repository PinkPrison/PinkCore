package org.pinkprison.pinkcore.core.command;

import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;

public abstract class SubCommand extends Command {
    private final String permission;
    private final String usage;
    private final String description;
    private final String[] aliases;

    public SubCommand(PinkCore plugin, String description, String usage, String permission, String... aliases) {
        super(plugin);

        this.description = description;
        this.usage = usage;
        this.permission = permission;
        this.aliases = aliases;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public String getPermission() {
        return this.permission;
    }

    public String getUsage(String label) {
        return "/" + label + " " + this.usage;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getAliases() {
        return this.aliases;
    }
}
