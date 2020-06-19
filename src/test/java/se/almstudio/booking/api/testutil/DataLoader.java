package se.almstudio.booking.api.testutil;

import com.github.javafaker.Faker;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.*;
import se.almstudio.booking.api.repository.*;
import se.almstudio.booking.api.repository.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
  private final HotelRepository hotelRepository = new DefaultHotelRepository();
  private final RoomTypeRepository roomTypeRepository = new DefaultRoomTypeRepository();
  private final RoomRepository roomRepository = new DefaultRoomRepository();
  private final RoomAmenityRepository roomAmenityRepository = new DefaultRoomAmenityRepository();
  private final HotelAmenityRepository hotelAmenityRepository = new DefaultHotelAmenityRepository();
  private final UserRepository userRepository = new DefaultUserRepository();
  private final BookingRepository bookingRepository = new DefaultBookingRepository();
  private final Faker faker = new Faker();

  @Test
  public void testGenerateData() {
    generate(10,10,10,0,0, 0, 0);
  }

  public void generate(int hotels, int roomTypes, int rooms, int roomAmenities, int hotelAmenities, int users, int bookings) {

    List<Long> hotelId = new ArrayList<>();
    List<Long> roomTypeId = new ArrayList<>();


    for (int i = 0; i < hotels; i++) {
        Hotel hotel = new Hotel();
        hotel.setName(faker.name().name());
        hotel.setAddress(faker.address().fullAddress());
        hotel.setCity(faker.address().city());
        hotel.setCountry(faker.address().country());
        Long result = hotelRepository.create(hotel);
        hotelId.add(result);
    }

    for (int i = 0; i < roomTypes; i++) {
      RoomType roomType = new RoomType();
      roomType.setHotelId(hotelId.get(i));
      roomType.setName(faker.name().name());
      roomType.setDescription(faker.lorem().sentence());
      roomType.setCapacity(faker.number().numberBetween(2,6));
      Long result = roomTypeRepository.create(roomType);
      roomTypeId.add(result);
    }

    for (int i = 0; i < rooms; i++) {
      Room room = new Room();
      room.setHotelId(hotelId.get(i));
      room.setRoomTypeId(roomTypeId.get(i));
      room.setNumber((int) faker.number().randomNumber());
      room.setPhoneNumber(faker.phoneNumber().toString());
      room.setFloor(faker.number().numberBetween(1,200));
      roomRepository.create(room);
    }
  }
}
