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
      String query = "INSERT INTO RoomType(HotelId, Name, Description, Capacity, Registered) VALUES(?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, roomtype.getHotelId());
      ps.setString  (2, roomtype.getName());
      ps.setString(3, roomtype.getDescription());
      ps.setInt(4, roomtype.getCapacity());
      ps.setObject(5, LocalDateTime.now());
      ps.executeUpdate();
      if(ps.getGeneratedKeys().next()) {
        LOGGER.debug("RoomType was created, the roomType information is: name: {}",roomtype.getName());
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
}
