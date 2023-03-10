package org.pinkprison.pinkcore.core.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.Command;

public class ReglerCommand extends Command implements CommandExecutor {

    private final PinkCore plugin;

    public ReglerCommand(PinkCore plugin) {
        super(plugin);
        plugin.getCommand("regler").setExecutor(this);
        this.plugin = plugin;
    }

    /*
     * TODO: Add rules to config
     */
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append(" &d&lPINK &f&lREGLER");
        builder.append("\n");
        builder.append("&8[&b1&8]&f Alle former for hack er &eikke &ftilladt.");
        builder.append("&8[&b2&8]&f Autoclicker/Macro er &eikke&f tilladt.");
        builder.append("&8[&b3&8]&f Grimt sprog og spam er &eikke&f tilladt.");
        builder.append("&8[&b4&8]&f Reklame er &eikke&f tilladt.");
        builder.append("&8[&b5&8]&f Scam er &atilladt&f. &8(&fDog ikke hvor &bCoins &fer involveret!&8)");
        builder.append("&8[&b6&8]&f Smurfing mens main er banned er &atilladt&f, dog kan denne regel ses bort fra i nogle tilfælde.");
        builder.append("&8[&b7&8]&f Ingen form for bug, eller glitch er tilladt.");
        builder.append("&8[&b8&8]&f Det er &eikke&f tilladt at bruge minimap.");
        builder.append("&8[&b9&8]&f Det er &eikke&f tilladt at no-pvp abuse med portaler");
        builder.append("&8[&b10&8]&f Det er ikke &etilladt&f at leake personlige oplysninger om andre.");
        builder.append("&8[&b11&8]&f Beskyldninger for abuse skal rapporteres over vores discord.");
        builder.append("&8[&b12&8]&f Salg af ingame ting for &aemeralder &fi lobbyerne er &eIKKE &ftilladt på nogen måde.");
        builder.append("&8[&b13&8]&f Det er &eikke&f tilladt at tigge om &bCoins&f.");
        builder.append("\n");
        builder.append("&3&oGenerelt bare brug din sunde fornuft, så er alt godt :)");
        builder.append("&3&oBrud på disse regler kan få dig mutet, og i nogle tilfælde banned!");
        sender.sendMessage(builder.toString());
        return true;
    }
}