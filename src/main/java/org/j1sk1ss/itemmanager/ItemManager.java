package org.j1sk1ss.itemmanager;

import org.bukkit.plugin.java.JavaPlugin;

public final class ItemManager extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.print("[ItemManager] ItemManager module activated");
    }

    @Override
    public void onDisable() {
        System.out.print("[ItemManager] ItemManager module deactivated");
    }
}
