package se.almstudio.booking.api.repository.impl;

import se.almstudio.booking.api.model.entity.Room;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.util.ConnectionManager;
import se.almstudio.booking.api.util.impl.ConnectionUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionManager.INSTANCE.getConnection();
            String query = "SELECT * FROM Room WHERE id=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, roomId);
            ps.execute();
            rs = ps.getResultSet();
            if(rs.next()){
                Room room = new Room();
                room.setId(roomId);
                room.setHotelId(rs.getLong("hotelId"));
                room.setNumber(rs.getInt("number"));
                room.setPhoneNumber(rs.getString("phoneNumber"));
                room.setFloor(rs.getInt("floor"));
                room.setNumberOfGuest(rs.getInt("numberOfGuests"));
                room.setRegistered(rs.getObject("registered", LocalDateTime.class));
                return room;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeQuietly(rs);
            ConnectionUtils.closeQuietly(ps);
            ConnectionUtils.closeQuietly(connection);
        }
    }

    @Override
    public boolean update(Room room) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionManager.INSTANCE.getConnection();
            String query = "UPDATE Room SET HotelId=?, Number=?, PhoneNumber=?, Floor=?, NumberOfGuests=? WHERE id=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, room.getHotelId());
            ps.setInt(2, room.getNumber());
            ps.setString(3, room.getPhoneNumber());
            ps.setInt(4,room.getFloor());
            ps.setInt(5,room.getNumberOfGuest());
            ps.setLong(6, room.getId());
            int resultUpdated = ps.executeUpdate();

            return resultUpdated == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeQuietly(ps);
            ConnectionUtils.closeQuietly(connection);
        }
    }

    @Override
    public boolean delete(Long roomId) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionManager.INSTANCE.getConnection();
            String query = "DELETE FROM Room WHERE id=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, roomId);
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeQuietly(ps);
            ConnectionUtils.closeQuietly(connection);
        }

    }

    @Override
    public List<Room> findByHotelId(Long hotelId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConnectionManager.INSTANCE.getConnection();
            String query = "SELECT * FROM Room WHERE hotelId=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, hotelId);
            ps.execute();
            rs = ps.getResultSet();
            List<Room> rooms = new ArrayList<>();
            while(rs.next()){
                Room room = new Room();
                room.setId(rs.getLong("ID"));
                room.setHotelId(rs.getLong("hotelId"));
                room.setNumber(rs.getInt("number"));
                room.setPhoneNumber(rs.getString("phoneNumber"));
                room.setFloor(rs.getInt("floor"));
                room.setNumberOfGuest(rs.getInt("numberOfGuests"));
                room.setRegistered(rs.getObject("registered", LocalDateTime.class));
                rooms.add(room);
            }
            return rooms;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeQuietly(rs);
            ConnectionUtils.closeQuietly(ps);
            ConnectionUtils.closeQuietly(connection);
        }
    }

    @Override
    public boolean deleteRoomByHotelID(Long hotelId) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionManager.INSTANCE.getConnection();
            String query = "DELETE FROM Room WHERE hotelId=?";
            ps = connection.prepareStatement(query);
            ps.setLong(1, hotelId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeQuietly(ps);
            ConnectionUtils.closeQuietly(connection);
        }
    }
}
