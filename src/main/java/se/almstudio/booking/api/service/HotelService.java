package se.almstudio.booking.api.service;

public interface HotelService {
  /**
   * Delete hotel and its room hotel id
   *
   * @param hotelId Hotel ID
   * @return {@code true} if deleted
   */
  boolean delete(Long hotelId);
}
