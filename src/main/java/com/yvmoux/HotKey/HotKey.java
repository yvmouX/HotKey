package com.yvmoux.HotKey;

import cn.yvmou.ylib.api.YLib;
import cn.yvmou.ylib.api.YLibCreate;
import cn.yvmou.ylib.api.scheduler.UniversalScheduler;
import cn.yvmou.ylib.api.services.LoggerService;
import org.bukkit.plugin.java.JavaPlugin;

public final class HotKey extends JavaPlugin {
    private LoggerService log;
    private UniversalScheduler scheduler;
    private Config config;
    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        register();
        log.info("插件已启用" +
                "\n==============================================" +
                "\n插件版本: " + getDescription().getVersion() +
                "\n前置：" +
                        "\n - null: " +
                "\n=============================================="
        );

    }

    @Override
    public void onDisable() {
        log.info("插件已禁用");
    }

    private void register() {
        // 1、创建必要的前置
        YLib ylib = YLibCreate.create(this);
        log = ylib.getSimpleLogger();
        scheduler = ylib.getScheduler();
        config = new Config();
        commandHandler = new CommandHandler(scheduler);

        // 2、加载默认配置
        this.saveDefaultConfig();
        config.resetValue(this);

        // 3、注册命令
        ylib.getSimpleCommandManager().registerCommands("hotkey", new ReloadCmd(this, config));

        // 4、注册监听器
        this.getServer().getPluginManager().registerEvents(new EventListener(config, commandHandler), this);
    }
}
