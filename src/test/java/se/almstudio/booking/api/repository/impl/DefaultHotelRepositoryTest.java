package se.almstudio.booking.api.repository.impl;

import org.junit.*;
import se.almstudio.booking.api.model.entity.Hotel;

import java.time.LocalDateTime;

public class DefaultHotelRepositoryTest {

  private static Long hotelIdDeleted;
  private static Long hotelIdCreated;
  private static Long hotelIdRead;
  private static Long hotelIdUpdated;
  
  @AfterClass
  public static void afterClass() {
    DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
    hotelRepository.delete(hotelIdDeleted);
    hotelRepository.delete(hotelIdCreated);
    hotelRepository.delete(hotelIdRead);
    hotelRepository.delete(hotelIdUpdated);
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
        hotelIdCreated = hotelRepository.create(hotel);
        Assert.assertNotNull(hotelIdCreated);
        Assert.assertNotEquals(0L, hotelIdCreated.longValue());
    }

    @Test
    public void testReadHotelByHotelIdExpectHotel() {
        DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
        Hotel hotel = new Hotel();
        hotel.setName("Scandic");
        hotel.setCity("Stockholm");
        hotel.setCountry("Sweden");
        hotel.setAddress("Sundby");
        hotelIdRead = hotelRepository.create(hotel);
        Hotel resultHotel = hotelRepository.findById(hotelIdRead);
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
        hotelIdUpdated = hotelRepository.create(hotel);
        hotel.setName("Scandic 53");
        hotel.setId(hotelIdUpdated);
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
        hotelIdDeleted = hotelRepository.create(hotel);

        boolean resultDelete = hotelRepository.delete(hotelIdDeleted);
        Assert.assertTrue(resultDelete);
    }
}
