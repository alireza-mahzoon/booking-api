package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Booking;
import se.almstudio.booking.api.repository.BookingRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DefaultBookingRepository implements BookingRepository{
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBookingRepository.class);

  @Override
  public Long create(Booking booking) {
    LOGGER.info("Creating a booking");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "INSERT INTO Booking(UserId, CheckinDate, CheckoutDate, HotelId, RoomId, Registered, Updated) VALUES(?,?,?,?,?,?,?)";
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, booking.getUserId());
      ps.setObject(2, booking.getCheckInDate());
      ps.setObject(3, booking.getCheckOutDate());
      ps.setLong(4, booking.getHotelId());
      ps.setLong(5, booking.getRoomId());
      ps.setObject(6, LocalDateTime.now());
      ps.setObject(7, LocalDateTime.now());
      ps.executeUpdate();
      if(ps.getGeneratedKeys().next()) {
        LOGGER.debug("Booking was created");
        return ps.getGeneratedKeys().getLong(1);
      }
      return null;
    } catch (SQLException e) {
      LOGGER.warn("Failed to create Booking", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

}
