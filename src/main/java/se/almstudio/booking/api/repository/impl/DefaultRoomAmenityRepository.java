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
}
