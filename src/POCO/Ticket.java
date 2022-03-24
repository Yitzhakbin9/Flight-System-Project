package POCO;

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
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                '}';
    }
}
