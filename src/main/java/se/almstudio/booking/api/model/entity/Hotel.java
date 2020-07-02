package se.almstudio.booking.api.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Hotel {
  private Long id;
  private String name;
  private String address;
  private String city;
  private String country;
  private LocalDateTime registered;
  private LocalDateTime updated;

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public LocalDateTime getRegistered() {
    return registered;
  }

  public void setRegistered(LocalDateTime registered) {
    this.registered = registered;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Hotel hotel = (Hotel) o;
    return id.equals(hotel.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Hotel{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", address='" + address + '\'' +
      ", city='" + city + '\'' +
      ", country='" + country + '\'' +
      ", registered=" + registered +
      ", updated=" + updated +
      '}';
  }
}
