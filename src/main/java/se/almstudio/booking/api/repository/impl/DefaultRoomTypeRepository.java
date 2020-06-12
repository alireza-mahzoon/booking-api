package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.RoomType;
import se.almstudio.booking.api.repository.RoomTypeRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultRoomTypeRepository implements RoomTypeRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomTypeRepository.class);

  @Override
  public Long create(RoomType roomtype) {
    LOGGER.info("Creating a roomType");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO RoomType(HotelId, Name, Description, Capacity, Registered, Updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, roomtype.getHotelId());
      ps.setString(2, roomtype.getName());
      ps.setString(3, roomtype.getDescription());
      ps.setInt(4, roomtype.getCapacity());
      ps.setObject(5, LocalDateTime.now());
      ps.setObject(6, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("RoomType was created, the roomType information is: name: {}", roomtype.getName());
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create roomType", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public RoomType findById(Long roomTypeId) {
    LOGGER.info("Finding roomType with Id={}", roomTypeId);
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT *  FROM RoomType WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, roomTypeId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        RoomType roomType = new RoomType();
        roomType.setId(roomTypeId);
        roomType.setHotelId(rs.getLong("hotelId"));
        roomType.setName(rs.getString("name"));
        roomType.setDescription(rs.getString("description"));
        roomType.setCapacity(rs.getInt("capacity"));
        roomType.setRegistered(rs.getObject("registered", LocalDateTime.class));
        LOGGER.debug("RoomType was found with roomTypeId={}", roomTypeId);
        return roomType;
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find the roomType", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean update(RoomType roomType) {
    LOGGER.info("Updating a roomType information");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "UPDATE RoomType SET HotelId=?, Name=?, Description=?, Capacity=? WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, roomType.getHotelId());
      ps.setString(2, roomType.getName());
      ps.setString(3, roomType.getDescription());
      ps.setInt(4, roomType.getCapacity());
      ps.setLong(5, roomType.getId());
      int resultUpdated = ps.executeUpdate();
      LOGGER.debug("{} room with id={} was updated", resultUpdated, roomType.getId());
      return resultUpdated == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to update the roomType", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean delete(Long roomTypeId) {
    LOGGER.info("Deleting room with roomTypeId={}", roomTypeId);
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "DELETE FROM RoomType WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, roomTypeId);
      int result = ps.executeUpdate();
      LOGGER.debug("roomType with id={} was deleted", roomTypeId);
      return result == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to delete the room with roomTypeId={}", roomTypeId, e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
