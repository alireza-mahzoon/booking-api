package se.almstudio.booking.api.repository.impl;

import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;

public class DefaultRoomRepository implements RoomRepository {

    @Override
    public Long create(Room room) {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = ConnectionManager.INSTANCE.getConnection();
            String query = "INSERT INTO Room(HotelId, Number, PhoneNumber, Floor, NumberOfGuests, Registered) VALUES(?,?,?,?,?,?)";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, room.getHotelId());
            ps.setInt(2, room.getNumber());
            ps.setString(3, room.getPhoneNumber());
            ps.setInt(4,room.getFloor());
            ps.setInt(5,room.getNumberOfGuest());
            ps.setObject(6,LocalDateTime.now());
            ps.executeUpdate();
            if (ps.getGeneratedKeys().next()) {
                return ps.getGeneratedKeys().getLong(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeQuietly(ps);
            ConnectionUtils.closeQuietly(connection);
        }
    }

    @Override
    public Room findById(Long roomId) {
        return null;
    }

    @Override
    public boolean update(Room room) {
        return false;
    }

    @Override
    public boolean delete(Long roomId) {
        return false;
    }
}
