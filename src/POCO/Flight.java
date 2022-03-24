package POCO;

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
