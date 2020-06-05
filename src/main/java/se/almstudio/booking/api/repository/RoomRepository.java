package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.Room;

import java.util.ArrayList;
import java.util.List;

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
     * Find rooms for a Hotel ID
     * @param hotelId Hotel ID
     * @retuen Room if found, {@code null} if not found
     */
    List<Room> findByHotelId(Long hotelId);

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

    /**
     * Delete hotel by its hotel id
     * @param hotelId Hotel ID
     * @return {@code true} if deleted
     */
    boolean deleteRoomByHotelID(Long hotelId);
}
