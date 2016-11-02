package com.gmail.grzegorz2047.violationlogger.database;

import com.gmail.grzegorz2047.violationlogger.Violation;
import com.gmail.grzegorz2047.violationlogger.ViolationLogger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.*;

public class SQLManager {

    private ViolationLogger plugin;

    private Connection connection;
    private PreparedStatement statement;


    // public String generateStringAutoInc(){
    //     if(!GenConf.DATABASE.equals(Database.FILE)) return "INT PRIMARY KEY AUTO_INCREMENT"; else return "INTEGER PRIMARY KEY AUTOINCREMENT";
    // }
    //public String generateDefVal(){
    //     if(!GenConf.DATABASE.equals(Database.FILE)) return "0"; else return "null";
    // }
    public SQLManager(ViolationLogger plugin, String host, int port, String user, String password, String db) {
        this.plugin = plugin;

        /*// switch(GenConf.DATABASE) {
        //     case FILE:
        Bukkit.getLogger().info("[SQLite] Connecting to SQLite database ...");
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + "ViolationLogger");
            if (this.connection != null) {
                Bukkit.getLogger().info("[SQLite] Connected to SQLite successfully!");
                this.startWork();
            }
        } catch (ClassNotFoundException ex) {
            Bukkit.getLogger().info("[SQLite] Connecting with SQLite failed! We were unable to load driver 'org.sqlite.JDBC'.");
        } catch (SQLException | InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            Bukkit.getLogger().info("[SQLite] Connecting with SQLite failed! Permission error: " + ex.getMessage());
        }
        // break;
            case MYSQL:*/
        Bukkit.getLogger().info("[MySQL] Connecting to MySQL database ...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true", user, password);
            this.statement = this.connection.prepareStatement("Select * FROM ViolationLogger WHERE 1");
            Bukkit.getLogger().info("[MySQL] Connected to MySQL successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Bukkit.getLogger().info("[MySQL] Connecting with MySQL failed! We were unable to load driver 'com.mysql.jdbc.Driver'.");
        }
        // break;
        //default:
        // Bukkit.getLogger().severe("[MySQL] Invalid database type '" + GenConf.DATABASE.name() + "'!");
        // break;
        //}

    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void addViolation(Violation violation) {
        try {
            String player = violation.getPlayer();
            String violationType = violation.getViolationType();
            int power = violation.getPower();
            String arena = violation.getArena();
            statement = this.connection.prepareStatement("INSERT INTO ViolationLogger (id, player, violation, power, arena) VALUES(?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet set = statement.getGeneratedKeys();
            int i = 0;
            if (set.next()) {
                i = set.getInt(1);
            }
            statement.setInt(1, i);
            statement.setString(2, player);
            statement.setString(3, violationType);
            statement.setInt(4, power);
            statement.setString(5, arena);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isConnectionClosed() {
        try {
            return this.connection == null || this.connection.isClosed();
        } catch (SQLException ex) {
            return true;
        }
    }

    public void closeConnection() {
        try {
            if (!isConnectionClosed()) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
/*
    public User getPlayer(Player p) {
        try {
            statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `" + sqlTablePrefix + "players` WHERE `username`='" + p.getName() + "'");
            result.next();
            String username = result.getString("username");
            int kills = result.getInt("kills");
            int deaths = result.getInt("deaths");
            int money = result.getInt("money");
            //System.out.println("Pobrane wartosci to "+kills+ " "+ deaths+" "+ money);
            return new User(username, kills, deaths, money);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
*/
}


