package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.HotelAmenity;

public interface HotelAmenityRepository {

  /**
   * Create a new hotelAmenities
   *
   * @param hotelAmenity HotelAmenities information
   * @return Id for the persisted hotelAmenities
   */
  Long create(HotelAmenity hotelAmenity);

  /**
   * Find a hotelAmenity by its id
   *
   * @param hotelAmenitiesId hotelAmenity ID
   * @return hotelAmenity if found, {@code null} if not found
   */
  HotelAmenity findById(Long hotelAmenitiesId);

  /**
   * Update hotelAmenities information
   *
   * @param hotelAmenity HotelAmenities to update
   * @return {@code true} if successful
   */
  boolean update(HotelAmenity hotelAmenity);

  /**
   * Delete hotelAmenity by its hotelAmenity id
   *
   * @param hotelAmenityId HotelAmenity ID
   * @return {@code true} if deleted
   */
  boolean delete(Long hotelAmenityId);
}




