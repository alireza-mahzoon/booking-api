package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.HotelAmenities;

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
    hotelId = hotelRepository.create(hotel);
  }


  @Test
  public void testCreateHotelAmenitiesExpectNotNullId() {
    DefaultHotelAmenitiesRepository hotelAmenitiesRepository = new DefaultHotelAmenitiesRepository();
    HotelAmenities hotelAmenities = new HotelAmenities();
    hotelAmenities.setHotelId(hotelId);
    hotelAmenities.setName("Library");
    hotelAmenities.setDescription("Opens from 10 AM to 4 PM");
    hotelAmenities.setPricing("Free");
    hotelAmenities.setRegistered(LocalDateTime.now());
    Long result = hotelAmenitiesRepository.create(hotelAmenities);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }
}
