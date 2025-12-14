package com.yvmoux.HotKey;

import cn.yvmou.ylib.api.command.CommandOptions;
import cn.yvmou.ylib.api.command.SubCommand;
import org.bukkit.command.CommandSender;

public class ReloadCmd implements SubCommand {
    private final HotKey plugin;
    private final Config config;

    public ReloadCmd(HotKey plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }
    @Override
    @CommandOptions(name = "reload", permission = "hotkey.command.reload", onlyPlayer = false, alias = {}, register = true, usage = "/hotkey reload")
    public boolean execute(CommandSender commandSender, String[] strings) {
        plugin.reloadConfig();
        config.resetValue(plugin);
        commandSender.sendMessage("已重载HotkeyCommand插件");
        return true;
    }
}
