package org.pinkprison.pinkcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.pinkprison.pinkcore.api.enums.Hook;
import org.pinkprison.pinkcore.api.hooks.Actionbar;
import org.pinkprison.pinkcore.api.hooks.PlaceholderAPIHook;
import org.pinkprison.pinkcore.api.hooks.VaultHook;
import org.pinkprison.pinkcore.api.interfaces.IHook;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.command.CoreCommand;
import org.pinkprison.pinkcore.core.config.Loader;
import org.pinkprison.pinkcore.core.listeners.*;

import java.util.HashMap;

public final class PinkCore extends JavaPlugin{
    private static final HashMap<String, Plugin> DEPENDANTS = new HashMap<>();
    private static final HashMap<Hook, Boolean> HOOKS = new HashMap<>();

    //For the auto broadcast
    private String[] messages;

    private Loader loader;
    private static PinkCore INSTANCE;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        INSTANCE = this;
        loader = new Loader();
        loader.load(getConfig());
        messages = loader.getAutoBroadcastMessages().toArray(new String[0]);

        Bukkit.getLogger().info("Loading dependant plugins.");
        for(Plugin dependant : getServer().getPluginManager().getPlugins()){
            PluginDescriptionFile pdf = dependant.getDescription();
            if(pdf.getDepend().contains(getName()) || pdf.getSoftDepend().contains(getName()))
                DEPENDANTS.put(dependant.getName(), dependant);
        }
        Bukkit.getLogger().info(String.format("Loaded dependants (%d): %s", DEPENDANTS.size(), DEPENDANTS.values()));

        Bukkit.getLogger().info("Initialising hooks...");
        initialiseHooks();

        registerCommands(loader);

        registerListeners(loader);

        startAutoBroadcast(loader.isAutoBroadcastEnabled());
    }

    private void registerCommands(Loader loader) {
        getLogger().info("Loading commands...");
        getCommand("blockedcommands").setExecutor(
                new org.pinkprison.pinkcore.core.command.CommandBlocker(this, loader));
        new CoreCommand(getInstance());
    }

    private void registerListeners(Loader loader){
        Bukkit.getLogger().info("Registering listeners...");
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

    private void startAutoBroadcast(boolean bool) {
        if (!bool) return;
        new BukkitRunnable() {
            int messageIndex = 0;
            @Override
            public void run() {
                String currentMessage = messages[messageIndex];
                Bukkit.broadcastMessage(ColorUtils.getColored(currentMessage));
                messageIndex++;
                if(messageIndex >= messages.length)
                    messageIndex = 0;
            }
        }.runTaskTimer(this, 0L, loader.getAutoBroadcastInterval() * 20L);
    }

    public void reload() {
        reloadConfig();
        loader.load(getConfig());
        messages = loader.getAutoBroadcastMessages().toArray(new String[0]);
    }

    public String getPrefix() {
        return "&8[ &d&lPink&f&lCore &8] &r";
    }

    public Loader getLoader() {
        return loader;
    }

    public static PinkCore getInstance() {
        return INSTANCE;
    }
}
