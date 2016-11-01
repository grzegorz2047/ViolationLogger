package com.gmail.grzegorz2047.violationlogger;

import com.gmail.grzegorz2047.violationlogger.database.SQLManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by grzeg on 01.11.2016.
 */
public class ViolationLogger extends JavaPlugin {
    private SQLManager sql;
    private String arenaName;

    @Override
    public void onEnable() {
//See "Creating you're defaults"
        this.getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
        //Save the config whenever you manipulate it
        this.saveConfig();

        connect();
        this.getServer().getPluginManager().registerEvents(new NCPListener(this), this);
    }

    public void connect() {
        sql = new SQLManager(this, this.getConfig().getString("mysql.host"),
                this.getConfig().getInt("mysql.port"),
                this.getConfig().getString("mysql.user"),
                this.getConfig().getString("mysql.password"),
                this.getConfig().getString("mysql.db"));
        arenaName = this.getConfig().getString("minigame");
    }

    @Override
    public void onDisable() {
    }

    public SQLManager getSql() {
        return sql;
    }

    public String getArenaName() {
        return arenaName;
    }
}
