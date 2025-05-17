import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource datasource;
    private static Connection connection;

    static {
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/lab09" );
        config.setUsername( "postgres" );
        config.setPassword( "123456" );
        config.setAutoCommit( false );
        datasource = new HikariDataSource( config );
        try {
            connection = datasource.getConnection();
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }

    public static void rollback() {
        try {
            connection.rollback();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}