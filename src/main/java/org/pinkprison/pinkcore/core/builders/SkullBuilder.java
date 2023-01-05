package org.pinkprison.pinkcore.core.builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.UUID;

public class SkullBuilder {
    /** @deprecated */
    @Deprecated
    public static ItemStack itemFromName(String paramString) {
        ItemStack itemStack = getPlayerSkullItem();
        return itemWithName(itemStack, paramString);
    }

    /** @deprecated */
    @Deprecated
    public static ItemStack itemWithName(ItemStack paramItemStack, String paramString) {
        notNull(paramItemStack, "item");
        notNull(paramString, "name");
        return Bukkit.getUnsafe().modifyItemStack(paramItemStack, "{SkullOwner:\"" + paramString + "\"}");
    }

    /**
     * Returns a {@link Skull} item from the given {@link UUID}.
     *
     * @param paramUUID
     * @return {@link Skull} {@link ItemStack} with the given {@link UUID} as the owner.
     */
    public static ItemStack itemFromUuid(UUID paramUUID) {
        ItemStack itemStack = getPlayerSkullItem();
        return itemWithUuid(itemStack, paramUUID);
    }


    public static ItemStack itemWithUuid(ItemStack paramItemStack, UUID paramUUID) {
        notNull(paramItemStack, "item");
        notNull(paramUUID, "id");
        SkullMeta skullMeta = (SkullMeta)paramItemStack.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(paramUUID);
        if(offlinePlayer != null) {
            skullMeta.setOwner(offlinePlayer.getName());
        } else{
            return getPlayerSkullItem();
        }
        paramItemStack.setItemMeta(skullMeta);
        return paramItemStack;
    }

    public static ItemStack convertToSkull(ItemStack paramItemStack, UUID paramUUID) {
        notNull(paramItemStack, "item");
        notNull(paramUUID, "id");
        SkullMeta skullMeta = (SkullMeta)paramItemStack.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(paramUUID);
        if(offlinePlayer != null) {
            skullMeta.setOwner(offlinePlayer.getName());
        } else{
            return paramItemStack;
        }
        paramItemStack.setItemMeta(skullMeta);
        return paramItemStack;
    }

    /**
     * Returns a {@link Skull} item from the given {@link String} URL.
     *
     * @param paramString {@link String} URL
     * @return {@link Skull} {@link ItemStack} with the given {@link String} URL as the data.
     */
    public static ItemStack itemFromUrl(String paramString) {
        ItemStack itemStack = getPlayerSkullItem();
        return itemWithUrl(itemStack, paramString);
    }

    public static ItemStack itemWithUrl(ItemStack paramItemStack, String paramString) {
        notNull(paramItemStack, "item");
        notNull(paramString, "url");
        return itemWithBase64(paramItemStack, urlToBase64(paramString));
    }

    /**
     * Returns a {@link Skull} item from the given {@link String} Base64.
     *
     * @param paramString {@link String} Base64
     * @return {@link Skull} {@link ItemStack} with the given {@link String} Base64 as the data.
     */
    public static ItemStack itemFromBase64(String paramString) {
        ItemStack itemStack = getPlayerSkullItem();
        return itemWithBase64(itemStack, paramString);
    }

    public static ItemStack itemWithBase64(ItemStack paramItemStack, String paramString) {
        notNull(paramItemStack, "item");
        notNull(paramString, "base64");
        UUID uUID = new UUID(paramString.hashCode(), paramString.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(paramItemStack, "{SkullOwner:{Id:\"" + uUID + "\",Properties:{textures:[{Value:\"" + paramString + "\"}]}}}");
    }

    @Deprecated
    public static void blockWithName(Block paramBlock, String paramString) {
        notNull(paramBlock, "block");
        notNull(paramString, "name");
        setBlockType(paramBlock);
        ((Skull)paramBlock.getState()).setOwner(paramString);

    }

    public static void blockWithUuid(Block paramBlock, UUID paramUUID) {
        notNull(paramBlock, "block");
        notNull(paramUUID, "id");
        setBlockType(paramBlock);
        ((Skull)paramBlock.getState()).setOwner(Bukkit.getOfflinePlayer(paramUUID).getName());
    }

    public static void blockWithUrl(Block paramBlock, String paramString) {
        notNull(paramBlock, "block");
        notNull(paramString, "url");
        blockWithBase64(paramBlock, urlToBase64(paramString));
    }

    public static void blockWithBase64(Block paramBlock, String paramString) {
        notNull(paramBlock, "block");
        notNull(paramString, "base64");
        UUID uUID = new UUID(paramString.hashCode(), paramString.hashCode());
        String str = String.format("%d %d %d %s", paramBlock.getX(),
                paramBlock.getY(),
                paramBlock.getZ(), "{Owner:{Id:\"" + uUID + "\",Properties:{textures:[{Value:\"" + paramString + "\"}]}}}");
    }

    /** returns a {@link Skull} {@link ItemStack} */

    public static ItemStack getPlayerSkullItem() {
        return new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short)3);
    }

    private static void setBlockType(Block paramBlock) {
        try {
            paramBlock.setType(Material.valueOf("PLAYER_HEAD"), false);
        } catch (IllegalArgumentException illegalArgumentException) {
            paramBlock.setType(Material.valueOf("SKULL"), false);
        }
    }

    private static void notNull(Object paramObject, String paramString) {
        if (paramObject == null)
            throw new NullPointerException(paramString + " should not be null!");
    }

    private static String urlToBase64(String paramString) {
        URI uRI;
        try {
            uRI = new URI(paramString);
        } catch (URISyntaxException uRISyntaxException) {
            throw new RuntimeException(uRISyntaxException);
        }
        String str = "{\"textures\":{\"SKIN\":{\"url\":\"" + uRI.toString() + "\"}}}";
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
}
