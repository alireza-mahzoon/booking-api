package se.almstudio.booking.api.service;

import se.almstudio.booking.api.model.entity.Booking;
import se.almstudio.booking.api.model.entity.CityCountryRoomType;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

  /**
   * Find list of hotels with a specific roomtype in a city and country
   *
   * @param city City
   * @param country Country
   * @param roomTypeName RoomTypeName
   * @return Hotel if found, {@code null} if not found
   */
  List<CityCountryRoomType> findHotelByCityAndCountry(String city, String country, String roomTypeName);
}
