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

  public LocalDateTime getUpdated() {
    return Updated;
  }

  public void setUpdated(LocalDateTime updated) {
    Updated = updated;
  }

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public LocalDateTime getRegistered() {
    return Registered;
  }

  public void setRegistered(LocalDateTime registered) {
    Registered = registered;
  }

  @Override
  public String toString() {
    return "RoomType{" +
      "id=" + id +
      ", hotelId=" + hotelId +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", capacity=" + capacity +
      ", Registered=" + Registered +
      ", Updated=" + Updated +
      '}';
  }
}

