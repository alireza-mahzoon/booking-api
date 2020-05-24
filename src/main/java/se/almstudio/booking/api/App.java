package se.almstudio.booking.api;

import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.repository.HotelRepository;
import se.almstudio.booking.api.repository.impl.DefaultHotelRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Create Read Update Read Delete Read
        HotelRepository hotelRepository = new DefaultHotelRepository();
        Hotel result = hotelRepository.findById(5L);
        System.out.println(result);


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

        //Read
        Hotel resultEastHotel = hotelRepository.findById(resultID);
        System.out.println(resultEastHotel);

        //delete
        hotelRepository.delete(resultID);
    }
}
