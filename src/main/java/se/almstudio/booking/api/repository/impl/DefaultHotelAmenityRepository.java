package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.HotelAmenity;
import se.almstudio.booking.api.repository.HotelAmenityRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultHotelAmenityRepository implements HotelAmenityRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHotelAmenityRepository.class);

  @Override
  public Long create(HotelAmenity hotelAmenity) {
    LOGGER.info("Creating a hotelAmenities");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO HotelAmenity(HotelId, Name, Description, Pricing, Registered, Updated) VALUES(?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, hotelAmenity.getHotelId());
      ps.setString(2, hotelAmenity.getName());
      ps.setString(3, hotelAmenity.getDescription());
      ps.setString(4, hotelAmenity.getPricing());
      ps.setObject(5, LocalDateTime.now());
      ps.setObject(6, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("Hotel Amenity was created, the hotelAmenity information is: name: {}", hotelAmenity.getName());
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create hotelAmenity", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public HotelAmenity findById(Long hotelAmenitiesId) {
    LOGGER.info("Finding hotelAmenitiesId with Id={}", hotelAmenitiesId);
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT *  FROM HotelAmenity WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, hotelAmenitiesId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setId(hotelAmenitiesId);
        hotelAmenity.setHotelId(rs.getLong("hotelId"));
        hotelAmenity.setName(rs.getString("name"));
        hotelAmenity.setDescription(rs.getString("description"));
        hotelAmenity.setPricing(rs.getString("pricing"));
        hotelAmenity.setRegistered(rs.getObject("registered", LocalDateTime.class));
        LOGGER.debug("HotelAmenity was found with hotelAmenitiesId={}", hotelAmenitiesId);
        return hotelAmenity;
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find the hotelAmenitiesId", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean update(HotelAmenity hotelAmenity) {
    LOGGER.info("Updating a hotelAmenities information");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "UPDATE HotelAmenity SET HotelId=?, Name=?, Description=?, Pricing=? WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, hotelAmenity.getHotelId());
      ps.setString(2, hotelAmenity.getName());
      ps.setString(3, hotelAmenity.getDescription());
      ps.setString(4, hotelAmenity.getPricing());
      ps.setLong(5, hotelAmenity.getId());
      int resultUpdated = ps.executeUpdate();
      LOGGER.debug("{} hotelAmenity with id={} was updated", resultUpdated, hotelAmenity.getId());
      return resultUpdated == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to update the hotelAmenities", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean delete(Long hotelAmenitiesId) {
    LOGGER.info("Deleting hotelAmenity with hotelAmenitiesId={}", hotelAmenitiesId);
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "DELETE FROM HotelAmenity WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, hotelAmenitiesId);
      int result = ps.executeUpdate();
      LOGGER.debug("hotelAmenities with id={} was deleted", hotelAmenitiesId);
      return result == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to delete the room with hotelAmenitiesId={}", hotelAmenitiesId, e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
