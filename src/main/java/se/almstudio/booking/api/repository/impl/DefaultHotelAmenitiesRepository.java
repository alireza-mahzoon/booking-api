package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.HotelAmenities;
import se.almstudio.booking.api.repository.HotelAmenitiesRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultHotelAmenitiesRepository implements HotelAmenitiesRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHotelRepository.class);

  @Override
  public Long create(HotelAmenities hotelAmenities){
    LOGGER.info("Creating a hotel amenity");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager .INSTANCE.getConnection();
      String query = "INSERT INTO HOTELAMENITIES(HotelId, Name, Description, Pricing, Registered, Updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, hotelAmenities.getHotelId());
      ps.setString(2, hotelAmenities.getName());
      ps.setString(3, hotelAmenities.getDescription());
      ps.setString(4, hotelAmenities.getPricing());
      ps.setObject(5, LocalDateTime.now());
      ps.setObject(6, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("Hotelamenities was created");
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create a hotel", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(ps);
    }
  }
}



