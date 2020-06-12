package se.almstudio.booking.api.model.entity;

import java.time.LocalDateTime;

public class RoomType {
  private Long id;
  private Long hotelId;
  private String name;
  private String description;
  private int capacity;
  private LocalDateTime Registered;
  private LocalDateTime Updated;

  public void setUpdated(LocalDateTime updated) {
    Updated = updated;
  }

  public LocalDateTime getUpdated() {
    return Updated;
  }

  public Long getId() {
    return id;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getCapacity() {
    return capacity;
  }

  public LocalDateTime getRegistered() {
    return Registered;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public void setRegistered(LocalDateTime registered) {
    Registered = registered;
  }
}

