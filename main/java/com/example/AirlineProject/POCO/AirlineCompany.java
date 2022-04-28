package com.example.AirlineProject.POCO;

public class AirlineCompany implements POCO {
    public int id;
    public String Name;
    public int countryId;
    public int userId;

    public AirlineCompany(int id, String name, int countryId, int userId) {
        this.id = id;
        Name = name;
        this.countryId = countryId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AirlineCompany{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", countryId=" + countryId +
                ", userId=" + userId +
                '}';
    }
}
