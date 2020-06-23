package se.almstudio.booking.api.service;

import se.almstudio.booking.api.model.entity.Booking;
import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

  /**
   * Find available hotelIds between checkInDate and checkOutDate
   *
   * @param checkindate CheckIn Date
   * @param checkoutdate CheckOut Date
   * @return Booking if found, {@code null} if not found
   */
  List<Booking> findBooking(LocalDate checkindate, LocalDate checkoutdate);

  /**
   * Find hotels in the city and country
   *
   * @param city City
   * @param country Country
   * @return Hotel if found, {@code null} if not found
   */
  List<Hotel> findHotelByCityAndCountry(String city, String country);

  /**
   * Find roomtypes with the Capacity
   *
   * @param capacity Capacity
   * @return Hotel if found, {@code null} if not found
   */
  List<RoomType> findRoomType(int capacity);
}
