package se.almstudio.booking.api.testutil;

import com.github.javafaker.Faker;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.HotelAmenity;
import se.almstudio.booking.api.model.entity.RoomAmenity;
import se.almstudio.booking.api.repository.*;
import se.almstudio.booking.api.repository.impl.*;

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
    generate(10,0,0,0,0, 0, 0);
  }

  public void generate(int hotels, int roomTypes, int rooms, int roomAmenities, int hotelAmenities, int users, int bookings) {

    for (int i = 0; i < hotels; i++) {
      Hotel hotel = new Hotel();
      hotel.setName(faker.name().name());
      hotel.setAddress(faker.address().fullAddress());
      hotel.setCity(faker.address().city());
      hotel.setCountry(faker.address().country());
      hotelRepository.create(hotel);
    }
  }
}
