package se.almstudio.booking.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;

/**
 * Hello world!
 */
public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    //DefaultHotelService hotelService = new DefaultHotelService();
    //hotelService.delete(11L);

    //HotelRepository hotelRepository = new DefaultHotelRepository();


    RoomRepository roomRepository = new DefaultRoomRepository();

    Room room = new Room();
    room.setHotelId(17L);
    room.setNumber(177);
    room.setPhoneNumber("642398649823");
    room.setFloor(12);
    room.setNumberOfGuest(4);
    Long createdRoom = roomRepository.create(room);
    System.out.println(createdRoom);


  }
}
