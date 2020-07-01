package se.almstudio.booking.api.model.rest;

import se.almstudio.booking.api.model.entity.Hotel;
import se.almstudio.booking.api.model.entity.RoomType;

import java.util.List;

public class HotelOffer {
  private Hotel hotel;
  private List<RoomType> roomTypes;

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public void setRoomTypes(List<RoomType> roomTypes) {
    this.roomTypes = roomTypes;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public List<RoomType> getRoomTypes() {
    return roomTypes;
  }

  @Override
  public String toString() {
    return "HotelOffer{" +
      "hotel=" + hotel +
      ", roomTypes=" + roomTypes +
      '}';
  }
}
