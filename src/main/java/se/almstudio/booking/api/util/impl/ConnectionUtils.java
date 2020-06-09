package se.almstudio.booking.api.util.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtils {
  public static void closeQuietly(PreparedStatement ps) {
    try {
      if (ps != null)
        ps.close();
    } catch (SQLException ignored) {
    }
  }

  public static void closeQuietly(Connection connection) {
    try {
      if (connection != null)
        connection.close();
    } catch (SQLException ignored) {
    }
  }

  public static void closeQuietly(ResultSet rs) {
    try {
      if (rs != null)
        rs.close();
    } catch (SQLException ignored) {
    }
  }
}
