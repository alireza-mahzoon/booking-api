package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.RoomType;

public interface RoomTypeRepository {

  /**
   * Create a new RoomType
   *
   * @param roomType RoomType information
   * @return Id for the persisted roomType
   */
  Long create(RoomType roomType);

  /**
   * Find a roomType by its id
   *
   * @param roomTypeId roomType ID
   * @return RoomType if found, {@code null} if not found
   */
  RoomType findById(Long roomTypeId);

  /**
   * Update roomType information
   *
   * @param roomType RoomType to update
   * @return {@code true} if successful
   */
  boolean update(RoomType roomType);

  /**
   * Delete roomType by its roomType id
   *
   * @param roomTypeId RoomType ID
   * @return {@code true} if deleted
   */
  boolean delete(Long roomTypeId);
}
