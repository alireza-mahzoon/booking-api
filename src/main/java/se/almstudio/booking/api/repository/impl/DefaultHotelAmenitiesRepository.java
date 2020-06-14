package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.HotelAmenities;
import se.almstudio.booking.api.model.entity.RoomType;
import se.almstudio.booking.api.repository.HotelAmenitiesRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultHotelAmenitiesRepository implements HotelAmenitiesRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHotelAmenitiesRepository.class);

  @Override
  public Long create(HotelAmenities hotelAmenities) {
    LOGGER.info("Creating a hotelAmenities");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO HotelAmenities(HotelId, Name, Description, Pricing, Registered, Updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, hotelAmenities.getHotelId());
      ps.setString(2, hotelAmenities.getName());
      ps.setString(3, hotelAmenities.getDescription());
      ps.setString(4, hotelAmenities.getPricing());
      ps.setObject(5, LocalDateTime.now());
      ps.setObject(6, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("HotelAmenities was created, the hotelAmenities information is: name: {}", hotelAmenities.getName());
        return ps.getGeneratedKeys().getLong(1);
      }

      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create hotelAmenities", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public HotelAmenities findById(Long hotelAmenitiesId) {
    LOGGER.info("Finding hotelAmenities with Id={}", hotelAmenitiesId);
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT *  FROM HotelAmenities WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, hotelAmenitiesId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        HotelAmenities hotelAmenities = new HotelAmenities();
        hotelAmenities.setId(hotelAmenitiesId);
        hotelAmenities.setHotelId(rs.getLong("hotelId"));
        hotelAmenities.setName(rs.getString("name"));
        hotelAmenities.setDescription(rs.getString("description"));
        hotelAmenities.setPricing(rs.getString("pricing"));
        hotelAmenities.setRegistered(rs.getObject("registered", LocalDateTime.class));
        LOGGER.debug("HotelAmenities was found with hotelAmenitiesId={}", hotelAmenitiesId);
        return hotelAmenities;
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find the hotelAmenities", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
