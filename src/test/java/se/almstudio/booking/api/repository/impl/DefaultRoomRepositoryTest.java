package se.almstudio.booking.api.repository.impl;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;

import java.time.LocalDateTime;

public class DefaultRoomRepositoryTest {
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
    hotel.setUpdated(LocalDateTime.now());
    hotelId = hotelRepository.create(hotel);
  }

  //@AfterClass
  //public static void afterClass() {
  //  DefaultRoomRepository roomRepository = new DefaultRoomRepository();
  //  roomRepository.deleteRoomByHotelID(hotelId);
  //  DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
  //  hotelRepository.delete(hotelId);
  //}

  @Test
  public void testCreateRoomExpectNoneNullId() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setNumberOfGuest(4);
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
    room.setNumberOfGuest(4);
    Long resultRoomCreated = roomRepository.create(room);
    Room roomCreated = roomRepository.findById(resultRoomCreated);
    Assert.assertEquals(room.getHotelId(), roomCreated.getHotelId());
    Assert.assertEquals(room.getNumber(), roomCreated.getNumber());
    Assert.assertEquals(room.getPhoneNumber(), roomCreated.getPhoneNumber());
    Assert.assertEquals(room.getFloor(), roomCreated.getFloor());
    Assert.assertEquals(room.getNumberOfGuest(), roomCreated.getNumberOfGuest());
  }

  @Test
  public void testUpdateRoomExpectTrue() {
    DefaultRoomRepository roomRepository = new DefaultRoomRepository();
    Room room = new Room();
    room.setHotelId(hotelId);
    room.setNumber(99);
    room.setPhoneNumber("964389");
    room.setFloor(25);
    room.setNumberOfGuest(4);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    Long roomId = roomRepository.create(room);
    room.setNumber(88);
    room.setPhoneNumber("631826");
    room.setFloor(30);
    room.setNumberOfGuest(5);
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
    room.setNumberOfGuest(4);
    room.setRegistered(LocalDateTime.now());
    room.setUpdated(LocalDateTime.now());
    Long roomId = roomRepository.create(room);
    boolean resultDelete = roomRepository.delete(roomId);
    Assert.assertTrue(resultDelete);
  }
}
