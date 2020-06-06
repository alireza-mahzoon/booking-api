package se.almstudio.booking.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.repository.HotelRepository;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.repository.impl.DefaultHotelRepository;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;
import se.almstudio.booking.api.service.impl.DefaultHotelService;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        DefaultHotelService hotelService = new DefaultHotelService();
        hotelService.delete(11L);

        HotelRepository hotelRepository = new DefaultHotelRepository();

        //Create
        Hotel hotel = new Hotel();
        hotel.setName("West Hotel");
        hotel.setAddress("Solna 31");
        hotel.setCity("Stockholm");
        hotel.setCountry("Country");
        Long resultID = hotelRepository.create(hotel);
        System.out.println(resultID);

        //Read
        Hotel resultWestHotel = hotelRepository.findById(resultID);
        System.out.println(resultWestHotel);

        //Update
        hotel.setName("East Hotel");
        hotel.setId(resultID);
        hotelRepository.update(hotel);

    }
}
