package com.gmail.grzegorz2047.violationlogger;

import fr.neatmonster.nocheatplus.NCPEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by grzeg on 01.11.2016.
 */
public class NCPListener implements Listener {

    private final ViolationLogger plugin;

    public NCPListener(ViolationLogger plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNCP(NCPEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("cg.admin") || p.hasPermission("nocheatplus.admin")) {
                p.sendMessage(e.getMessage());
            }
        }
        //Configure prefix:violation:player:power: + arena
        plugin.getProcessor().addViolation(e.getMessage());
    }
}
