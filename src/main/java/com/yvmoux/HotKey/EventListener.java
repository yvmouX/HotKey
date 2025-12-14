package com.yvmoux.HotKey;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class EventListener implements Listener {
    private final Config config;
    private final CommandHandler commandHandler;

    public EventListener(Config config, CommandHandler commandHandler) {
        this.config = config;
        this.commandHandler = commandHandler;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHand(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking() && config.shiftFEnable) {
            event.setCancelled(true);
            for (String cmd : config.shiftF) {
                commandHandler.executeCommandWithPrefix(cmd, player);
            }
        }

        if (!player.isSneaking() && config.fEnable) {
            event.setCancelled(true);
            player.setSprinting(false);
            for (String cmd : config.f) {
                commandHandler.executeCommandWithPrefix(cmd, player);
            }
        }

    }

    @EventHandler
    public void onQ(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking() && config.shiftQEnable) {
            event.setCancelled(true);
            for (String cmd : config.shiftQ) {
                commandHandler.executeCommandWithPrefix(cmd, player);
            }
        }

        if (!player.isSneaking() && config.qEnable) {
            event.setCancelled(true);
            for (String cmd : config.q) {
                commandHandler.executeCommandWithPrefix(cmd, player);
            }
        }

    }
}
