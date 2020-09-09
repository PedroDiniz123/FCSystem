// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.coin.database;

import java.sql.SQLException;
import java.sql.Statement;

public class LoadTables
{
    public static void load() throws SQLException {
        final String coinBase = "create table if not exists coins(    uuid varchar(100) primary key,    amount DOUBLE default 0 not null )";
        final Statement statement = ConnectionFactory.getConnection().createStatement();
        statement.execute(coinBase);
    }
}
