package se.almstudio.booking.api.model.rest;

import java.util.List;

public class BookingOffer {
  private List<HotelOffer> availableRooms;

  public void setAvailableRooms(List<HotelOffer> availableRooms) {
    this.availableRooms = availableRooms;
  }

  public List<HotelOffer> getAvailableRooms() {
    return availableRooms;
  }

  @Override
  public String toString() {
    return "BookingOffer{" +
      "availableRooms=" + availableRooms +
      '}';
  }
}
