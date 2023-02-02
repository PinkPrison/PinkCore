package org.pinkprison.pinkcore.api.interfaces;

import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.api.enums.Hook;

/**
 * Interface for the hooks
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
public interface IHook {

    /**
     * Get the name of the hook
     * @return Hook name
     */
    String getName();

    /**
     * Get the enum of the hook
     * @return Hook enum
     */
    Hook getEnum();

    /**
     * Check if the hook is enabled
     * @return Hook enabled
     */
    boolean isEnabled();

    /**
     * Initialise the hook
     * @param plugin JavaPlugin
     * @return if the hook was initialised
     */
    boolean init(JavaPlugin plugin);
}
