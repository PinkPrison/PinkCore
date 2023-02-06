package org.pinkprison.pinkcore.api.hooks;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.api.hooks.exceptions.HookNotEnabledException;

/**
 * Vault hook
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 2.0.0
 */
public class VaultHook extends Hook {

    private static Economy ECONOMY = null;
    private static Chat CHAT = null;
    private static Permission PERMISSION = null;

    private static final String ECONOMY_EXCEPTION = "Tried using Vault's Economy Provider, but none was provided during initialising.";
    private static final String PERMISSION_EXCEPTION = "Tried using Vault's Permission Provider, but none was provided during initialising.";
    private static final String CHAT_EXCEPTION = "Tried using Vault's Chat Provider, but none was provided during initialising.";

    public VaultHook() {
        super("Vault", org.pinkprison.pinkcore.api.hooks.enums.Hook.VAULT);
    }

    /**
     *
     * Initialising the {@link VaultHook}.
     *
     * @param plugin The core plugin.
     * @return if the hook is established currently.
     */
    @Override
    public boolean init(JavaPlugin plugin) {
        if (!super.isEnabled()) return false;

        RegisteredServiceProvider<Economy> rspEconomy = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        RegisteredServiceProvider<Chat> rspChat = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        RegisteredServiceProvider<Permission> rspPermission = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        if (rspEconomy != null) {
            ECONOMY = rspEconomy.getProvider();
        } else {
            Bukkit.getLogger().severe("[VaultHook] No Economy Provider was found.");
        }

        if (rspChat != null) {
            CHAT = rspChat.getProvider();
        } else {
            Bukkit.getLogger().severe("[VaultHook] No Chat Provider was found.");
        }

        if (rspPermission != null) {
            PERMISSION = rspPermission.getProvider();
        } else {
            Bukkit.getLogger().severe("[VaultHook] No Permission Provider was found.");
        }

        return (
                ECONOMY != null
                        && CHAT != null
                        && PERMISSION != null
        );
    }

    /**
     *
     * Get the raw Vault {@link Economy} instance if initialised.
     *
     * @return Raw Vault {@link Economy} instance.
     */
    public Economy getEconomy(){
        return ECONOMY;
    }

    /**
     *
     * Check if an {@link OfflinePlayer} has a specific amount of money.
     *
     * @param paramOfflinePlayer The {@link OfflinePlayer} to check.
     * @param paramDouble The amount of money to check
     * @return true if the {@link OfflinePlayer}'s balance is greater or equal
     */
    public static boolean canAfford(OfflinePlayer paramOfflinePlayer, double paramDouble){
        if (ECONOMY == null) {
            throw new HookNotEnabledException(ECONOMY_EXCEPTION);
        }
        return getBalance(paramOfflinePlayer) >= paramDouble;
    }

    /**
     *
     * Get the balance of an {@link OfflinePlayer}
     *
     * @param paramOfflinePlayer The {@link OfflinePlayer} to get the balance of.
     * @return the balance of the {@link OfflinePlayer}
     */
    public static double getBalance(OfflinePlayer paramOfflinePlayer){
        if (ECONOMY == null) {
            throw new HookNotEnabledException(ECONOMY_EXCEPTION);
        }
        return ECONOMY.getBalance(paramOfflinePlayer);
    }

    /**
     *
     * Withdraw money from an {@link OfflinePlayer}'s balance.
     *
     * @param paramOfflinePlayer the {@link OfflinePlayer} to withdraw from.
     * @param paramDouble The amount to withdraw
     */
    public static void withdrawBalance(OfflinePlayer paramOfflinePlayer, double paramDouble){
        if (ECONOMY == null) {
            throw new HookNotEnabledException(ECONOMY_EXCEPTION);
        }
        ECONOMY.withdrawPlayer(paramOfflinePlayer, paramDouble);
    }

    /**
     * withdrawBalance alias
     */
    public static void removeBalance(OfflinePlayer paramOfflinePlayer, double paramDouble){
        withdrawBalance(paramOfflinePlayer, paramDouble);
    }

    /**
     *
     * Deposit money to {@link OfflinePlayer}'s balance.
     *
     * @param paramOfflinePlayer the {@link OfflinePlayer} to deposit to.
     * @param paramDouble The amount to deposit
     */
    public static void depositBalance(OfflinePlayer paramOfflinePlayer, double paramDouble){
        if (ECONOMY == null) {
            throw new HookNotEnabledException(ECONOMY_EXCEPTION);
        }
        ECONOMY.depositPlayer(paramOfflinePlayer, paramDouble);
    }

    /**
     * depositBalance alias
     */
    public static void addBalance(OfflinePlayer paramOfflinePlayer, double paramDouble){
        depositBalance(paramOfflinePlayer, paramDouble);
    }

    /**
     *
     * @param player the {@link Player} to get the prefix of
     * @return the prefix of the {@link Player}
     */
    public static String getPrefix(Player player){
        if (CHAT == null) {
            throw new HookNotEnabledException(CHAT_EXCEPTION);
        }
        return CHAT.getPlayerPrefix(player) != null ? CHAT.getPlayerPrefix(player) : "";
    }

    /**
     *
     * @param player the {@link Player} to get the suffix of
     * @return the suffix of the {@link Player}
     */
    public static String getSuffix(Player player){
        if (CHAT == null) {
            throw new HookNotEnabledException(CHAT_EXCEPTION);
        }
        return CHAT.getPlayerSuffix(player) != null ? CHAT.getPlayerSuffix(player) : "";
    }

    /**
     *
     * @param player the {@link Player} to get the group of
     * @return the group of the {@link Player}
     */
    public static String getPrimaryGroup(Player player){
        if (PERMISSION == null) {
            throw new HookNotEnabledException(PERMISSION_EXCEPTION);
        }
        return PERMISSION.getPrimaryGroup(player);
    }

    /**
     *
     * @param player the {@link Player} to get the groups of
     * @return the groups of the {@link Player}
     */
    public static String[] getPlayerGroups(Player player){
        if (PERMISSION == null) {
            throw new HookNotEnabledException(PERMISSION_EXCEPTION);
        }
        return PERMISSION.getPlayerGroups(player);
    }
}