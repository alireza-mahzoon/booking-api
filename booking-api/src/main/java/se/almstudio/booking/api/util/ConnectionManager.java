package se.almstudio.booking.api.util;

import se.almstudio.booking.api.util.impl.DefaultConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    ConnectionManager INSTANCE = new DefaultConnectionManager();

    /**
     * Get new connection
     * @return Connection to database
     * @throws SQLException if failed to retrieve a new connection
     */
    Connection getConnection() throws SQLException;
}
