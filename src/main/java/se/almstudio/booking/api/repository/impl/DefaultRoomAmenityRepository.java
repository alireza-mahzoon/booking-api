package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.RoomAmenity;
import se.almstudio.booking.api.repository.RoomAmenityRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultRoomAmenityRepository implements RoomAmenityRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomAmenityRepository.class);

  @Override
  public Long create(RoomAmenity roomAmenity) {
    LOGGER.info("Creating a roomAmenity");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO RoomAmenity(roomTypeId, Name, Description, Pricing, Registered, Updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, roomAmenity.getRoomTypeId());
      ps.setString(2, roomAmenity.getName());
      ps.setString(3, roomAmenity.getDescription());
      ps.setString( 4, roomAmenity.getPricing());
      ps.setObject( 5, LocalDateTime.now());
      ps.setObject(6, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("RoomAmenity was created");
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create roomAmenity");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public RoomAmenity findById(Long roomAmenityId) {
    LOGGER.info("Finding room amenity");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM RoomAmenity WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, roomAmenityId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        RoomAmenity roomAmenity = new RoomAmenity();
        roomAmenity.setId(roomAmenityId);
        roomAmenity.setRoomTypeId(rs.getLong("roomTypeId"));
        roomAmenity.setName(rs.getString("name"));
        roomAmenity.setDescription(rs.getString("description"));
        roomAmenity.setPricing(rs.getString("pricing"));
        LOGGER.debug("Room amenity was found");
        return roomAmenity;
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find room amenity", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean update(RoomAmenity roomAmenity) {
    LOGGER.info("Updating a roomAmenity information");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "UPDATE RoomAmenity SET RoomTypeId=?, Name=?, Description=?, Pricing=? WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, roomAmenity.getRoomTypeId());
      ps.setString(2, roomAmenity.getName());
      ps.setString(3, roomAmenity.getDescription());
      ps.setString(4, roomAmenity.getPricing());
      int resultUpdated = ps.executeUpdate();
      LOGGER.debug("Room amenity was updated");
      return resultUpdated == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to update the room amenity", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
