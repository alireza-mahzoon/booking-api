package se.almstudio.booking.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.almstudio.booking.api.model.entity.CityCountryRoomType;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.model.entity.RoomType;
import se.almstudio.booking.api.model.rest.AvailableRooms;
import se.almstudio.booking.api.model.rest.BookingOffer;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;
import se.almstudio.booking.api.service.BookingService;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookingService implements BookingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRoomRepository.class);

  @Override
  public List<CityCountryRoomType> findHotelByCityAndCountry(String city, String country, String name) {
    LOGGER.info("Finding list of available hotels in the city and country");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * from hotel join roomtype ON hotel.id = roomtype.hotelid WHERE hotel.city= ? AND hotel.country = ? AND roomType.name = ?";
      ps = connection.prepareStatement(query);
      ps.setString(1, city);
      ps.setString(2, country);
      ps.setString(3, name);
      ps.execute();
      rs = ps.getResultSet();
      List<CityCountryRoomType> hotels = new ArrayList<>();
      while (rs.next()) {
        CityCountryRoomType cityCountryRoomType = new CityCountryRoomType();
        cityCountryRoomType.setCity(rs.getString("city"));
        cityCountryRoomType.setCountry(rs.getString("country"));
        cityCountryRoomType.setName(rs.getString("name"));
        hotels.add(cityCountryRoomType);
      }
      LOGGER.debug("{} hotel were found with city={}, country={} and roomType={}", hotels.size(), city, country, name);
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
  public BookingOffer findOffer(String city, String country) {
    LOGGER.info("Finding list of available rooms in the city and country");
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = ConnectionManager.INSTANCE.getConnection();
      String query = "SELECT * from hotel join room ON hotel.id = room.hotelid WHERE hotel.city= ? AND hotel.country = ?";
      ps = connection.prepareStatement(query);
      ps.setString(1, city);
      ps.setString(2, country);
      ps.execute();
      rs = ps.getResultSet();

      List<AvailableRooms> rooms = new ArrayList<>();
      List<RoomType> roomTypes = new ArrayList<>();
      BookingOffer bookingOffer = new BookingOffer();

      while (rs.next()) {
        AvailableRooms availableRooms = new AvailableRooms();
        RoomType roomType = new RoomType();
        Hotel hotel = new Hotel();
        hotel.setId(rs.getLong("ID"));
        roomType.setId(rs.getLong("roomTypeId"));
        roomTypes.add(roomType);
        availableRooms.setHotel(hotel);
        availableRooms.setRoomTypes(roomTypes);
        rooms.add(availableRooms);
        bookingOffer.setAvailableRooms(rooms);
      }
      LOGGER.debug("{} booking offer were found with city={}, country={}", rooms.size(), city, country);
      return bookingOffer;
    } catch (SQLException e) {
      LOGGER.warn("Failed to find rooms");
      throw new RuntimeException(e);
    } finally {
      ConnectionUtils.closeQuietly(rs);
      ConnectionUtils.closeQuietly(ps);
      ConnectionUtils.closeQuietly(connection);
    }
  }
}
