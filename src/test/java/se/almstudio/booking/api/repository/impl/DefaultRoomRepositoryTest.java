package se.almstudio.booking.api.repository.impl;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.model.entity.RoomType;

import java.time.LocalDateTime;

public class DefaultRoomRepositoryTest {
  private static Long hotelId;
  private static Long roomTypeId;
  private static Long roomTypeUpdateId;

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

    DefaultRoomTypeRepository roomTypeUpdateRepository = new DefaultRoomTypeRepository();
    RoomType roomTypeUpdate = new RoomType();
    roomTypeUpdate.setHotelId(hotelId);
    roomTypeUpdate.setName("typeTwo");
    roomTypeUpdate.setDescription("TwoBeds");
    roomTypeUpdate.setCapacity(2);
    roomTypeUpdate.setRegistered(LocalDateTime.now());
    roomTypeUpdate.setUpdated(LocalDateTime.now());
    roomTypeUpdateId = roomTypeUpdateRepository.create(roomType);
  }

  @AfterClass
  public static void afterClass() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    roomRepository.deleteRoomByHotelID(hotelId);
    DefaultRoomTypeRepository roomTypeRepository = new DefaultRoomTypeRepository();
    roomTypeRepository.delete(roomTypeId);
    roomTypeRepository.delete(roomTypeUpdateId);
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    hotelRepository.delete(hotelId);
  }

  @Test
  public void testCreateRoomExpectNoneNullId() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setRoomTypeId(roomTypeId);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    Long result = roomRepository.create(room);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }

  @Test
  public void testReadRoomByRoomIdExpectRoom() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(83);
    room.setPhoneNumber("4623784");
    room.setFloor(23);
    room.setRoomTypeId(roomTypeId);
    Long resultRoomCreated = roomRepository.create(room);
    Room roomCreated = roomRepository.findById(resultRoomCreated);
    Assert.assertEquals(room.getHotelId(), roomCreated.getHotelId());
    Assert.assertEquals(room.getNumber(), roomCreated.getNumber());
    Assert.assertEquals(room.getPhoneNumber(), roomCreated.getPhoneNumber());
    Assert.assertEquals(room.getFloor(), roomCreated.getFloor());
    Assert.assertEquals(room.getRoomTypeId(), roomCreated.getRoomTypeId());
  }

  @Test
  public void testUpdateRoomExpectTrue() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setRoomTypeId(roomTypeId);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    Long roomId = roomRepository.create(room);
    room.setNumber(88);
    room.setPhoneNumber("631826");
    room.setFloor(30);
    room.setRoomTypeId(roomTypeUpdateId);
    room.setId(roomId);
    boolean resultUpdate = roomRepository.update(room);
    Assert.assertTrue(resultUpdate);
  }

  @Test
  public void testDeleteRoomExpectTrue() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setRoomTypeId(roomTypeId);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    Long roomId = roomRepository.create(room);
    boolean resultDelete = roomRepository.delete(roomId);
    Assert.assertTrue(resultDelete);
  }
}
