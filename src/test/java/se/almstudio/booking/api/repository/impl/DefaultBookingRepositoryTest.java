package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.model.entity.User;
import se.almstudio.booking.api.model.entity.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefaultBookingRepositoryTest {

  private static Long hotelId;
  private static Long roomId;
  private static Long userId;

  @BeforeClass
  public static void beforeClass() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    Hotel hotel = new Hotel();
    hotel.setName("Scandic");
    hotel.setCity("Stockholm");
    hotel.setCountry("Sweden");
    hotel.setAddress("Sundby");
    hotel.setRegistered(LocalDateTime.now());
    hotel.setUpdated(LocalDateTime.now());
    hotelId = hotelRepository.create(hotel);

    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setNumberOfGuest(4);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    roomId = roomRepository.create(room);

    DefaultUserRepository userRepository = new DefaultUserRepository();
    User user = new User();
    LocalDate date = LocalDate.of(1983, 4, 26);
    user.setFirstName("Herman");
    user.setLastName("Kiraly");
    user.setEmail("Herman@gmail.com");
    user.setBirthday(date);
    user.setRegistered(LocalDateTime.now());
    user.setUpdated(LocalDateTime.now());
    userId = userRepository.create(user);
  }

  @Test
  public void testCreateBookingExpectNotNull() {
    DefaultBookingRepository bookingRepository = new DefaultBookingRepository();
    Booking booking = new Booking();
    LocalDate checkIn = LocalDate.now();
    LocalDate checkOut = LocalDate.of(2019, 9, 11);
    booking.setUserId(userId);
    booking.setCheckInDate(checkIn);
    booking.setCheckOutDate(checkOut);
    booking.setHotelId(hotelId);
    booking.setRoomId(roomId);
    booking.setRegistered(LocalDateTime.now());
    booking.setUpdated(LocalDateTime.now());
    Long result = bookingRepository.create(booking);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }

  @Test
  public void testReadBookingByIdExpectBooking() {
    DefaultBookingRepository bookingRepository = new DefaultBookingRepository();
    Booking booking = new Booking();
    LocalDate checkIn = LocalDate.now();
    LocalDate checkOut = LocalDate.of(2019, 9, 11);
    booking.setUserId(userId);
    booking.setCheckInDate(checkIn);
    booking.setCheckOutDate(checkOut);
    booking.setHotelId(hotelId);
    booking.setRoomId(roomId);
    booking.setRegistered(LocalDateTime.now());
    booking.setUpdated(LocalDateTime.now());
    Long result = bookingRepository.create(booking);
    Booking bookingCreated = bookingRepository.findById(result);
    Assert.assertEquals(booking.getUserId(), bookingCreated.getUserId());
    Assert.assertEquals(booking.getCheckInDate(), bookingCreated.getCheckInDate());
    Assert.assertEquals(booking.getCheckOutDate(), bookingCreated.getCheckOutDate());
    Assert.assertEquals(booking.getHotelId(), bookingCreated.getHotelId());
    Assert.assertEquals(booking.getRoomId(), bookingCreated.getRoomId());
  }

  @Test
  public void testUpdateBookingExpectTrue() {
    DefaultBookingRepository bookingRepository = new DefaultBookingRepository();
    Booking booking = new Booking();
    LocalDate checkIn = LocalDate.now();
    LocalDate checkOut = LocalDate.of(2019, 9, 11);
    booking.setUserId(userId);
    booking.setCheckInDate(checkIn);
    booking.setCheckOutDate(checkOut);
    booking.setHotelId(hotelId);
    booking.setRoomId(roomId);
    booking.setRegistered(LocalDateTime.now());
    booking.setUpdated(LocalDateTime.now());
    Long result = bookingRepository.create(booking);
    LocalDate checkInUpdated = LocalDate.of(2020, 9, 10);
    LocalDate checkOutUpdated = LocalDate.of(2020, 9, 11);
    booking.setCheckInDate(checkInUpdated);
    booking.setCheckOutDate(checkOutUpdated);
    booking.setId(result);
    boolean resultUpdated = bookingRepository.update(booking);
    Assert.assertTrue(resultUpdated);
  }
}
