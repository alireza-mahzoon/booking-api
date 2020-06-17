package se.almstudio.booking.api.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
  private Long id;
  private Long userId;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private Long hotelId;
  private Long roomId;
  private LocalDateTime Registered;
  private LocalDateTime Updated;

  public void setId(Long id) {
    this.id = id;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setCheckInDate(LocalDate checkInDate) {
    this.checkInDate = checkInDate;
  }

  public void setCheckOutDate(LocalDate checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public void setRegistered(LocalDateTime registered) {
    Registered = registered;
  }

  public void setUpdated(LocalDateTime updated) {
    Updated = updated;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public LocalDate getCheckInDate() {
    return checkInDate;
  }

  public LocalDate getCheckOutDate() {
    return checkOutDate;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public Long getRoomId() {
    return roomId;
  }

  public LocalDateTime getRegistered() {
    return Registered;
  }

  public LocalDateTime getUpdated() {
    return Updated;
  }
}
