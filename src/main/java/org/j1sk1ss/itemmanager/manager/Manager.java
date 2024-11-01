package org.j1sk1ss.itemmanager.manager;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

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

        if (itemMeta == null) return item;
        if (itemMeta.hasLore()) {
            if (loreLine.isEmpty())
                Objects.requireNonNull(itemMeta.lore()).clear();
        }

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
     * Set material to itemStack
     * @param itemStack itemStack
     * @param material material
     */
    @SuppressWarnings("deprecation")
    public static void setMaterial(ItemStack itemStack, Material material) {
        itemStack.setType(material);
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
        var meta = itemStack.getItemMeta();

        if (meta == null) return null;
        if (meta.getLore() == null) return null;

        return meta.getLore();
    }

    /**
     * Gets name of itemStack
     * @param itemStack ItemStack
     * @return Name of itemStack
     */
    @SuppressWarnings("deprecation")
    public static String getName(ItemStack itemStack) {
        return Objects.requireNonNull(itemStack.getItemMeta().getDisplayName());
    }

    /**
     * Give list of items to player
     * @param itemStacks List of items
     * @param player Player that will take this list
     */
    public static void giveItems(List<ItemStack> itemStacks, Player player) {
        for (var item : itemStacks) giveItems(item, player);
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
        for (var item : itemStacks) giveItemsWithoutLore(item, player);
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
            if (item != null) {
                takeItems(item, player);
            }
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
     * Check if player has item
     * @param itemStacks items
     * @param player player
     * @return True, if all items from list presented in inventory
     */
    public static boolean hasItems(List<ItemStack> itemStacks, Player player) {
        var result = true;
        for (var item : itemStacks)
            if (item != null) {
                result = result && hasItems(item, player);
            }

        return result;
    }

    /**
     * Check if player has item
     * @param itemStack item
     * @param player player
     * @return True if item presented in inventory
     */
    public static boolean hasItems(ItemStack itemStack, Player player) {
        for (var playerItem : player.getInventory()) {
            if (playerItem == null) continue;
            if (playerItem.equals(itemStack)) return true;
        }

        return false;
    }

    /**
     * Set itemStack model data
     * @param itemStack itemStack
     * @param modelData ModelData
     */
    public static void setModelData(ItemStack itemStack, int modelData) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return;

        meta.setCustomModelData(modelData);
        itemStack.setItemMeta(meta);
    }

    /**
     * Get itemStack model data
     * @param itemStack itemStack
     * @return Model data
     */
    public static int getModelData(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return -1;
        if (itemStack.getItemMeta().hasCustomModelData()) return itemStack.getItemMeta().getCustomModelData();
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
        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        if (meta == null) return;

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
        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        if (meta == null) return;

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
        if (meta == null) return -1.0;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.DOUBLE);
        if (value == null) return -1.0;

        return value;
    }

    /**
     * Get double from container
     * @param itemStack itemStack
     * @param key key
     * @param def Default value
     * @return double from container
     */
    public static double getDoubleFromContainer(ItemStack itemStack, String key, double def) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return def;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.DOUBLE);
        if (value == null) return def;

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
        if (meta == null) return -1;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.INTEGER);
        if (value == null) return -1;

        return value;
    }

    /**
     * Get int from container
     * @param itemStack itemStack
     * @param key key
     * @param def Default value
     * @return int from container
     */
    public static int getIntegerFromContainer(ItemStack itemStack, String key, int def) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return def;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.INTEGER);
        if (value == null) return def;

        return value;
    }

    /**
     * Set string to PersistentData container
     * @param itemStack itemStack
     * @param value value
     * @param key key in container
     */
    public static void setString2Container(ItemStack itemStack, String value, String key) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        meta.getPersistentDataContainer().set(containerKey, PersistentDataType.STRING, value);
        itemStack.setItemMeta(meta);
    }

    /**
     * Get string from container
     * @param itemStack itemStack
     * @param key key
     * @return int from container
     */
    public static String getStringFromContainer(ItemStack itemStack, String key) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return null;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        return meta.getPersistentDataContainer().get(containerKey, PersistentDataType.STRING);
    }

    /**
     * Get string from container
     * @param itemStack itemStack
     * @param key key
     * @param def Default value
     * @return int from container
     */
    public static String getStringFromContainer(ItemStack itemStack, String key, String def) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return def;

        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        var value = meta.getPersistentDataContainer().get(containerKey, PersistentDataType.STRING);
        if (value == null) return def;

        return value;
    }

    /**
     * Delete key from item
     * @param itemStack itemStack
     * @param key Key
     */
    public static void deleteKeyFromContainer(ItemStack itemStack, String key) {
        var meta = itemStack.getItemMeta();
        var containerKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemManager.PC")), key);
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(containerKey))
            meta.getPersistentDataContainer().remove(containerKey);

        itemStack.setItemMeta(meta);
    }
}
