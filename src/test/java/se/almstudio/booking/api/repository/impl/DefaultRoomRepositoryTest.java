package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import se.almstudio.booking.api.model.entity.Room;

import java.time.LocalDateTime;

public class DefaultRoomRepositoryTest {
    @Test
    public void testCreateRoomExpectNoneNullId() {
        DefaultRoomRepository roomRepository = new DefaultRoomRepository();
        Room room = new Room();
        room.setHotelId(22L);
        room.setNumber(99);
        room.setPhoneNumber("964389");
        room.setFloor(25);
        room.setNumberOfGuest(4);
        room.setRegistered(LocalDateTime.now());
        Long result = roomRepository.create(room);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0L, result.longValue());
    }
}
