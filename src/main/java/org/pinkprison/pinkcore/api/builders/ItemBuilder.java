package org.pinkprison.pinkcore.api.builders;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.enums.Hook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemBuilder {
    private final ItemStack item;
    private final Player player;

    /* -------------------------------------------------------------------- */
    public ItemBuilder(ItemStack paramItemStack) {
        this(paramItemStack, null);
    }

    public ItemBuilder(ItemStack paramItemStack, Player paramPlayer) {
        this.item = paramItemStack.clone();
        this.player = paramPlayer;
    }

    /* -------------------------------------------------------------------- */
    public ItemBuilder(Material paramMaterial, int paramInt) {
        this(paramMaterial, paramInt, null);
    }

    public ItemBuilder(Material paramMaterial, int paramInt, Player paramPlayer) {
        this.item = new ItemStack(paramMaterial);
        this.item.setAmount(paramInt);
        this.player = paramPlayer;
    }

    /* -------------------------------------------------------------------- */
    public ItemBuilder(Material paramMaterial) {
        this(paramMaterial, paramMaterial.getMaxStackSize());
    }

    public ItemBuilder(Material paramMaterial, Player paramPlayer) {
        this(paramMaterial, paramMaterial.getMaxStackSize(), paramPlayer);
    }

    /* -------------------------------------------------------------------- */
    public ItemBuilder(Material paramMaterial, int paramInt, short paramShort) {
        this(paramMaterial, paramInt, paramShort, null);
    }

    public ItemBuilder(Material paramMaterial, int paramInt, short paramShort, Player paramPlayer) {
        this.item = new ItemStack(paramMaterial);
        this.item.setAmount(paramInt);
        this.item.setDurability(paramShort);
        this.player = paramPlayer;
    }

    public ItemStack build() {
        return this.item;
    }

    public ItemBuilder setType(Material paramMaterial) {
        this.item.setType(paramMaterial);
        return this;
    }

    public int getAmount() {
        return this.item.getAmount();
    }

    public ItemBuilder setAmount(int paramInt) {
        this.item.setAmount(paramInt);
        return this;
    }

    public ItemBuilder setDurability(short paramShort) {
        this.item.setDurability(paramShort);
        return this;
    }

    public String getDisplayName() {
        ItemMeta itemMeta = this.item.getItemMeta();
        return itemMeta.getDisplayName();
    }

    public ItemBuilder replaceLoreAndDisplayName(String paramString1, String paramString2) {
        return replaceLore(paramString1, format(paramString2)).replaceDisplayName(paramString1, format(paramString2));
    }

    public ItemBuilder setDisplayName(String paramString) {
        ItemMeta itemMeta = this.item.getItemMeta();
        itemMeta.setDisplayName(format(paramString));
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder replaceDisplayName(String paramString1, String paramString2) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (itemMeta.hasDisplayName()) {
            itemMeta.setDisplayName(itemMeta.getDisplayName().replace(paramString1, format(paramString2)));
            this.item.setItemMeta(itemMeta);
        }
        return this;
    }

    public List<String> getLore() {
        ItemMeta itemMeta = this.item.getItemMeta();
        return (itemMeta.hasLore() && itemMeta.getLore() != null) ? itemMeta.getLore() : new ArrayList<>();
    }

    public ItemBuilder setLore(Collection<String> paramCollection) {
        ItemMeta itemMeta = this.item.getItemMeta();
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : paramCollection)
            arrayList.add(format(str));
        itemMeta.setLore(arrayList);
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(String... paramVarArgs) {
        ItemMeta itemMeta = this.item.getItemMeta();
        ArrayList<String> arrayList = new ArrayList<>();
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = paramVarArgs).length, b = 0; b < i; ) {
            String str = arrayOfString[b];
            arrayList.add(format(str));
            b++;
        }
        itemMeta.setLore(arrayList);
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder changeLore(int paramInt, String paramString1){
        ItemMeta itemMeta = this.item.getItemMeta();
        if(itemMeta.hasLore()){
            ArrayList<String> arrayList = new ArrayList<>();
            for(int i = 0; i<itemMeta.getLore().size(); i++){
                if(i != paramInt || itemMeta.getLore().get(i) == null){
                    arrayList.add(itemMeta.getLore().get(i));
                    continue;
                }
                arrayList.add(format(paramString1));
            }
            itemMeta.setLore(arrayList);
            this.item.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder replaceLore(String paramString1, String paramString2) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (itemMeta.hasLore()) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (String str : itemMeta.getLore()) {
                if (str == null) {
                    arrayList.add(null);
                    continue;
                }
                arrayList.add(str.replace(paramString1, format(paramString2)));
            }
            itemMeta.setLore(arrayList);
            this.item.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder replaceLore(String paramString, List<String> paramList) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (itemMeta.hasLore()) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (String str : itemMeta.getLore()) {
                if (str == null) {
                    arrayList.add(null);
                    continue;
                }
                if (str.contains(paramString)) {
                    for (String str1 : paramList)
                        arrayList.add(format(str1));
                    continue;
                }
                arrayList.add(str);
            }
            itemMeta.setLore(arrayList);
            this.item.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder replaceLore(String paramString, String... paramVarArgs) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (itemMeta.hasLore()) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (String str : itemMeta.getLore()) {
                if (str == null) {
                    arrayList.add(null);
                    continue;
                }
                if (str.contains(paramString)) {
                    byte b;
                    int i;
                    String[] arrayOfString;
                    for (i = (arrayOfString = paramVarArgs).length, b = 0; b < i; ) {
                        String str1 = arrayOfString[b];
                        arrayList.add(format(str1));
                        b++;
                    }
                    continue;
                }
                arrayList.add(str);
            }
            itemMeta.setLore(arrayList);
            this.item.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder addLore(Collection<String> paramCollection) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (itemMeta.getLore() == null)
            itemMeta.setLore(new ArrayList<>());
        List<String> list = itemMeta.getLore();
        for (String str : paramCollection)
            list.add(format(str));
        itemMeta.setLore(list);
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLore(String... paramVarArgs) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (itemMeta.getLore() == null)
            itemMeta.setLore(new ArrayList<>());
        List<String> list = itemMeta.getLore();
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = paramVarArgs).length, b = 0; b < i; ) {
            String str = arrayOfString[b];
            list.add(format(str));
            b++;
        }
        itemMeta.setLore(list);
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment paramEnchantment, int paramInt) {
        this.item.addUnsafeEnchantment(paramEnchantment, paramInt);
        return this;
    }

    public ItemBuilder glow(boolean glow) {
        ItemMeta itemMeta = this.item.getItemMeta();
        if (glow) {
            itemMeta.addEnchant(Enchantment.LURE, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            for (Enchantment enchantment : itemMeta.getEnchants().keySet()) {
                itemMeta.removeEnchant(enchantment);
            }
        }
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder removeNBT(boolean remove){
        ItemMeta meta = this.item.getItemMeta();
        if(remove)
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE);
        else
            meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE);
        this.item.setItemMeta(meta);
        return this;
    }

    private String format(String paramString) {
        if(PinkCore.isHookInitialised(Hook.PLACEHOLDERAPI))
            paramString = PlaceholderAPI.setPlaceholders(player, paramString);
        return ChatColor.translateAlternateColorCodes('&', paramString);
    }

    public boolean hasDisplayName() {
        ItemMeta itemMeta = this.item.getItemMeta();
        return itemMeta.hasDisplayName();
    }

    public boolean hasLore() {
        ItemMeta itemMeta = this.item.getItemMeta();
        return itemMeta.hasLore();
    }
}
