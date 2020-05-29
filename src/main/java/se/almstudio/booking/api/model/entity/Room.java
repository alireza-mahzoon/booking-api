package se.almstudio.booking.api.model.entity;

public class Room {
    private int roomNumber;
    private int roomPhoneNumber;
    private int roomFloor;
    private int numberOfGuest;
    private boolean isAvailable;

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomPhoneNumber() {
        return roomPhoneNumber;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomPhoneNumber(int roomPhoneNumber) {
        this.roomPhoneNumber = roomPhoneNumber;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}


