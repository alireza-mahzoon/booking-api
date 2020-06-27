package se.almstudio.booking.api.model.entity;

public class CityCountryRoomType {
  private String city;
  private String country;
  private String name;

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getName(String name) {
    return this.name;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setName(String name) {
    this.name = name;
  }


}
