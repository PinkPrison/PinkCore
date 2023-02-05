package org.pinkprison.pinkcore.api.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of IHook for the dependencies
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
public abstract class Hook {

    private final String name;
    private final org.pinkprison.pinkcore.api.hooks.enums.Hook hook;
    private final boolean isEnabled;

    /**
     * Hook constructor
     *
     * @param name Hook name
     * @param hook Hook enum
     */
    public Hook(String name, org.pinkprison.pinkcore.api.hooks.enums.Hook hook){
        this.name = name;
        this.hook = hook;
        if (hook.isBuiltIn()) {
            this.isEnabled = true;
        } else {
            this.isEnabled = Bukkit.getPluginManager().getPlugin(getName()) != null && Bukkit.getPluginManager().getPlugin(getName()).isEnabled();
        }
    }

    /**
     *
     * @return if the hook is enabled
     */
    public boolean isEnabled(){
        return this.isEnabled;
    }

    /**
     *
     * @return the name of the hook
     */
    public @NotNull String getName() {
        return this.name;
    }

    /**
     *
     * @return the enum of the hook
     */
    public @NotNull org.pinkprison.pinkcore.api.hooks.enums.Hook getEnum() {
        return this.hook;
    }

    public abstract boolean init(JavaPlugin plugin);
}
