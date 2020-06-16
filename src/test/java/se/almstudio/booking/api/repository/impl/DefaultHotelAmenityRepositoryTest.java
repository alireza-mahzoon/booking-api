package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.HotelAmenity;

import java.time.LocalDateTime;

public class DefaultHotelAmenityRepositoryTest {

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

  @Test
  public void testCreateHotelAmenitiesExpectNoneNullId() {
    DefaultHotelAmenityRepository hotelAmenitiesRepository = new DefaultHotelAmenityRepository();
    HotelAmenity hotelAmenity = new HotelAmenity();
    hotelAmenity.setHotelId(hotelId);
    hotelAmenity.setName("Library");
    hotelAmenity.setDescription("Open from 8 AM to 4 PM");
    hotelAmenity.setPricing("200 SEK");
    hotelAmenity.setRegistered(LocalDateTime.now());
    hotelAmenity.setUpdated(LocalDateTime.now());
    Long result = hotelAmenitiesRepository.create(hotelAmenity);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }

  @Test
  public void testReadHotelAmenitiesByIdExpectHotelAmenities() {
    DefaultHotelAmenityRepository hotelAmenitiesRepository = new DefaultHotelAmenityRepository();
    HotelAmenity hotelAmenity = new HotelAmenity();
    hotelAmenity.setHotelId(hotelId);
    hotelAmenity.setName("Library");
    hotelAmenity.setDescription("Open from 10 AM to 4 PM");
    hotelAmenity.setPricing("Free");
    hotelAmenity.setRegistered(LocalDateTime.now());
    hotelAmenity.setUpdated(LocalDateTime.now());
    Long resultHotelAmenitiesCreated = hotelAmenitiesRepository.create(hotelAmenity);
    HotelAmenity hotelAmenityCreated = hotelAmenitiesRepository.findById(resultHotelAmenitiesCreated);
    Assert.assertEquals(hotelAmenity.getHotelId(), hotelAmenityCreated.getHotelId());
    Assert.assertEquals(hotelAmenity.getName(), hotelAmenityCreated.getName());
    Assert.assertEquals(hotelAmenity.getDescription(), hotelAmenityCreated.getDescription());
    Assert.assertEquals(hotelAmenity.getPricing(), hotelAmenityCreated.getPricing());
  }

  @Test
  public void testUpdateHotelAmenitiesExpectTrue() {
    DefaultHotelAmenityRepository hotelAmenitiesRepository = new DefaultHotelAmenityRepository();
    HotelAmenity hotelAmenity = new HotelAmenity();
    hotelAmenity.setHotelId(hotelId);
    hotelAmenity.setName("Library");
    hotelAmenity.setDescription("Open from 10 AM to 4 PM");
    hotelAmenity.setPricing("Free");
    hotelAmenity.setRegistered(LocalDateTime.now());
    hotelAmenity.setUpdated(LocalDateTime.now());
    Long resultHotelAmenitiesCreated = hotelAmenitiesRepository.create(hotelAmenity);
    hotelAmenity.setName("Borrowing book");
    hotelAmenity.setDescription("24/7");
    hotelAmenity.setPricing("10 SEK for each book");
    hotelAmenity.setId(resultHotelAmenitiesCreated);
    boolean resultUpdate = hotelAmenitiesRepository.update(hotelAmenity);
    Assert.assertTrue(resultUpdate);
  }

  @Test
  public void testDeleteHotelAmenitiesExpectTrue() {
    DefaultHotelAmenityRepository hotelAmenitiesRepository = new DefaultHotelAmenityRepository();
    HotelAmenity hotelAmenity = new HotelAmenity();
    hotelAmenity.setHotelId(hotelId);
    hotelAmenity.setName("Library");
    hotelAmenity.setDescription("Open from 10 AM to 4 PM");
    hotelAmenity.setPricing("Free");
    hotelAmenity.setRegistered(LocalDateTime.now());
    hotelAmenity.setUpdated(LocalDateTime.now());
    Long resultHotelAmenitiesCreated = hotelAmenitiesRepository.create(hotelAmenity);
    boolean resultDelete = hotelAmenitiesRepository.delete(resultHotelAmenitiesCreated);
    Assert.assertTrue(resultDelete);
  }
}
