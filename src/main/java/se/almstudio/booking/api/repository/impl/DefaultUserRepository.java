package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.model.entity.User;
import se.almstudio.booking.api.repository.UserRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultUserRepository implements UserRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserRepository.class);


  @Override
  public Long create(User user) {
    LOGGER.info("Creating a user");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO User(firstName, lastName, birthDay, email, registered, updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setString(3, user.getBirthday());
      ps.setString(4, user.getEmail());
      ps.setObject(5, LocalDateTime.now());
      ps.setObject(6, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("User was created");
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create user", e);
      throw new RuntimeException(e);
    }  finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public Room findById(Long userId) {
    return null;
  }

  @Override
  public boolean update(User user) {
    return false;
  }

  @Override
  public boolean delete(Long userId) {
    return false;
  }
}
