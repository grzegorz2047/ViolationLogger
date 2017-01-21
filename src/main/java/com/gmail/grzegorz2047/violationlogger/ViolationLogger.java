package com.gmail.grzegorz2047.violationlogger;

import com.gmail.grzegorz2047.violationlogger.database.SQLManager;
import com.gmail.grzegorz2047.violationlogger.database.ViolationProcessing;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by grzeg on 01.11.2016.
 */
public class ViolationLogger extends JavaPlugin {

    private ViolationProcessing processor;

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.processor = new ViolationProcessing(this);
        processor.start();
        this.getServer().getPluginManager().registerEvents(new NCPListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    public ViolationProcessing getProcessor() {
        return processor;
    }
}
