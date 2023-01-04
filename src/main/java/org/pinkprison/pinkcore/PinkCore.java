package org.pinkprison.pinkcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.core.enums.Hook;
import org.pinkprison.pinkcore.core.hooks.Actionbar;
import org.pinkprison.pinkcore.core.hooks.PlaceholderAPIHook;
import org.pinkprison.pinkcore.core.hooks.VaultHook;
import org.pinkprison.pinkcore.core.interfaces.IHook;

import java.util.HashMap;

public final class PinkCore extends JavaPlugin {
    private static final HashMap<Hook, Boolean> HOOKS = new HashMap<>();
    private static PinkCore INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initialiseHooks(){
        IHook[] hooks = new IHook[]{
                new VaultHook(),
                new PlaceholderAPIHook(),
                new Actionbar(),
        };
        for(IHook hook : hooks)
            HOOKS.put(hook.getEnum(), hook.init(this));
    }

    public static boolean isHookInitialised(Hook paramHook) {
        return HOOKS.getOrDefault(paramHook, false);
    }

    public static PinkCore getInstance() {
        return INSTANCE;
    }
}
