package se.almstudio.booking.api.service.impl;

import se.almstudio.booking.api.repository.HotelRepository;
import se.almstudio.booking.api.repository.RoomRepository;
import se.almstudio.booking.api.repository.impl.DefaultHotelRepository;
import se.almstudio.booking.api.repository.impl.DefaultRoomRepository;
import se.almstudio.booking.api.service.HotelService;

public class DefaultHotelService implements HotelService {

    @Override
    public boolean delete(Long hotelID) {
        RoomRepository roomRepository = new DefaultRoomRepository();
        roomRepository.deleteRoomByHotelID(hotelID);
        HotelRepository hotelRepository = new DefaultHotelRepository();
        return hotelRepository.delete(hotelID);
    }
}
