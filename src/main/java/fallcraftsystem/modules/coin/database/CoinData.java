// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coin.database;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinData
{
    public static double getCoins(final Player player) throws SQLException {
        double ret = 0.0;
        final String query = "select * from coins where uuid = ?";
        final PreparedStatement sets = ConnectionFactory.getConnection().prepareStatement(query);
        sets.setString(1, player.getUniqueId().toString());
        final ResultSet results = sets.executeQuery();
        while (results.next()) {
            ret = results.getDouble("amount");
        }
        return ret;
    }
    
    public static void insert(final Player player) throws SQLException {
        final String newPlayer = "insert into coins (uuid, amount) values (? , ?)";
        final PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);
        preparedStatement.setString(1, player.getUniqueId().toString());
        preparedStatement.setDouble(2, 0.0);
        preparedStatement.execute();
    }
    
    public static void addCoins(final Player player, final double amount) throws SQLException {
        final String newPlayer = "update coins set amount = amount + " + amount + " where uuid = '" + player.getUniqueId().toString() + "'";
        final PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);
        preparedStatement.executeUpdate();
    }
    
    public static void takeCoins(final Player player, final double amount) throws SQLException {
        final String newPlayer = "update coins set amount = amount - " + amount + " where uuid = '" + player.getUniqueId().toString() + "'";
        final PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);
        preparedStatement.execute();
    }
}
