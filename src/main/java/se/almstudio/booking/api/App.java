package se.almstudio.booking.api;

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
    public static void main( String[] args )
    {
        DefaultHotelService hotelService = new DefaultHotelService();
        hotelService.delete(11L);
    }
}
