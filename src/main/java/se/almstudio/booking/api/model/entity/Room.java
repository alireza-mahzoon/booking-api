package se.almstudio.booking.api.model.entity;

import java.time.LocalDateTime;

public class Room {
  private Long id;
  private Long hotelId;
  private int number;
  private String phoneNumber;
  private int floor;
  private int numberOfGuest;
  private LocalDateTime registered;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getNumberOfGuest() {
    return numberOfGuest;
  }

  public void setNumberOfGuest(int numberOfGuest) {
    this.numberOfGuest = numberOfGuest;
  }

  public LocalDateTime getRegistered() {
    return registered;
  }

  public void setRegistered(LocalDateTime registered) {
    this.registered = registered;
  }

  @Override
  public String toString() {

    return "Room{" +
      "id=" + id +
      ", hotelId='" + hotelId + '\'' +
      ", number='" + number + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", floor='" + floor + '\'' +
      ", numberOfGuests='" + numberOfGuest + '\'' +
      ", registered=" + registered +
      '}';
  }
}


