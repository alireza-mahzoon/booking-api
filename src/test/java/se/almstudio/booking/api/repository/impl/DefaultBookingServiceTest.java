package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.*;
import se.almstudio.booking.api.model.rest.BookingOffer;
import se.almstudio.booking.api.service.impl.DefaultBookingService;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultBookingServiceTest {

  private static Long hotelId;
  private static Long roomTypeIdOne;
  private static Long roomTypeIdTwo;
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
    RoomType roomTypeOne = new RoomType();
    roomTypeOne.setHotelId(hotelId);
    roomTypeOne.setName("typeOne");
    roomTypeOne.setDescription("oneBed");
    roomTypeOne.setCapacity(1);
    roomTypeOne.setRegistered(LocalDateTime.now());
    roomTypeOne.setUpdated(LocalDateTime.now());
    roomTypeIdOne = roomTypeRepository.create(roomTypeOne);

    RoomType roomTypeTwo = new RoomType();
    roomTypeTwo.setHotelId(hotelId);
    roomTypeTwo.setName("typeTwo");
    roomTypeTwo.setDescription("twoBeds");
    roomTypeTwo.setCapacity(2);
    roomTypeTwo.setRegistered(LocalDateTime.now());
    roomTypeTwo.setUpdated(LocalDateTime.now());
    roomTypeIdTwo = roomTypeRepository.create(roomTypeTwo);

    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setRoomTypeId(roomTypeIdTwo);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    roomId = roomRepository.create(room);
  }

  @Test
  public void testFindHotelByCityAndCountryExpectNotNull() {
    DefaultBookingService bookingService = new DefaultBookingService();
    List<CityCountryRoomType> result = bookingService.findHotelByCityAndCountry("Stockholm", "Sweden", "typeTwo");
    Assert.assertNotEquals(0L, result.size());
  }

  @Test
  public void testFindOfferByCityAndCountryExpectNotNull() {
    DefaultBookingService bookingService = new DefaultBookingService();
    BookingOffer result = bookingService.findOffer("Stockholm","Sweden");
    Assert.assertNotNull(result);
  }
}
