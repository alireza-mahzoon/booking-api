package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.*;
import se.almstudio.booking.api.service.impl.DefaultBookingService;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DefaultBookingServiceTest {

  /*private static Long hotelId;
  private static Long roomId;
  private static Long userId;
  private static Long roomTypeId;

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

    DefaultRoomTypeRepository roomTypeRepository = new DefaultRoomTypeRepository();
    RoomType roomType = new RoomType();
    roomType.setHotelId(hotelId);
    roomType.setCapacity(3);
    roomType.setDescription("Two beds");
    roomType.setName("Type One");
    roomTypeId = roomTypeRepository.create(roomType);

    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setRoomTypeId(roomTypeId);
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

    DefaultBookingRepository bookingRepository = new DefaultBookingRepository();
    Booking booking = new Booking();
    LocalDate checkIn = LocalDate.of(2019, 9, 10);
    LocalDate checkOut = LocalDate.of(2019, 9, 11);
    booking.setUserId(userId);
    booking.setCheckInDate(checkIn);
    booking.setCheckOutDate(checkOut);
    booking.setHotelId(hotelId);
    booking.setRoomId(roomId);
    booking.setRegistered(LocalDateTime.now());
    booking.setUpdated(LocalDateTime.now());
  }*/

  @Test
  public void testFindHotelByCityAndCountryExpectNotNull() {
    DefaultBookingService bookingService = new DefaultBookingService();
    List<CityCountryRoomType> result = bookingService.findHotelByCityAndCountry("Lake Gabriel", "Mayotte", "Dynamic Usability Manager" );
    Assert.assertNotEquals(0L, result.size());
  }
}
