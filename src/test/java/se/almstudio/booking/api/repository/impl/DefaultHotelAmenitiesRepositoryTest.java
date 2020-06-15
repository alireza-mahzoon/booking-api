package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.HotelAmenities;
import se.almstudio.booking.api.model.entity.RoomType;

import java.time.LocalDateTime;

public class DefaultHotelAmenitiesRepositoryTest {

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
    DefaultHotelAmenitiesRepository hotelAmenitiesRepository = new DefaultHotelAmenitiesRepository();
    HotelAmenities hotelAmenities = new HotelAmenities();
    hotelAmenities.setHotelId(hotelId);
    hotelAmenities.setName("Library");
    hotelAmenities.setDescription("Open from 8 AM to 4 PM");
    hotelAmenities.setPricing("200 SEK");
    hotelAmenities.setRegistered(LocalDateTime.now());
    hotelAmenities.setUpdated(LocalDateTime.now());
    Long result = hotelAmenitiesRepository.create(hotelAmenities);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }

  @Test
  public void testReadHotelAmenitiesByIdExpectHotelAmenities() {
    DefaultHotelAmenitiesRepository hotelAmenitiesRepository = new DefaultHotelAmenitiesRepository();
    HotelAmenities hotelAmenities = new HotelAmenities();
    hotelAmenities.setHotelId(hotelId);
    hotelAmenities.setName("Library");
    hotelAmenities.setDescription("Open from 10 AM to 4 PM");
    hotelAmenities.setPricing("Free");
    hotelAmenities.setRegistered(LocalDateTime.now());
    hotelAmenities.setUpdated(LocalDateTime.now());
    Long resultHotelAmenitiesCreated = hotelAmenitiesRepository.create(hotelAmenities);
    HotelAmenities hotelAmenitiesCreated = hotelAmenitiesRepository.findById(resultHotelAmenitiesCreated);
    Assert.assertEquals(hotelAmenities.getHotelId(), hotelAmenitiesCreated.getHotelId());
    Assert.assertEquals(hotelAmenities.getName(), hotelAmenitiesCreated.getName());
    Assert.assertEquals(hotelAmenities.getDescription(), hotelAmenitiesCreated.getDescription());
    Assert.assertEquals(hotelAmenities.getPricing(), hotelAmenitiesCreated.getPricing());
  }

  @Test
  public void testUpdateHotelAmenitiesExpectTrue() {
    DefaultHotelAmenitiesRepository hotelAmenitiesRepository = new DefaultHotelAmenitiesRepository();
    HotelAmenities hotelAmenities = new HotelAmenities();
    hotelAmenities.setHotelId(hotelId);
    hotelAmenities.setName("Library");
    hotelAmenities.setDescription("Open from 10 AM to 4 PM");
    hotelAmenities.setPricing("Free");
    hotelAmenities.setRegistered(LocalDateTime.now());
    hotelAmenities.setUpdated(LocalDateTime.now());
    Long resultHotelAmenitiesCreated = hotelAmenitiesRepository.create(hotelAmenities);
    hotelAmenities.setName("Borrowing book");
    hotelAmenities.setDescription("24/7");
    hotelAmenities.setPricing("10 SEK for each book");
    hotelAmenities.setId(resultHotelAmenitiesCreated);
    boolean resultUpdate = hotelAmenitiesRepository.update(hotelAmenities);
    Assert.assertTrue(resultUpdate);
  }
}
