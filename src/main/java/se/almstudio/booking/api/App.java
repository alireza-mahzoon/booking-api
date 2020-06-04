package se.almstudio.booking.api;

import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.repository.HotelRepository;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.repository.impl.DefaultHotelRepository;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;

import java.util.ArrayList;
import java.util.List;

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
        //Hotel result = hotelRepository.findById(5L);
        //System.out.println(result);

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

        List<Room> rooms = roomRepository.findByHotelId(11L);
        for (Room room : rooms) {
            System.out.println(room);
        }


        //create a room
        Room room = new Room();
        room.setHotelId(11L);
        room.setNumber(77);
        room.setPhoneNumber("1111111");
        room.setFloor(10);
        room.setNumberOfGuest(8);
        Long createdRoom = roomRepository.create(room);
        System.out.println(createdRoom);

        //Read room
        Room sixtySixRoom = roomRepository.findById(createdRoom);
        System.out.println(sixtySixRoom);

        //Update the room
        room.setHotelId(19L);
        room.setNumber(88);
        room.setPhoneNumber("631826");
        room.setFloor(30);
        room.setNumberOfGuest(5);
        room.setId(createdRoom);
        roomRepository.update(room);


        //Read room
        Room roomForHotelNineteen = roomRepository.findById(createdRoom);
        System.out.println(roomForHotelNineteen);








    }
}
