package org.pinkprison.pinkcore.core.hooks;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.pinkprison.pinkcore.core.interfaces.IHook;

public class Hook implements IHook {
    private final String name;
    private final org.pinkprison.pinkcore.core.enums.Hook hook;
    private final boolean isEnabled;

    public Hook(String paramString, org.pinkprison.pinkcore.core.enums.Hook paramHook){
        this.name = paramString;
        this.hook = paramHook;
        if(paramHook.isBuiltIn())
            this.isEnabled = true;
        else this.isEnabled = Bukkit.getPluginManager().getPlugin(getName()) != null && Bukkit.getPluginManager().getPlugin(getName()).isEnabled();
    }

    @Override
    public boolean isEnabled(){
        return isEnabled;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull org.pinkprison.pinkcore.core.enums.Hook getEnum() {
        return hook;
    }
}
