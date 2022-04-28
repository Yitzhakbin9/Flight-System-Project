package com.example.AirlineProject.POCO;

public class Administrator implements POCO {

    public int id;
    public String firstName;
    public String lastName;
    public int userId;

    public Administrator(int id, String firstName, String lastName, int userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
