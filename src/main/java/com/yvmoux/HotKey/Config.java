package com.yvmoux.HotKey;

import java.util.List;

public class Config {
    public List<String> shiftF;
    public List<String> shiftQ;
    public List<String> f;
    public List<String> q;
    public boolean shiftFEnable;
    public boolean shiftQEnable;
    public boolean fEnable;
    public boolean qEnable;

    public Config() {
    }


    public void resetValue(HotKey plugin) {
        shiftF = plugin.getConfig().getStringList("Shift+F");
        shiftQ = plugin.getConfig().getStringList("Shift+Q");
        f = plugin.getConfig().getStringList("F");
        q = plugin.getConfig().getStringList("Q");
        shiftFEnable = plugin.getConfig().getBoolean("Shift+F_Enable");
        shiftQEnable = plugin.getConfig().getBoolean("Shift+Q_Enable");
        fEnable = plugin.getConfig().getBoolean("F_Enable");
        qEnable = plugin.getConfig().getBoolean("Q_Enable");
    }
}
