package se.almstudio.booking.api.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Booking;
import se.almstudio.booking.api.model.entity.User;
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

  @Override
  public Booking findById(Long bookingId) {
    LOGGER.info("Finding a booking information");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * FROM Booking WHERE id = ?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, bookingId);
      ps.execute();
      rs = ps.getResultSet();
      if (rs.next()) {
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setUserId(rs.getLong("userId"));
        booking.setCheckInDate(rs.getObject("checkInDate", LocalDate.class));
        booking.setCheckOutDate(rs.getObject("checkOutDate", LocalDate.class));
        booking.setHotelId(rs.getLong("hotelId"));
        booking.setRoomId(rs.getLong("roomId"));
        booking.setRegistered(rs.getObject("registered", LocalDateTime.class));
        booking.setUpdated(rs.getObject("updated", LocalDateTime.class));
        LOGGER.debug("Booking was found");
        return booking;
      }
      return null;
      } catch (SQLException e) {
      LOGGER.warn("Failed to find the booking", e);
      throw  new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }

  @Override
  public boolean update(Booking booking) {
    LOGGER.info("Updating booking information");
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "UPDATE Booking SET UserId=?, CheckInDate=?, CheckOutDate=?, HotelId=?, RoomId=? WHERE id=?";
      ps = connection.prepareStatement(query);
      ps.setLong(1, booking.getUserId());
      ps.setObject(2, booking.getCheckInDate());
      ps.setObject(3, booking.getCheckOutDate());
      ps.setLong(4, booking.getHotelId());
      ps.setLong(5, booking.getRoomId());
      ps.setLong(6, booking.getId());
      int resultUpdated = ps.executeUpdate();
      LOGGER.debug("Booking was updated");
      return resultUpdated == 1;
    } catch (SQLException e) {
      LOGGER.warn("Failed to update booking", e);
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
