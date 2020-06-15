package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.HotelAmenities;
import se.almstudio.booking.api.model.entity.RoomType;

public interface HotelAmenitiesRepository {

  /**
   * Create a new hotelAmenities
   *
   * @param hotelAmenities HotelAmenities information
   * @return Id for the persisted hotelAmenities
   */
  Long create(HotelAmenities hotelAmenities);

  /**
   * Find a hotelAmenity by its id
   *
   * @param hotelAmenitiesId hotelAmenity ID
   * @return hotelAmenity if found, {@code null} if not found
   */
  HotelAmenities findById(Long hotelAmenitiesId);

  /**
   * Update hotelAmenities information
   *
   * @param hotelAmenities HotelAmenities to update
   * @return {@code true} if successful
   */
  boolean update(HotelAmenities hotelAmenities);
}




