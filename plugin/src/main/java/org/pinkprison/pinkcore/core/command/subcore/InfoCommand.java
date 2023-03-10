package org.pinkprison.pinkcore.core.command.subcore;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.CommandResult;
import org.pinkprison.pinkcore.api.command.SubCommand;

public class InfoCommand extends SubCommand {

    private final PinkCore plugin;

    public InfoCommand(PinkCore plugin) {
        super(plugin, "Information omkring PinkCore og serveren", "info", "pinkcore.command.info", "information");
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            return CommandResult.wrongUsage(this);
        }

        if (!hasPermission(sender, getPermissions())) {
            return CommandResult.noPermission(this);
        }

        StringBuilder message = new StringBuilder();
        message.append("§7§m----------------------------------------");
        message.append("\n");
        message.append("§b§lMinecraft: §f: ").append(plugin.getServer().getVersion());
        message.append("§b§lServer: §f: ").append(plugin.getServer().getBukkitVersion());
        message.append("§b§lPinkCore §f: ").append(plugin.getDescription().getVersion());
        message.append("\n");
        if (PinkCore.getDependants().size() > 0) {
            message.append("§b§lDependants §f: ");
            for (Plugin pl : PinkCore.getDependants().values()) {
                message.append(" - ").append(pl.getName()).append(" (").append(pl.getDescription().getVersion()).append(")\n");
            }
            message.append("\n");
        }
        message.append("§7§m----------------------------------------");
        sender.sendMessage(message.toString());
        return CommandResult.success(this);
    }
}