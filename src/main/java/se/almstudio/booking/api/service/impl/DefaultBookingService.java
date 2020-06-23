package se.almstudio.booking.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Booking;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.RoomType;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;
import se.almstudio.booking.api.service.BookingService;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookingService implements BookingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomRepository.class);

  @Override
  public List<Booking> findBooking(LocalDate checkInDate, LocalDate checkOutDate) {
    LOGGER.info("Finding list of available hotels between checkIn and checkOut date");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM booking WHERE ? < checkInDate or ? > checkOutDate";
      ps = connection.prepareStatement(query);
      ps.setObject(1, checkOutDate);
      ps.setObject(2, checkInDate);
      ps.execute();
      rs = ps.getResultSet();
      List<Booking> bookings = new ArrayList<>();
      while (rs.next()) {
        Booking booking = new Booking();
        booking.setId(rs.getLong("ID"));
        booking.setUserId(rs.getLong("userId"));
        booking.setCheckInDate(rs.getObject("checkInDate", LocalDate.class));
        booking.setCheckOutDate(rs.getObject("checkOutDate", LocalDate.class));
        booking.setHotelId(rs.getLong("hotelId"));
        booking.setRoomId(rs.getLong("roomId"));
        booking.setRegistered(rs.getObject("registered", LocalDateTime.class));
        booking.setUpdated(rs.getObject("Updated", LocalDateTime.class));
        bookings.add(booking);
      }
      LOGGER.debug("Bookings were found");
      return bookings;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find bookings");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public List<Hotel> findHotelByCityAndCountry(String city, String country) {
    LOGGER.info("Finding list of available hotels in the city and country");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM hotel WHERE city = ? AND country = ?";
      ps = connection.prepareStatement(query);
      ps.setString(1, city);
      ps.setString(2, country);
      ps.execute();
      rs = ps.getResultSet();
      List<Hotel> hotels = new ArrayList<>();
      while (rs.next()) {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getLong("ID"));
        hotel.setName(rs.getString("name"));
        hotel.setAddress(rs.getString("address"));
        hotel.setCity(rs.getString("city"));
        hotel.setCountry(rs.getString("country"));
        hotel.setRegistered(rs.getObject("registered", LocalDateTime.class));
        hotel.setUpdated(rs.getObject("Updated", LocalDateTime.class));
        hotels.add(hotel);
      }
      LOGGER.debug("hotel were found");
      return hotels;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find hotel");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public List<RoomType> findRoomType(int Capacity) {
    LOGGER.info("Finding list of available hotels with the roomType");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM roomType WHERE capacity = ?";
      ps = connection.prepareStatement(query);
      ps.setInt(1, Capacity);
      ps.execute();
      rs = ps.getResultSet();
      List<RoomType> roomTypes = new ArrayList<>();
      while (rs.next()) {
        RoomType roomType = new RoomType();
        roomType.setId(rs.getLong("ID"));
        roomType.setHotelId(rs.getLong("hotelId"));
        roomType.setName(rs.getString("name"));
        roomType.setDescription(rs.getString("description"));
        roomType.setCapacity(rs.getInt("capacity"));
        roomType.setRegistered(rs.getObject("registered", LocalDateTime.class));
        roomType.setUpdated(rs.getObject("Updated", LocalDateTime.class));
        roomTypes.add(roomType);
      }
      LOGGER.debug("roomType were found");
      return roomTypes;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find roomType");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
