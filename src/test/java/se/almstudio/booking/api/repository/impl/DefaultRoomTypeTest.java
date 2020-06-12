package se.almstudio.booking.api.repository.impl;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.RoomType;

import java.time.LocalDateTime;

public class DefaultRoomTypeTest {

  private static Long hotelId;

  @BeforeClass
  public static void beforeClass() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    Hotel hotel = new Hotel();
    hotel.setName("Scandic");
    hotel.setCity("Stockholm");
    hotel.setCountry("Sweden");
    hotel.setAddress("Sundby");
    hotel.setRegistered(LocalDateTime.now());
    hotelId = hotelRepository.create(hotel);
  }

  @Test
  public void testCreateRoomTypeExpectNotNullId() {
    DefaultRoomTypeRepository roomTypeRepository = new DefaultRoomTypeRepository();
    RoomType roomType = new RoomType();
    roomType.setHotelId(hotelId);
    roomType.setName("typeOne");
    roomType.setDescription("fewfwe");
    roomType.setCapacity(2);
    roomType.setRegistered(LocalDateTime.now());
    Long result = roomTypeRepository.create(roomType);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }
}
