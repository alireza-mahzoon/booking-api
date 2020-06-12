package se.almstudio.booking.api.repository.impl;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
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

  @Test
  public void testReadRoomTypeByIdExpectRoomType() {
    DefaultRoomTypeRepository roomTypeRepository = new DefaultRoomTypeRepository();
    RoomType roomType = new RoomType();
    roomType.setHotelId(hotelId);
    roomType.setName("typeOne");
    roomType.setDescription("fewfwe");
    roomType.setCapacity(2);
    roomType.setRegistered(LocalDateTime.now());
    Long resultRoomTypeCreated = roomTypeRepository.create(roomType);
    RoomType roomTypeCreated = roomTypeRepository.findById(resultRoomTypeCreated);
    Assert.assertEquals(roomType.getHotelId(), roomTypeCreated.getHotelId());
    Assert.assertEquals(roomType.getName(), roomTypeCreated.getName());
    Assert.assertEquals(roomType.getDescription(), roomTypeCreated.getDescription());
    Assert.assertEquals(roomType.getCapacity(), roomTypeCreated.getCapacity());
  }

  @Test
  public void testUpdateRoomTypeExpectTrue() {
    DefaultRoomTypeRepository roomTypeRepository = new DefaultRoomTypeRepository();
    RoomType roomType = new RoomType();
    roomType.setHotelId(hotelId);
    roomType.setName("typeOne");
    roomType.setDescription("oneBed");
    roomType.setCapacity(1);
    roomType.setRegistered(LocalDateTime.now());
    Long resultRoomTypeCreated = roomTypeRepository.create(roomType);
    roomType.setName("typeTwo");
    roomType.setDescription("twoBed");
    roomType.setCapacity(2);
    roomType.setId(resultRoomTypeCreated);
    boolean resultUpdate = roomTypeRepository.update(roomType);
    Assert.assertTrue(resultUpdate);
  }
}
