package org.pinkprison.pinkcore.api.hooks;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.pinkprison.pinkcore.api.interfaces.IHook;

/**
 * Implementation of IHook for the dependencies
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
public abstract class Hook implements IHook {
    private final String name;
    private final org.pinkprison.pinkcore.api.enums.Hook hook;
    private final boolean isEnabled;

    /**
     * Hook constructor
     *
     * @param paramString Hook name
     * @param paramHook Hook enum
     */
    public Hook(String paramString, org.pinkprison.pinkcore.api.enums.Hook paramHook){
        this.name = paramString;
        this.hook = paramHook;
        if(paramHook.isBuiltIn())
            this.isEnabled = true;
        else this.isEnabled = Bukkit.getPluginManager().getPlugin(getName()) != null && Bukkit.getPluginManager().getPlugin(getName()).isEnabled();
    }

    /**
     *
     * @return if the hook is enabled
     */
    @Override
    public boolean isEnabled(){
        return isEnabled;
    }

    /**
     *
     * @return the name of the hook
     */
    @Override
    public @NotNull String getName() {
        return name;
    }

    /**
     *
     * @return the enum of the hook
     */
    @Override
    public @NotNull org.pinkprison.pinkcore.api.enums.Hook getEnum() {
        return hook;
    }
}
