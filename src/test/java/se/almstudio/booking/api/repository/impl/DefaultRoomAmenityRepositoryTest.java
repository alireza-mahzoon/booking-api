package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.RoomAmenity;
import se.almstudio.booking.api.model.entity.RoomType;

import java.time.LocalDateTime;

public class DefaultRoomAmenityRepositoryTest {

  private static Long hotelId;
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
    roomType.setName("typeOne");
    roomType.setDescription("oneBed");
    roomType.setCapacity(1);
    roomType.setRegistered(LocalDateTime.now());
    roomType.setUpdated(LocalDateTime.now());
    roomTypeId = roomTypeRepository.create(roomType);
  }

  @Test
  public void testCreateRoomAmenityExpectNotNull() {
    DefaultRoomAmenityRepository roomAmenityRepository = new DefaultRoomAmenityRepository();
    RoomAmenity roomAmenity = new RoomAmenity();
    roomAmenity.setRoomTypeId(roomTypeId);
    roomAmenity.setName("MiniBar");
    roomAmenity.setDescription("cola, water, vodka");
    roomAmenity.setPricing("100 SEK");
    roomAmenity.setRegistered(LocalDateTime.now());
    roomAmenity.setUpdated(LocalDateTime.now());
    Long result = roomAmenityRepository.create(roomAmenity);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }
}
