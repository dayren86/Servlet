package data.connection;

import lombok.Getter;

@Getter
public class Prefs {
    private final String DB_JDBC_CONNECTION_URL = "jdbc:postgresql://127.0.0.1:5432/company";
    private final String DB_JDBC_CONNECTION_LOGIN = "postgres";
    private final String DB_JDBC_CONNECTION_PASSWORD = "";

}
