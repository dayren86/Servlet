package data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final Db INSTANCE = new Db();

    private Connection connection;

    Prefs prefs = new Prefs();

    private Db() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(prefs.getDB_JDBC_CONNECTION_URL(), prefs.getDB_JDBC_CONNECTION_LOGIN(), prefs.getDB_JDBC_CONNECTION_PASSWORD());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        new DbInitService().dbInit();
    }

    public static Db getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
