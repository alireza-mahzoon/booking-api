package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.repository.HotelRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultHotelRepository implements HotelRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHotelRepository.class);

  @Override
  public Long create(Hotel hotel) {
    LOGGER.info("Creating a hotel");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO Hotel(Name, Address, City, Country, Registered) VALUES(?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, hotel.getName());
      ps.setString(2, hotel.getAddress());
      ps.setString(3, hotel.getCity());
      ps.setString(4, hotel.getCountry());
      ps.setObject(5, LocalDateTime.now());
      ps.executeUpdate();
      if (ps.getGeneratedKeys().next()) {
        LOGGER.debug("Hotel was created, the hotel information is: name: {}, address: {}, city: {}, country: {}", hotel.getName(), hotel.getAddress(), hotel.getCity(), hotel.getCountry());
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create hotel", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public Hotel findById(Long hotelId) {
    LOGGER.info("Finding hotel with Id={}", hotelId);
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM Hotel WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, hotelId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        hotel.setName(rs.getString("name"));
        hotel.setAddress(rs.getString("address"));
        hotel.setCity(rs.getString("city"));
        hotel.setCountry(rs.getString("country"));
        hotel.setRegistered(rs.getObject("registered", LocalDateTime.class));
        LOGGER.debug("Hotel was found with hotelId={}", hotelId);
        return hotel;
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find hotel with hotelId={}", hotelId, e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean update(Hotel hotel) {
    LOGGER.info("Updating a hotel information (name, address and city)");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "UPDATE Hotel SET Name=?, Address=?, City=? WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setString(1, hotel.getName());
      ps.setString(2, hotel.getAddress());
      ps.setString(3, hotel.getCity());
      ps.setLong(4, hotel.getId());
      int resultUpdated = ps.executeUpdate();
      LOGGER.debug("{} hotel was updated, information of hotel with id={} after update is: name: {}, address: {}, city: {}", resultUpdated, hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getCity());
      return resultUpdated == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to update the hotel with Id={}", hotel.getId(), e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean delete(Long id) {
    LOGGER.info("Deleting hotel with hotelId={}", id);
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "DELETE FROM Hotel WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, id);
      int result = ps.executeUpdate();
      LOGGER.debug("{} hotel was deleted when deleting with hotelId={}", result, id);
      return result == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to delete hotel with hotelId={}", id, e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
