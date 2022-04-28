package com.example.AirlineProject.Facade;

import com.example.AirlineProject.DAO.AirlineCompanyDAO;
import com.example.AirlineProject.DAO.FlightDAO;
import com.example.AirlineProject.DAO.TicketDAO;
import com.example.AirlineProject.POCO.AirlineCompany;
import com.example.AirlineProject.POCO.Flight;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AirlineFacade extends AnonymousFacade {

    private LoginToken loginToken; // userID (User_id from "Airline_Company" table)  , userName , userRole
    private FlightDAO flightDAO = new FlightDAO();
    private AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    public AirlineFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }


    public List<Flight> get_my_flights() {
        return this.flightDAO.getFlightsByAirline_id(this.loginToken.getUser_id());
    }


    public void update_airline(AirlineCompany airline) {

        if (airline.userId != this.loginToken.getUser_id()) {
            System.out.println("You cannot update another airline!");
            return;
        }
        this.airlineCompanyDAO.Update(airline, this.loginToken.getUser_id());
    }


    public void add_flight(Flight flight) {
        if (flight.remainingTickets < 1) {
            System.out.println("You cannot add flight with remaining tickets less then 1!");
            return;
        }
        if (flight.originCountryId == flight.destinationCountryId) {
            System.out.println("You cannot add flight where origin country = destination country!");
            return;
        }

        AirlineCompany airline = this.airlineCompanyDAO.Get(flight.airlineCompanyId);

//        if (airline.userId != this.loginToken.getUser_id()) {
//            System.out.println("airline.userId="+airline.userId);
//            System.out.println("this.loginToken.getUser_id()="+this.loginToken.getUser_id());
//
//            System.out.println("You cannot edit flight of another airline!\n" +
//                    "Please insert correct airlineCompanyId!");
//            return;
//        }

        Timestamp departure = Timestamp.valueOf(flight.departureTime);
        Timestamp landing = Timestamp.valueOf(flight.landingTime);

        if (landing.before(departure)) {
            System.out.println("You cannot enter departure time earlier then landing time!");
            return;
        }

        Flight newFlight = new Flight(flight.id, this.loginToken.getUser_id(), flight.originCountryId,
                flight.destinationCountryId, flight.departureTime, flight.landingTime, flight.remainingTickets);

        this.flightDAO.Add(newFlight);
    }


    public void update_flight(Flight flight) {
        if (flight.airlineCompanyId != this.loginToken.getUser_id()) {
            System.out.println("You cannot edit flight of another airline!\n" +
                    "Please insert correct airlineCompanyId!");
            return;
        }

        // before we update we check if the flight is in the list of flights of
        // this airline. It meant to prevent us from update a flight that is not exit and also
        // prevent a situation where we try to update flight with correct airline id but
        // incorrect flight id.

        List<Flight> allFlights = get_my_flights();
        List<Integer> ids = new ArrayList<>(); // list of id only
        for (var f : allFlights) {
            ids.add(f.id);
        }

        if (!ids.contains(flight.id)) {
            System.out.println("You cannot update flight that is not exit!");
        }
        this.flightDAO.Update(flight, flight.id);
    }

    // remove flight by its flight_id
    public void remove_flight(Flight flight) {
        if (flight.airlineCompanyId != this.loginToken.getUser_id()) {
            System.out.println("You cannot edit flight of another airline!\n" +
                    "Please insert correct airlineCompanyId!");
            return;
        }

        List<Flight> allFlights = get_my_flights();
        List<Integer> ids = new ArrayList<>(); // list of id only
        for (var f : allFlights) {
            ids.add(f.id);
        }

        if (!ids.contains(flight.id)) {
            System.out.println("You cannot remove flight that is not exit!");
        }

        // We have to remove all the tickets belong to this flight!
        this.ticketDAO.removeTicketsByFlightId(flight.id);
        this.flightDAO.Remove(flight);

    }
}
