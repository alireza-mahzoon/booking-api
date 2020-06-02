package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.Room;

public interface RoomRepository {

    /**
     * Create a new room
     * @param room Room information
     * @return Id for the persisted room
     */
    Long create(Room room);

    /**
     * Find a room by its id
     * @param roomId Room ID
     * @return Room if found, {@code null} if not found
     */
    Room findById(Long roomId);

    /**
     * update room information
     * @param room Room to update
     * @return {@code true} if successful
     */
    boolean update(Room room);

    /**
     * Delete room by its id
     * @param roomId Room ID
     * @return {@code true} if deleted
     */
    boolean delete(Long roomId);
}
