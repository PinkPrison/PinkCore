package org.pinkprison.pinkcore.api.hooks;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * PlaceholderAPI hook
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 2.0.0
 */
public class PlaceholderAPIHook extends Hook{

    public PlaceholderAPIHook() {
        super("PlaceholderAPI", org.pinkprison.pinkcore.api.hooks.enums.Hook.PLACEHOLDERAPI);
    }

    /**
     *
     * Initialising the {@link PlaceholderAPIHook}.
     *
     * @param paramPlugin The core plugin.
     * @return if the hook is established currently.
     */
    @Override
    public boolean init(JavaPlugin paramPlugin) {
        return super.isEnabled();
    }
}