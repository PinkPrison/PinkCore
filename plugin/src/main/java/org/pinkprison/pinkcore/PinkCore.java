package org.pinkprison.pinkcore;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.pinkprison.pinkcore.api.listener.Listeners;
import org.pinkprison.pinkcore.api.hooks.Hook;
import org.pinkprison.pinkcore.api.hooks.PlaceholderAPIHook;
import org.pinkprison.pinkcore.api.hooks.VaultHook;
import org.pinkprison.pinkcore.core.command.*;
import org.pinkprison.pinkcore.core.config.Loader;
import org.pinkprison.pinkcore.core.listeners.*;
import org.pinkprison.pinkcore.core.metrics.Metrics;

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

    private Loader loader;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.saveDefaultConfig();
        this.loader = new Loader(this, this.getConfig());
        this.getLoader().load();

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

        Metrics metrics = new Metrics(this, 17663);

        this.registerCommands();
        this.registerListeners(loader);
    }

    private void registerCommands() {
        this.getLogger().info("Loading commands...");
        new CoreCommand(getInstance());
        new BroadcastCommand(getInstance());
        new DiscordCommand(getInstance());
        new ReglerCommand(getInstance());
        new OntimeCommand(getInstance());
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
        };

        for (Hook hook : hooks) {
            HOOKS.put(hook.getEnum(), hook.init(this));
        }
    }

    public static boolean isHookInitialised(Hook hook) {
        return HOOKS.getOrDefault(hook, false);
    }

    public void reload() {
        this.reloadConfig();
        this.getLoader().load();
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

    public static HashMap<String, Plugin> getDependants() {
        return DEPENDANTS;
    }
}