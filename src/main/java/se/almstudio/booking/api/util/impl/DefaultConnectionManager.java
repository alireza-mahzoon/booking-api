package se.almstudio.booking.api.util.impl;

import se.almstudio.booking.api.util.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultConnectionManager implements ConnectionManager {
  @Override
  public Connection getConnection() throws SQLException {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookme",
        "postgres", "postgres");
    } catch (ClassNotFoundException e) {
      throw new SQLException(e);
    }
  }
}
