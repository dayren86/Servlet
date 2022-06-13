package data.connection;

import org.flywaydb.core.Flyway;

public class DbInitService {
    public void dbInit() {
        Prefs prefs = new Prefs();

        Flyway flyway = Flyway
                .configure()
                .dataSource(prefs.getDB_JDBC_CONNECTION_URL(), prefs.getDB_JDBC_CONNECTION_LOGIN(), prefs.getDB_JDBC_CONNECTION_PASSWORD())
                .load();
        flyway.migrate();
    }
}
