package com.moubiecat;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 插件主要類
 *
 * @author MouBieCat
 */
public final class MouBieCat extends JavaPlugin {

    private final ClaimChecker checker = new ClaimChecker();

    /**
     * 啟用插件
     */
    @Override
    public void onEnable() {
        this.checker.runTaskTimer(this, 0, 20L);
    }

    /**
     * 禁用插件
     */
    @Override
    public void onDisable() {
        this.checker.cancel();
    }

}
