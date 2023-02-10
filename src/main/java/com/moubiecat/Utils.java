package com.moubiecat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * 一些工具方法
 *
 * @author MouBieCat
 */
public final class Utils {

    /**
     * 傳送玩家至安全位置
     *
     * @param player 玩家
     */
    public static void teleportToSafety(@NonNull Player player) {
        final Location location = player.getLocation();
        for (int y = location.getBlockY(); y >= -64; y--) {
            // 獲取所在位置的方塊
            final Block blockAt = player.getWorld().getBlockAt(new Location(player.getWorld(), location.getBlockX(), y, location.getBlockZ()));
            // 如果方塊不是空氣、可通過，代表是安全位置
            if (!blockAt.getType().equals(Material.AIR) && !blockAt.isPassable()) {
                // 將玩家傳送至此
                player.teleport(new Location(player.getWorld(), location.getBlockX(), y + 2.5D, location.getBlockZ(), location.getYaw(), location.getPitch()));
                return; // 返回
            }
        }
    }

    /**
     * 緩慢至安全位置
     *
     * @param player 玩家
     */
    public static void slowDownToSafety(@NonNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 0));
    }

    /**
     * 設置玩家飛行狀態
     *
     * @param player 玩家
     * @param state  狀態
     */
    public static void setFlightState(@NonNull Player player, boolean state) {
        player.setAllowFlight(state);
        player.setFlying(state);
    }

}
