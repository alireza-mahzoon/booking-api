package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.HotelAmenities;

public interface HotelAmenitiesRepository {

  /**
   * Create a new hotelAmenities
   *
   * @param hotelAmenities HotelAmenities information
   * @return Id for the persisted hotelAmenities
   */
  Long create(HotelAmenities hotelAmenities);
}
