package org.pinkprison.pinkcore.core.interfaces;

import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.core.enums.Hook;

public interface IHook {

    String getName();

    Hook getEnum();

    boolean isEnabled();

    boolean init(JavaPlugin plugin);
}
