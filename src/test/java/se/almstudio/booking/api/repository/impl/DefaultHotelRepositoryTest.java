package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;

import java.time.LocalDateTime;

public class DefaultHotelRepositoryTest {
    @Test
    public void testCreateHotelExpectNoneNullId() {
        DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
        Hotel hotel = new Hotel();
        hotel.setName("Scandic");
        hotel.setCity("Stockholm");
        hotel.setCountry("Sweden");
        hotel.setAddress("Sundby");
        hotel.setRegistered(LocalDateTime.now());
        Long result = hotelRepository.create(hotel);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0L, result.longValue());
    }

    @Test
    public void testUpdateHotelExpectTrue(){
        DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
        Hotel hotel = new Hotel();
        hotel.setName("Scandic");
        hotel.setCity("Stockholm");
        hotel.setCountry("Sweden");
        hotel.setAddress("Sundby");
        hotel.setRegistered(LocalDateTime.now());
        Long result = hotelRepository.create(hotel);
        hotel.setName("Scandic 53");
        hotel.setId(result);
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
        Long result = hotelRepository.create(hotel);
        boolean resultDelete = hotelRepository.delete(result);
        Assert.assertTrue(resultDelete);
    }
}
