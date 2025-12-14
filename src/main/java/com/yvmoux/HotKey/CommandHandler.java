package com.yvmoux.HotKey;

import cn.yvmou.ylib.api.scheduler.UniversalScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandHandler {
    private final UniversalScheduler scheduler;

    public CommandHandler(UniversalScheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 解析并执行带有前缀的命令
     * @param command 带前缀的命令字符串，如 "o: say hello"
     * @param player 执行命令的玩家
     */
    public void executeCommandWithPrefix(String command, Player player) {
        // 去除首尾空格
        command = command.trim();

        // 判断前缀
        if (command.startsWith("o:")) {
            // OP权限执行
            executeAsOp(command.substring(2).trim(), player);
        } else if (command.startsWith("p:")) {
            // 玩家执行
            executeAsPlayer(command.substring(2).trim(), player);
        } else if (command.startsWith("c:")) {
            // 控制台执行
            executeAsConsole(command.substring(2).trim());
        } else {
            // 默认情况下作为玩家执行
            executeAsPlayer(command, player);
        }
    }

    private void executeAsOp(String command, Player player) {
        boolean wasOp = player.isOp();
        try {
            player.setOp(true);
            player.performCommand(command);
        } finally {
            player.setOp(wasOp); // 恢复原来的OP状态
        }
    }

    private void executeAsPlayer(String command, Player player) {
        scheduler.runTask(player, () -> player.performCommand(command));
    }

    private void executeAsConsole(String command) {
        scheduler.runTask(() -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
    }
}
