package se.almstudio.booking.api;

import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.repository.HotelRepository;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.repository.impl.DefaultHotelRepository;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;

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


        RoomRepository roomRepository = new DefaultRoomRepository();

        //Create room
        Room room = new Room();
        room.setHotelId(10L);
        room.setNumber(202);
        room.setPhoneNumber("2020");
        room.setFloor(12);

        Long resul = roomRepository.create(room);
        System.out.println(resul);


    }
}
