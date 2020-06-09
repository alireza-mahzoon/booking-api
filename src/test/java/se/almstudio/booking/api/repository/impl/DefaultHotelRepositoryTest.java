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
    public void testReadHotelByHotelIdExpectHotel() {
        DefaultHotelRepository hotelRepository = new DefaultHotelRepository();
        Hotel hotel = new Hotel();
        hotel.setName("Scandic");
        hotel.setCity("Stockholm");
        hotel.setCountry("Sweden");
        hotel.setAddress("Sundby");
        Long result = hotelRepository.create(hotel);
        Hotel resultHotel = hotelRepository.findById(result);
        Assert.assertEquals(hotel.getName(),resultHotel.getName());
        Assert.assertEquals(hotel.getCity(),resultHotel.getCity());
        Assert.assertEquals(hotel.getCountry(),resultHotel.getCountry());
        Assert.assertEquals(hotel.getAddress(),resultHotel.getAddress());
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
