package se.almstudio.booking.api.model.entity;

import java.time.LocalDateTime;

public class HotelAmenities {
  private Long id;
  private Long hotelId;
  private String name;
  private String description;
  private String pricing;
  private LocalDateTime registered;
  private LocalDateTime updated;

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

  public void setPricing(String pricing) {
    this.pricing = pricing;
  }

  public void setRegistered(LocalDateTime registered) {
    this.registered = registered;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
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

  public String getPricing() {
    return pricing;
  }

  public LocalDateTime getRegistered() {
    return registered;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }
}
