package se.almstudio.booking.api.repository.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;

import java.time.LocalDateTime;

public class DefaultHotelRepositoryTest {

  private Long hotelId;

  @After
  public void after() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    hotelRepository.delete(hotelId);
  }

  @Test
  public void testCreateHotelExpectNoneNullId() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    Hotel hotel = new Hotel();
    hotel.setName("Scandic");
    hotel.setCity("Stockholm");
    hotel.setCountry("Sweden");
    hotel.setAddress("Sundby");
    hotel.setRegistered(LocalDateTime.now());
    hotelId = hotelRepository.create(hotel);
    Assert.assertNotNull(hotelId);
    Assert.assertNotEquals(0L, hotelId.longValue());
  }

  @Test
  public void testReadHotelByHotelIdExpectHotel() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    Hotel hotel = new Hotel();
    hotel.setName("Scandic");
    hotel.setCity("Stockholm");
    hotel.setCountry("Sweden");
    hotel.setAddress("Sundby");
    hotelId = hotelRepository.create(hotel);
    Hotel resultHotel = hotelRepository.findById(hotelId);
    Assert.assertEquals(hotel.getName(), resultHotel.getName());
    Assert.assertEquals(hotel.getCity(), resultHotel.getCity());
    Assert.assertEquals(hotel.getCountry(), resultHotel.getCountry());
    Assert.assertEquals(hotel.getAddress(), resultHotel.getAddress());
  }

  @Test
  public void testUpdateHotelExpectTrue() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    Hotel hotel = new Hotel();
    hotel.setName("Scandic");
    hotel.setCity("Stockholm");
    hotel.setCountry("Sweden");
    hotel.setAddress("Sundby");
    hotel.setRegistered(LocalDateTime.now());
    hotelId = hotelRepository.create(hotel);
    hotel.setName("Scandic 53");
    hotel.setId(hotelId);
    boolean resultUpdate = hotelRepository.update(hotel);
    Assert.assertTrue(resultUpdate);
  }

  @Test
  public void testDeleteHotelByIdExpectTrue() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    Hotel hotel = new Hotel();
    hotel.setName("Scandic");
    hotel.setCity("Stockholm");
    hotel.setCountry("Sweden");
    hotel.setAddress("Sundby");
    hotel.setRegistered(LocalDateTime.now());
    hotelId = hotelRepository.create(hotel);

    boolean resultDelete = hotelRepository.delete(hotelId);
    Assert.assertTrue(resultDelete);
  }
}
