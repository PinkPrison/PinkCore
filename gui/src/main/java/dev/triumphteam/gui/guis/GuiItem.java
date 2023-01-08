/**
 * MIT License
 *
 * Copyright (c) 2021 TriumphTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.gui.guis;

import dev.triumphteam.gui.components.GuiAction;
import dev.triumphteam.gui.components.util.ItemNbt;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * GuiItem represents the {@link ItemStack} on the {@link Inventory}
 */
@SuppressWarnings("unused")
public class GuiItem {

    // Action to do when clicking on the item
    private GuiAction<InventoryClickEvent> action;

    // The ItemStack of the GuiItem
    private ItemStack itemStack;

    // Random UUID to identify the item when clicking
    private final UUID uuid = UUID.randomUUID();

    /**
     * Main constructor of the GuiItem
     *
     * @param itemStack The {@link ItemStack} to be used
     * @param action    The {@link GuiAction} to run when clicking on the Item
     */
    public GuiItem(@NotNull final ItemStack itemStack, @Nullable final GuiAction<@NotNull InventoryClickEvent> action) {

        this.action = action;

        // Sets the UUID to an NBT tag to be identifiable later
        this.itemStack = ItemNbt.setString(itemStack, "mf-gui", uuid.toString());
    }

    /**
     * Secondary constructor with no action
     *
     * @param itemStack The ItemStack to be used
     */
    public GuiItem(@NotNull final ItemStack itemStack) {
        this(itemStack, null);
    }

    /**
     * Alternate constructor that takes {@link Material} instead of an {@link ItemStack} but without a {@link GuiAction}
     *
     * @param material The {@link Material} to be used when invoking class
     */
    public GuiItem(@NotNull final Material material) {
        this(new ItemStack(material), null);
    }

    /**
     * Alternate constructor that takes {@link Material} instead of an {@link ItemStack}
     *
     * @param material The {@code Material} to be used when invoking class
     * @param action   The {@link GuiAction} should be passed on {@link InventoryClickEvent}
     */
    public GuiItem(@NotNull final Material material, @Nullable final GuiAction<@NotNull InventoryClickEvent> action) {
        this(new ItemStack(material), action);
    }

    /**
     * Replaces the {@link ItemStack} of the GUI Item
     *
     * @param itemStack The new {@link ItemStack}
     */
    public void setItemStack(@NotNull final ItemStack itemStack) {
        this.itemStack = ItemNbt.setString(itemStack, "mf-gui", uuid.toString());
    }

    /**
     * Replaces the {@link GuiAction} of the current GUI Item
     *
     * @param action The new {@link GuiAction} to set
     */
    public void setAction(@Nullable final GuiAction<@NotNull InventoryClickEvent> action) {
        this.action = action;
    }

    /**
     * Gets the GuiItem's {@link ItemStack}
     *
     * @return The {@link ItemStack}
     */
    @NotNull
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Gets the random {@link UUID} that was generated when the GuiItem was made
     */
    @NotNull
    UUID getUuid() {
        return uuid;
    }

    /**
     * Gets the {@link GuiAction} to do when the player clicks on it
     */
    @Nullable
    GuiAction<InventoryClickEvent> getAction() {
        return action;
    }

}