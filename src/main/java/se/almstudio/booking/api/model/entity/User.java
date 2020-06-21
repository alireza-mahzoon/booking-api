package se.almstudio.booking.api.model.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private String email;
  private LocalDateTime Registered;
  private LocalDateTime updated;

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRegistered(LocalDateTime registered) {
    Registered = registered;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public String getEmail() {
    return email;
  }

  public LocalDateTime getRegistered() {
    return Registered;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }
}
