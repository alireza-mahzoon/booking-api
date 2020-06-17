package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.Booking;

public interface BookingRepository {

  /**
   * Create a new Booking
   *
   * @param booking Booking information
   * @return Id for the persisted booking
   */
  Long create(Booking booking);

  /**
   * Find a booking by its id
   *
   * @param bookingId booking ID
   * @return Booking if found, {@code null} if not found
   */
  Booking findById(Long bookingId);

  /**
   * Update booking information
   *
   * @param booking Booking to update
   * @return {@code true} if successful
   */
  boolean update(Booking booking);

  /**
   * delete booking by its booking id
   *
   * @param bookingId Booking ID
   * @return {@code true} if deleted
   */
  boolean delete(Long bookingId);
}
