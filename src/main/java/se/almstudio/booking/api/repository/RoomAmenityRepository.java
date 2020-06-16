package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.RoomAmenity;

public interface RoomAmenityRepository {

  /**
   * Create a new roomAmenity
   *
   * @param roomAmenity RoomAmenity information
   * @return Id for the persisted roomAmenity
   */
  Long create(RoomAmenity roomAmenity);

  /**
   * Find a roomAmenity by its id
   *
   * @param roomAmenityId roomAmenity ID
   * @return RoomAmenity if found, {@code null} if not found
   */
  RoomAmenity findById(Long roomAmenityId);

  /**
   * update roomAmenity information
   *
   * @param roomAmenity RoomAmenity to update
   * @return {@code true} if successful
   */
  boolean update(RoomAmenity roomAmenity);

  /**
   * Delete roomAmenity by its roomAmenity id
   *
   * @param roomAmenityId RoomAmenity ID
   * @return {@code true} if deleted
   */
  boolean delete(Long roomAmenityId);
}
