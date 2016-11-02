package com.gmail.grzegorz2047.violationlogger.database;

import com.gmail.grzegorz2047.violationlogger.Violation;
import com.gmail.grzegorz2047.violationlogger.ViolationLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by grzeg on 02.11.2016.
 */
public class ViolationProcessing extends Thread {

    private final ViolationLogger plugin;
    private SQLManager sql;
    private String arenaName;
    private final BlockingQueue<Violation> queryQueue = new ArrayBlockingQueue<Violation>(1024);

    public ViolationProcessing(ViolationLogger plugin) {
        this.plugin = plugin;
        connect();
    }

    private void connect() {
        sql = new SQLManager(plugin, plugin.getConfig().getString("mysql.host"),
                plugin.getConfig().getInt("mysql.port"),
                plugin.getConfig().getString("mysql.user"),
                plugin.getConfig().getString("mysql.password"),
                plugin.getConfig().getString("mysql.db"));
        arenaName = plugin.getConfig().getString("minigame");
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                try {
                    Violation violation = this.queryQueue.take();
                    this.sql.addViolation(violation);
                } catch (InterruptedException ignored) {
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Throwable ex) {

        }
    }

    public void addViolation(String message) {
        Violation violation = new Violation(message, arenaName);
        this.queryQueue.add(violation);
    }
}
