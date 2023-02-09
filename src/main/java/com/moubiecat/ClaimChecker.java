package com.moubiecat;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;

/**
 * 用於檢查玩家是否在領地內
 *
 * @author MouBieCat
 */
public final class ClaimChecker extends BukkitRunnable {

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            // 檢查繞過許可權
            if (player.isOp() || player.hasPermission("GriefPreventionFly.bypass")) continue;

            // 獲取宣告的領地
            final @Nullable Claim claimAt = GriefPrevention.instance.dataStore.getClaimAt(player.getLocation(), false, null);

            if (claimAt != null && claimAt.hasExplicitPermission(player, ClaimPermission.Access)) {
                if (!player.getAllowFlight()) {
                    player.sendMessage("§7[§6領地飛行§7] §f您進入具有權限的領地。 §b雙擊空白鍵進行飛行。");
                    Utils.setFlightState(player, true);
                }

                // 如果玩家正在飛行，則撥放粒子特效
                if (player.isFlying()) {
                    player.spawnParticle(Particle.REDSTONE, player.getLocation(), 5, 0.2f, 0.2f, 0.2f, new Particle.DustOptions(Color.GREEN, 2));
                }

            } else if (player.getAllowFlight()) {
                player.sendMessage("§7[§6領地飛行§7] §f您已經離開或不再具有權限的領地，因此系統自動關閉飛行。");
                Utils.teleportToSafety(player);
                Utils.setFlightState(player, false);
            }
        }
    }

}
