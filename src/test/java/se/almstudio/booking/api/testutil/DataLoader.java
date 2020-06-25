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
    generate(10,10,10,10,10, 10, 10);
  }

  public void generate(int hotels, int roomTypes, int rooms, int roomAmenities, int hotelAmenities, int users, int bookings) {

    List<Long> hotelId = new ArrayList<>();
    List<Long> roomTypeId = new ArrayList<>();
    List<Long> userId = new ArrayList<>();
    List<Long> roomId = new ArrayList<>();

    for (int i = 0; i < hotels; i++) {
      Hotel hotel = new Hotel();
      hotel.setName(faker.name().name());
      hotel.setAddress(faker.address().fullAddress());
      hotel.setCity(faker.address().city());
      hotel.setCountry(faker.address().country());
      Long result = hotelRepository.create(hotel);
      hotelId.add(result);
    }

    for (int i = 0; i < roomTypes ; i++) {
      RoomType roomType = new RoomType();
      roomType.setHotelId(hotelId.get(i));
      for (int j=0; j < roomTypes; j++) {
        roomType.setName(faker.name().title());
        roomType.setDescription(faker.lorem().sentence());
        roomType.setCapacity(faker.number().numberBetween(2,6));
        Long result = roomTypeRepository.create(roomType);
        roomTypeId.add(result);
      }
    }

    for (int i = 0; i < rooms; i++) {
      Room room = new Room();
      room.setHotelId(hotelId.get(i));
      for (int j = 0; j < rooms; j++) {
        room.setNumber(faker.number().numberBetween(1,100000));
        room.setPhoneNumber(faker.phoneNumber().phoneNumber());
        room.setFloor(faker.number().numberBetween(1,200));
        room.setRoomTypeId(roomTypeId.get(j));
        Long result = roomRepository.create(room);
        roomId.add(result);
      }
    }

    for (int i = 0; i < roomAmenities; i++) {
      RoomAmenity roomAmenity = new RoomAmenity();
      roomAmenity.setRoomTypeId(roomTypeId.get(i));
      for (int j = 0; j < roomAmenities; j++) {
        roomAmenity.setName(faker.name().name());
        roomAmenity.setDescription(faker.lorem().sentence(5));
        roomAmenity.setPricing("100SEK");
        roomAmenityRepository.create(roomAmenity);
      }
    }

    for (int i = 0; i < hotelAmenities; i++) {
      HotelAmenity hotelAmenity = new HotelAmenity();
      for (int j = 0; j < hotelAmenities; j++) {
        hotelAmenity.setHotelId(hotelId.get(i));
        hotelAmenity.setName(faker.name().name());
        hotelAmenity.setDescription(faker.lorem().sentence(5));
        hotelAmenity.setPricing("500SEK");
        hotelAmenityRepository.create(hotelAmenity);
      }
    }

    for (int i = 0; i < users; i++) {
      User user = new User();
      user.setFirstName(faker.name().firstName());
      user.setLastName(faker.name().lastName());
      user.setBirthday(LocalDate.now());
      user.setEmail(faker.name().name());
      Long result = userRepository.create(user);
      userId.add(result);
    }

    for (int i = 0; i < bookings; i++) {
      Booking booking = new Booking();
      booking.setUserId(userId.get(i));
      int duration = faker.number().numberBetween(2, 15);
      LocalDate checkIn = LocalDate.now().plusDays(faker.number().numberBetween(14, 300));
      booking.setCheckInDate(checkIn);
      booking.setCheckOutDate(checkIn.plusDays(duration));
      booking.setHotelId(hotelId.get(i));
      booking.setRoomId(roomId.get(i));
      bookingRepository.create(booking);
    }
  }
}
