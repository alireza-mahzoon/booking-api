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

    public int getRoomNumber() {
        return number;
    }

    public String getRoomPhoneNumber() {
        return phoneNumber;
    }

    public int getRoomFloor() {
        return floor;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }


    public void setRoomNumber(int number) {
        this.number = number;
    }

    public void setRoomPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRoomFloor(int floor) {
        this.floor = floor;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public Long getRoomId() {
        return id;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomId(Long id) {
        this.id = id;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }
}


