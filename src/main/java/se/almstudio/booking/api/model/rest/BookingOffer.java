package se.almstudio.booking.api.model.rest;

import se.almstudio.booking.api.model.entity.Room;

import java.util.List;

public class BookingOffer {
  private List<AvailableRooms> availableRooms;

  public void setAvailableRooms(List<AvailableRooms> availableRooms) {
    this.availableRooms = availableRooms;
  }

  public List<AvailableRooms> getAvailableRooms() {
    return availableRooms;
  }

}
