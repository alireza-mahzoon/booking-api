package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.Hotel;

public interface HotelRepository {

  /**
   * Create a new hotel
   *
   * @param hotel Hotel information
   * @return Id for the persisted hotel
   */
  Long create(Hotel hotel);

  /**
   * Find a hotel by its id
   *
   * @param hotelId Hotel ID
   * @return Hotel if found, {@code null} if not found
   */
  Hotel findById(Long hotelId);

  /**
   * Update hotel information
   *
   * @param hotel Hotel to update
   * @return {@code true} if successful
   */
  boolean update(Hotel hotel);

  /**
   * Delete hotel by its id
   *
   * @param hotelId Hotel ID
   * @return {@code true} if deleted
   */
  boolean delete(Long hotelId);
}
