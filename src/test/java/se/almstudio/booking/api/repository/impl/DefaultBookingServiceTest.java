package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.*;
import se.almstudio.booking.api.service.impl.DefaultBookingService;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultBookingServiceTest {

  private static Long hotelId;
  private static Long roomTypeId;
  private static Long roomId;

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
    roomType.setName("typeOne");
    roomType.setDescription("oneBed");
    roomType.setCapacity(1);
    roomType.setRegistered(LocalDateTime.now());
    roomType.setUpdated(LocalDateTime.now());
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
  }

  @Test
  public void testFindHotelByCityAndCountryExpectNotNull() {
    DefaultBookingService bookingService = new DefaultBookingService();
    List<CityCountryRoomType> result = bookingService.findHotelByCityAndCountry("Stockholm", "Sweden", "typeOne");
    Assert.assertNotEquals(0L, result.size());
  }
}
