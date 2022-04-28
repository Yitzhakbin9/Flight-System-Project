package com.example.AirlineProject.POCO;


public class Ticket implements POCO {
    public int id;
    public int flightId;
    public int customerId;

    public Ticket(int id, int flightId, int customerId) {
        this.id = id;
        this.flightId = flightId;
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && flightId == ticket.flightId && customerId == ticket.customerId;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                '}';
    }
}
