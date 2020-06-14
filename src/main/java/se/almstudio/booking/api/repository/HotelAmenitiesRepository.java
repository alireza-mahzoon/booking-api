package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.HotelAmenities;

public interface HotelAmenitiesRepository {

  /**
   * Create a new HotelAmenity
   *
   * @param hotelAmenity HotelAmenity information
   * @return Id for the persisted hotelAmenity
   */
  Long create(HotelAmenities hotelAmenity);
}
