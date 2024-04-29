package org.j1sk1ss.itemmanager.manager;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.j1sk1ss.itemmanager.ItemManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Manager {
    /**
     * Sets lore of itemStack
     * @param item ItemStack that should take new lore
     * @param loreLine Lore line
     * @return ItemStack with lore
     */
    public static ItemStack setLore(ItemStack item, String loreLine) {
        var itemMeta = Objects.requireNonNull(item).getItemMeta();
        var lore = new ArrayList<Component>();

        for (var line : loreLine.split("\n"))
            lore.add(Component.text(line));

        itemMeta.lore(lore);
        Objects.requireNonNull(item).setItemMeta(itemMeta);
        return item;
    }

    /**
     * Sets lore of itemStack
     * @param item ItemStack that should take new name
     * @param name New name
     * @return ItemStack with name
     */
    public static ItemStack setName(ItemStack item, String name) {
        var itemMeta = Objects.requireNonNull(item).getItemMeta();

        itemMeta.displayName(Component.text(name));
        Objects.requireNonNull(item).setItemMeta(itemMeta);

        return item;
    }

    /**
     * Gets material of itemStack
     * @param item ItemStack
     * @return Material of itemStack
     */
    public static Material getMaterial(ItemStack item) {
        return item.getType();
    }

    /**
     * Gets lore of itemStack
     * @param itemStack ItemStack
     * @return Lore of itemStack
     */
    @SuppressWarnings("deprecation")
    public static List<String> getLoreLines(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return new ArrayList<>();
        if (itemStack.getItemMeta().getLore() == null) return new ArrayList<>();

        return itemStack.getItemMeta().getLore();
    }

    /**
     * Gets name of itemStack
     * @param itemStack ItemStack
     * @return Name of itemStack
     */
    @SuppressWarnings("deprecation")
    public static String getName(ItemStack itemStack){
        return Objects.requireNonNull(itemStack.getItemMeta().getDisplayName());
    }

    /**
     * Give list of items to player
     * @param itemStacks List of items
     * @param player Player that will take this list
     */
    public static void giveItems(List<ItemStack> itemStacks, Player player) {
        for (var item : itemStacks)
            player.getInventory().addItem(item).forEach((index, itemStack) ->
                    player.getWorld().dropItem(player.getLocation(), itemStack));
    }

    /**
     * Give item to player
     * @param item Item
     * @param player Player that will take this item
     */
    public static void giveItems(ItemStack item, Player player) {
        player.getInventory().addItem(item).forEach((index, itemStack) ->
                player.getWorld().dropItem(player.getLocation(), itemStack));
    }

    /**
     * Give items to player without any lore
     * @param itemStacks List of items
     * @param player Player that will take this list
     */
    public static void giveItemsWithoutLore(List<ItemStack> itemStacks, Player player) {
        for (var item : itemStacks)
            player.getInventory().addItem(Manager.setLore(item, "")).forEach((index, itemStack) ->
                    player.getWorld().dropItem(player.getLocation(), itemStack));
    }

    /**
     * Give item to player without any lore
     * @param item Item
     * @param player Player that will take this item
     */
    public static void giveItemsWithoutLore(ItemStack item, Player player) {
        player.getInventory().addItem(Manager.setLore(item, "")).forEach((index, itemStack) ->
                player.getWorld().dropItem(player.getLocation(), itemStack));
    }

    /**
     * Takes items from player
     * @param itemStacks List of items that should be taken
     * @param player Player that will lose this list of items
     */
    public static void takeItems(List<ItemStack> itemStacks, Player player) {
        for (var item : itemStacks)
            if (item != null)
                for (var playerItem : player.getInventory())
                    if (playerItem != null)
                        if (playerItem.equals(item)) player.getInventory().removeItem(playerItem);
    }

    /**
     * Takes item from player
     * @param itemStack Item that should be taken
     * @param player Player that will lose this item
     */
    public static void takeItems(ItemStack itemStack, Player player) {
        for (var playerItem : player.getInventory()) {
            if (playerItem == null) continue;
            if (playerItem.equals(itemStack)) player.getInventory().removeItem(playerItem);

            break;
        }
    }

    /**
     * Set itemStack model data
     * @param itemStack itemStack
     * @param modelData ModelData
     */
    public static void setModelData(ItemStack itemStack, int modelData) {
        var meta = itemStack.getItemMeta();
        meta.setCustomModelData(modelData);
        itemStack.setItemMeta(meta);
    }

    /**
     * Get itemStack model data
     * @param itemStack itemStack
     * @return Model data
     */
    public static int getModelData(ItemStack itemStack) {
        if (itemStack.getItemMeta().hasCustomModelData())
            return itemStack.getItemMeta().getCustomModelData();
        else return -1;
    }

    /**
     * Set double to PersistentData container
     * @param itemStack itemStack
     * @param value value
     * @param key key in container
     */
    public static void setDouble2Container(ItemStack itemStack, double value, String key) {
        var meta = itemStack.getItemMeta();
        var containerKey = new NamespacedKey(ItemManager.getPlugin(ItemManager.class), key);

        meta.getPersistentDataContainer().set(containerKey, PersistentDataType.DOUBLE, value);
        itemStack.setItemMeta(meta);
    }

    /**
     * Set int to PersistentData container
     * @param itemStack itemStack
     * @param value value
     * @param key key in container
     */
    public static void setInteger2Container(ItemStack itemStack, int value, String key) {
        var meta = itemStack.getItemMeta();
        var containerKey = new NamespacedKey(ItemManager.getPlugin(ItemManager.class), key);

        meta.getPersistentDataContainer().set(containerKey, PersistentDataType.INTEGER, value);
        itemStack.setItemMeta(meta);
    }

    /**
     * Get double from container
     * @param itemStack itemStack
     * @param key key
     * @return double from container
     */
    public static double getDoubleFromContainer(ItemStack itemStack, String key) {
        var meta = itemStack.getItemMeta();
        var containerKey = new NamespacedKey(ItemManager.getPlugin(ItemManager.class), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.DOUBLE);
        if (value == null) return -1;

        return value;
    }

    /**
     * Get int from container
     * @param itemStack itemStack
     * @param key key
     * @return int from container
     */
    public static int getIntegerFromContainer(ItemStack itemStack, String key) {
        var meta = itemStack.getItemMeta();
        var containerKey = new NamespacedKey(ItemManager.getPlugin(ItemManager.class), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.INTEGER);
        if (value == null) return -1;

        return value;
    }

    /**
     * Delete key from item
     * @param itemStack itemStack
     * @param key Key
     */
    public static void deleteKeyFromContainer(ItemStack itemStack, String key) {
        var meta = itemStack.getItemMeta();
        var containerKey = new NamespacedKey(ItemManager.getPlugin(ItemManager.class), key);

        if (meta.getPersistentDataContainer().has(containerKey))
            meta.getPersistentDataContainer().remove(containerKey);

        itemStack.setItemMeta(meta);
    }
}
