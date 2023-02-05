package org.pinkprison.pinkcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.pinkprison.pinkcore.api.events.Listeners;
import org.pinkprison.pinkcore.api.hooks.Actionbar;
import org.pinkprison.pinkcore.api.hooks.Hook;
import org.pinkprison.pinkcore.api.hooks.PlaceholderAPIHook;
import org.pinkprison.pinkcore.api.hooks.VaultHook;
import org.pinkprison.pinkcore.core.command.CoreCommand;
import org.pinkprison.pinkcore.core.config.Loader;
import org.pinkprison.pinkcore.core.listeners.*;

import java.util.HashMap;

/**
 * PinkCore main class
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
public final class PinkCore extends JavaPlugin{

    private static final HashMap<String, Plugin> DEPENDANTS = new HashMap<>();
    private static final HashMap<org.pinkprison.pinkcore.api.hooks.enums.Hook, Boolean> HOOKS = new HashMap<>();
    private static PinkCore INSTANCE;

    private final Loader loader = new Loader(this);

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.saveDefaultConfig();
        this.getLoader().load(getConfig());

        this.getLogger().info("Loading dependant plugins.");
        for (Plugin dependant : getServer().getPluginManager().getPlugins()) {
            PluginDescriptionFile pdf = dependant.getDescription();
            if (pdf.getDepend().contains(getName()) || pdf.getSoftDepend().contains(getName())) {
                DEPENDANTS.put(dependant.getName(), dependant);
            }
        }
        this.getLogger().info(String.format("Loaded dependants (%d): %s", DEPENDANTS.size(), DEPENDANTS.values()));

        this.getLogger().info("Initialising hooks...");
        this.initialiseHooks();

        new BukkitRunnable() {
            public @Override void run() {
                new Listeners().register();
            }
        }.runTaskLater(this, 1);

        this.registerCommands(loader);
        this.registerListeners(loader);
    }

    private void registerCommands(Loader loader) {
        this.getLogger().info("Loading commands...");
        new org.pinkprison.pinkcore.core.command.CommandBlocker(this, loader);
        new CoreCommand(getInstance());
    }

    private void registerListeners(Loader loader) {
        this.getLogger().info("Registering listeners...");
        new DamageBugCanceller(this,
                    loader.getDamageExploitDisablerErrorMessage(),
                    loader.getDamageExploitDisablerFixMessage());
        new CommandBlocker(this, loader);
        new BlockListener(this, loader);
        new CraftListener(this, loader);
        new EntityListener(this, loader);
        new PlayerListener(this, loader);
        new WeatherListener(this, loader);
        new WorldListener(this, loader);
    }

    private void initialiseHooks() {
        Hook[] hooks = new Hook[] {
                new VaultHook(),
                new PlaceholderAPIHook(),
                new Actionbar()
        };

        for (Hook hook : hooks) {
            HOOKS.put(hook.getEnum(), hook.init(this));
        }
    }

    public static boolean isHookInitialised(Hook paramHook) {
        return HOOKS.getOrDefault(paramHook, false);
    }

    public void reload() {
        this.reloadConfig();
        this.getLoader().load(getConfig());
    }

    public String getPrefix() {
        return "&8[ &d&lPink&f&lCore &8] &r";
    }

    public Loader getLoader() {
        return this.loader;
    }

    public static PinkCore getInstance() {
        return INSTANCE;
    }
}
