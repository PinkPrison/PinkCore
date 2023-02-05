package org.pinkprison.pinkcore.api.builders;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.pinkprison.pinkcore.api.utils.ColorUtils;

import java.util.*;

/**
 * A class for creating ItemStacks with ease.
 *
 * <p>
 *     This class is part of the AwesomeLib project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 1.4.0
 */
public final class ItemCreator {
    private final ItemStack itemStack;

    private ItemMeta itemMeta;

    /**
     * Creates a new ItemBuilder from an existing ItemBuilder.
     *
     * @param itemBuilder The ItemBuilder to create the ItemBuilder from.
     */
    public ItemCreator(@NotNull ItemCreator itemBuilder) {
        this(itemBuilder.itemStack, itemBuilder.itemMeta);
    }

    /**
     * Creates a new ItemBuilder from the String name of a Material.
     *
     * @param material The String name of the Material to create the ItemBuilder from.
     */
    public ItemCreator(String material) {
        this(Material.valueOf(material));
    }

    /**
     * Creates a new ItemBuilder from a Material.
     *
     * @param material The Material to create the ItemBuilder from.
     */
    public ItemCreator(Material material) {
        this(material, 1);
    }

    /**
     * Creates a new ItemBuilder from a Material and amount.
     *
     * @param material The Material to create the ItemBuilder from.
     * @param amount   The amount of the Material to create the ItemBuilder from.
     */
    public ItemCreator(Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Creates a new ItemBuilder from a Material, name, and lore.
     *
     * @param material The Material to create the ItemBuilder from.
     * @param name     The name of the ItemBuilder.
     * @param lore     The lore of the ItemBuilder.
     */
    public ItemCreator(Material material, String name, String... lore) {
        this(material, 1, (short) 0, name, lore);
    }

    /**
     * Creates a new ItemBuilder from a Material, amount, name, and lore.
     *
     * @param material The Material to create the ItemBuilder from.
     * @param amount   The amount of the Material to create the ItemBuilder from.
     * @param name     The name of the ItemBuilder.
     * @param lore     The lore of the ItemBuilder.
     */
    public ItemCreator(Material material, int amount, String name, String... lore) {this(material, amount, (short) 0, name, lore);}

    /**
     * Creates a new ItemBuilder from a Material, amount, durability, name, and lore.
     *
     * @param material The Material to create the ItemBuilder from.
     * @param amount   The amount of the Material to create the ItemBuilder from.
     * @param i        The durability of the Material to create the ItemBuilder from.
     * @param name     The name of the ItemBuilder.
     * @param lore     The lore of the ItemBuilder.
     */
    public ItemCreator(Material material, int amount, int i, String name, String... lore) {this(material, amount, (short) i, name, lore);}

    /**
     * Creates a new ItemBuilder from a Material, amount, and durability.
     *
     * @param material   The Material to create the ItemBuilder from.
     * @param amount     The amount of the Material to create the ItemBuilder from.
     * @param durability The durability of the Material to create the ItemBuilder from.
     */
    public ItemCreator(Material material, int amount, short durability) {
        this(new ItemStack(material, amount, durability));
    }

    /**
     * Creates a new ItemBuilder from a Material, amount, durability, name, and lore.
     *
     * @param material   The Material to create the ItemBuilder from.
     * @param amount     The amount of the Material to create the ItemBuilder from.
     * @param durability The durability of the Material to create the ItemBuilder from.
     * @param name       The name of the ItemBuilder.
     * @param lore       The lore of the ItemBuilder.
     */
    public ItemCreator(Material material, int amount, short durability, String name, String... lore) {
        this(new ItemStack(material, amount, durability));
        this.name(name);
        this.setLore(lore);
    }

    /**
     * Creates a new ItemBuilder from an existing ItemStack.
     *
     * @param itemStack The ItemStack to create the ItemBuilder from.
     */
    public ItemCreator(@NotNull ItemStack itemStack) {
        this(itemStack.clone(), itemStack.getItemMeta());
    }

    /**
     * Creates a new ItemBuilder from an existing ItemStack and ItemMeta.
     *
     * @param itemStack The ItemStack to create the ItemBuilder from.
     * @param itemMeta  The ItemMeta to create the ItemBuilder from.
     */
    public ItemCreator(@NotNull ItemStack itemStack, ItemMeta itemMeta) {
        if (itemStack.getType() == Material.AIR) itemStack.setType(Material.DIRT);
        this.itemStack = itemStack;
        this.itemMeta = itemMeta;
    }

    /**
     * Clone the ItemBuilder.
     *
     * @return The cloned ItemBuilder.
     */
    @Contract(" -> new")
    @Override
    public @NotNull ItemCreator clone() {
        return new ItemCreator(this.build());
    }

    /**
     * Set the material of the item with a string value.
     *
     * @param material the string value of the material of the item
     * @return the ItemBuilder instance
     */
    public ItemCreator material(String material) {
        this.itemStack.setType(Material.valueOf(material));
        return this;
    }

    /**
     * Set the material of the item.
     *
     * @param material the material of the item
     * @return the ItemBuilder instance
     */
    public ItemCreator material(Material material) {
        this.itemStack.setType(material);
        return this;
    }

    /**
     * Set the amount of the item.
     *
     * @param amount the amount of the item
     * @return the ItemBuilder instance
     */
    public ItemCreator amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Set the durability of the item.
     *
     * @param durability the durability of the item
     * @return the ItemBuilder instance
     */
    public ItemCreator setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    /**
     * Set the name of the item.
     *
     * @param name the name of the item
     * @return the ItemBuilder instance
     */
    public ItemCreator name(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * Add a specific unsafe enchantment to the item.
     *
     * @param enchantment the unsafe enchantment to add
     * @param level       the level of the enchantment
     * @return the ItemBuilder instance
     */
    public ItemCreator addUnsafeEnchantment(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Add a map of unsafe enchantments to the item.
     *
     * @param enchantments the unsafe enchantments to add
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator addUnsafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((enchantment, level) -> this.itemMeta.addEnchant(enchantment, level, true));
        return this;
    }

    /**
     * Add an enchantment to the item.
     *
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     * @return the ItemBuilder instance
     */
    public ItemCreator addEnchantment(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, false);
        return this;
    }

    /**
     * Add a map of enchantments to the item.
     *
     * @param enchantments the enchantments to add
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator addEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((enchantment, level) -> this.itemMeta.addEnchant(enchantment, level, false));
        return this;
    }

    /**
     * Remove a specific enchantment from the item.
     *
     * @param enchantment the enchantment to remove
     * @return the ItemBuilder instance
     */
    public ItemCreator removeEnchantment(Enchantment enchantment) {
        this.itemMeta.removeEnchant(enchantment);
        return this;
    }

    /**
     * Remove a collection of enchantments from the item.
     *
     * @param enchantments the enchantments to remove
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator removeEnchantments(@NotNull Collection<Enchantment> enchantments) {
        enchantments.forEach(this.itemMeta::removeEnchant);
        return this;
    }

    /**
     * Makes an item glow.
     *
     * @return the ItemBuilder instance
     */
    public ItemCreator makeGlowing() {
        if (!this.itemMeta.hasEnchants()) this.itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        return this.addItemFlag(ItemFlag.HIDE_ENCHANTS);
    }

    /**
     * Colorize the name and lore of the ItemStack
     *
     * @return the ItemBuilder instance
     */
    public ItemCreator colorizeAll() {
        this.colorizeName();
        return this.colorizeLore();
    }

    /**
     * Colorize the lore of the ItemStack
     *
     * @return the ItemBuilder instance
     */
    public ItemCreator colorizeLore() {
        if (this.itemMeta.hasLore()) {
            List<String> lore = this.getItemMeta().getLore();
            lore.replaceAll(ColorUtils::color);
            return this.setLore(lore);
        }
        return this;
    }

    /**
     * Colorize the name of the ItemStack
     *
     * @return the ItemBuilder instance
     */
    public ItemCreator colorizeName() {
        if (this.itemMeta.hasDisplayName()) this.name(ColorUtils.color(this.getItemMeta().getDisplayName()));
        return this;
    }

    /**
     * Specify if the Item is unbreakable or not
     *
     * @param unbreakable true if the item is unbreakable
     * @return the ItemBuilder instance
     */
    public ItemCreator unbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Get the lore of the ItemStack
     *
     * @return the lore of the ItemStack
     */
    public List<String> getLore() {
        return this.itemMeta.getLore();
    }

    /**
     * Set the lore of the ItemStack
     *
     * @param lore the lore to set
     * @return the ItemBuilder instance
     */
    public ItemCreator setLore(String... lore) {
        return this.setLore(Arrays.asList(lore));
    }

    /**
     * Set the lore of the ItemStack
     *
     * @param lore the lore to set
     * @return the ItemBuilder instance
     */
    public ItemCreator setLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Remove a line from the lore of the ItemStack
     *
     * @param line the line to remove
     * @return the ItemBuilder instance
     */
    public ItemCreator removeLoreLine(String line) {
        List<String> lore = new ArrayList<>(this.itemMeta.getLore());
        lore.remove(line);
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Remove a line from the lore of the ItemStack at the specified index
     *
     * @param index the index of the line to remove
     * @return the ItemBuilder instance
     */
    public ItemCreator removeLoreLine(int index) {
        if (index < 0) return this;
        List<String> lore = new ArrayList<>(this.itemMeta.getLore());
        if (index > lore.size()) return this;
        lore.remove(index);
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Add a list of lines to the lore of the ItemStack
     *
     * @param lore the list of lines to add
     * @return the ItemBuilder instance
     */
    public ItemCreator addLore(String... lore) {
        return this.addLore(Arrays.asList(lore));
    }

    /**
     * Add a list of lines to the lore of the ItemStack
     *
     * @param lore the list of lines to add
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator addLore(@NotNull List<String> lore) {
        lore.forEach(this::addLoreLine);
        return this;
    }

    /**
     * Add a line to the lore of the ItemStack
     *
     * @param line the line to add
     * @return the ItemBuilder instance
     */
    public ItemCreator addLoreLine(String line) {
        List<String> lore = new ArrayList<>();
        if (this.itemMeta.hasLore()) lore = new ArrayList<>(this.itemMeta.getLore());
        lore.add(line);
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Add a line to the lore of the ItemStack at a specific position
     *
     * @param line the line to add
     * @param pos  the position to add the line
     * @return the ItemBuilder instance
     */
    public ItemCreator addLoreLine(String line, int pos) {
        List<String> lore = new ArrayList<>(this.itemMeta.getLore());
        if (pos >= lore.size()) return this;
        lore.set(pos, line);
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Add a list of item flags to the ItemStack
     *
     * @param itemFlags the item flags
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator addItemFlags(@NotNull List<String> itemFlags) {
        for (String itemFlagStr : itemFlags)
            try {
                ItemFlag itemFlag = ItemFlag.valueOf(itemFlagStr);
                this.addItemFlag(itemFlag);
            } catch (IllegalArgumentException ignored) {
            }
        return this;
    }

    /**
     * Set the item flags of the ItemStack
     *
     * @param itemFlags the item flags
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator itemFlags(@NotNull List<ItemFlag> itemFlags) {
        this.itemMeta.addItemFlags(itemFlags.toArray(new ItemFlag[0]));
        return this;
    }

    /**
     * Add an item flag to the ItemStack
     *
     * @param itemFlag the item flag
     * @return the ItemBuilder instance
     */
    public ItemCreator addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }

    /**
     * Set the color of the ItemStack if it is a dye
     *
     * @param color the color
     * @return the ItemBuilder instance
     */
    @Contract("_ -> this")
    public ItemCreator setColor(@NotNull DyeColor color) {
        this.itemStack.setDurability(color.getDyeData());
        return this;
    }

    /**
     * Set the color of the ItemStack if it is a leather armor
     *
     * @param color the color
     * @return the ItemBuilder instance
     */
    public ItemCreator setLeatherArmorColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) this.itemMeta;
        if (Bukkit.getItemFactory().isApplicable(leatherArmorMeta, this.itemStack)) {
            leatherArmorMeta.setColor(color);
            this.itemMeta = leatherArmorMeta;
        }
        return this;
    }

    /**
     * Set the skull owner of the ItemStack
     *
     * @param owner the skull owner
     * @return the ItemBuilder instance
     */
    public ItemCreator setSkullOwner(String owner) {
        SkullMeta skullMeta = (SkullMeta) this.itemMeta;
        if (Bukkit.getItemFactory().isApplicable(skullMeta, this.itemStack)) {
            skullMeta.setOwner(owner);
            this.itemMeta = skullMeta;
        }
        return this;
    }

    /**
     * Get the ItemMeta of the ItemStack
     *
     * @return the ItemMeta of the ItemStack
     */
    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    /**
     * Get the ItemStack display name
     *
     * @return the ItemStack display name
     */
    public String getDisplayName() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        return itemMeta.getDisplayName();
    }

    /**
     * Check if the ItemStack has a display name
     *
     * @return true if the ItemStack has a display name
     */
    public boolean hasDisplayName() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        return itemMeta.hasDisplayName();
    }

    /**
     * Check if the ItemStack has lore
     *
     * @return true if the ItemStack has lore
     */
    public boolean hasLore() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        return itemMeta.hasLore();
    }

    /**
     * Build the ItemStack
     *
     * @return the ItemStack
     */
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
