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
}
