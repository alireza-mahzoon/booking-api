package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.User;
import se.almstudio.booking.api.repository.UserRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDate;
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
      String query = "INSERT INTO \"User\"(FirstName, LastName, Birthday, Email, Registered, Updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setObject(3, user.getBirthday());
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
  public User findById(Long userId) {
    LOGGER.info("Finding user with Id={}", userId);
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM \"User\" WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, userId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        User user = new User();
        user.setId(userId);
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setBirthday(rs.getObject("birthday", LocalDate.class));
        user.setEmail(rs.getString("email"));
        user.setRegistered(rs.getObject("registered", LocalDateTime.class));
        user.setUpdated(rs.getObject("updated", LocalDateTime.class));
        return user;
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find the user", e);
      throw new RuntimeException(e);
    }finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean update(User user) {
    LOGGER.info("updating a user information");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "UPDATE \"User\" SET FirstName=?, LastName=?, BirthDay=?, Email=? WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setObject(3, user.getBirthday());
      ps.setString(4, user.getEmail());
      ps.setLong(5, user.getId());
      int resultUpdated = ps.executeUpdate();
      LOGGER.debug("User was updated");
      return resultUpdated == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to update user");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean delete(Long userId) {
    LOGGER.info("Deleting a user");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "DELETE FROM \"User\" WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, userId);
      int result = ps.executeUpdate();
      LOGGER.debug("user was deleted");
      return result == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to delete user");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
