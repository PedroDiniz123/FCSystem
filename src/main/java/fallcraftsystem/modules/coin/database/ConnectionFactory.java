// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coin.database;

import fallcraftsystem.core.FallCraftSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
    private static Connection connection;
    private final FallCraftSystem pl;
    
    private ConnectionFactory() throws ClassNotFoundException, SQLException {
        this.pl = FallCraftSystem.plugin;
        Class.forName("org.sqlite.JDBC");
        ConnectionFactory.connection = DriverManager.getConnection("jdbc:sqlite:" + this.pl.getDataFolder() + "/coin.db");
    }
    
    public static Connection getConnection() {
        if (ConnectionFactory.connection == null) {
            try {
                new ConnectionFactory();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return ConnectionFactory.connection;
    }
    
    static {
        ConnectionFactory.connection = null;
    }
}
