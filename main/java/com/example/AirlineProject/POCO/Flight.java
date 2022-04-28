package com.example.AirlineProject.POCO;

import java.util.Objects;

public class Flight implements POCO {

    public int id;
    public int airlineCompanyId;
    public int originCountryId;
    public int destinationCountryId;
    public String departureTime;
    public String landingTime;
    public int remainingTickets;

    public Flight(int id, int airlineCompanyId, int originCountryId, int destinationCountryId, String departureTime, String landingTime, int remainingTickets) {
        this.id = id;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && airlineCompanyId == flight.airlineCompanyId &&
                originCountryId == flight.originCountryId && destinationCountryId == flight.destinationCountryId
                && remainingTickets == flight.remainingTickets &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(landingTime, flight.landingTime);
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompanyId=" + airlineCompanyId +
                ", originCountryId=" + originCountryId +
                ", destinationCountryId=" + destinationCountryId +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                ", remainingTickets=" + remainingTickets +
                '}';
    }

}
