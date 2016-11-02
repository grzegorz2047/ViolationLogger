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
//See "Creating you're defaults"
        this.getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
        //Save the config whenever you manipulate it
        this.saveConfig();
        this.processor = new ViolationProcessing(this);
        this.getServer().getPluginManager().registerEvents(new NCPListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    public ViolationProcessing getProcessor() {
        return processor;
    }
}
