package com.example.AirlineProject.Facade;

import com.example.AirlineProject.DAO.CustomerDAO;
import com.example.AirlineProject.DAO.FlightDAO;
import com.example.AirlineProject.DAO.TicketDAO;
import com.example.AirlineProject.POCO.Customer;
import com.example.AirlineProject.POCO.Flight;
import com.example.AirlineProject.POCO.Ticket;

import java.util.List;

public class CustomerFacade extends AnonymousFacade {


    private LoginToken loginToken; // userID = (id from "Users" table. NOT id from "Customer" table) ; userName ; userRole
    private CustomerDAO customerDAO = new CustomerDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private FlightDAO flightDAO = new FlightDAO();


    public CustomerFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public void update_customer(Customer customer) {
        this.customerDAO.Update(customer, this.loginToken.getUser_id());
    }

    /**
     * We create new ticket with id=0 (it doesn't matter what number we put as id, because
     * it is autoincrement in the database) , the flightId of the ticket , and the id from
     * the customer who called this function.
     * <p>
     * Before we add ticket we check if there are any tickets left to buy.
     * If there are (Flights.remaining_tickets > 0) - we update the corresponding flight.
     * Else - throw "No tickets left!" exception.
     */

    public void add_ticket(Ticket ticket) throws Exception {

        Customer currentCustomer = customerDAO.getCustomerByUsername(this.loginToken.getName());
        if (ticket.customerId != currentCustomer.id) {
            System.out.println("Your Ticket.Customer_Id doesn't much your own Customer_id!");
            return;
        }

        Ticket newTicket = new Ticket(0, ticket.flightId, currentCustomer.id);

        int id = ticket.flightId;
        Flight flight = this.flightDAO.Get(id);
        if (flight.remainingTickets == 0) throw new Exception("No tickets left!");
        else {
            Flight updatedFlight = new Flight(0, flight.airlineCompanyId, flight.originCountryId,
                    flight.destinationCountryId, flight.departureTime,
                    flight.landingTime, --flight.remainingTickets);
            this.flightDAO.Update(updatedFlight, flight.id);
            this.ticketDAO.Add(newTicket);
        }
    }


    public void remove_ticket(Ticket ticket) {

        Customer currentCustomer = customerDAO.getCustomerByUsername(this.loginToken.getName());

        if (ticket.customerId != currentCustomer.id) {
            System.out.println("You cannot remove tickets of another customer!\n" +
                    "Please insert your own customer_Id");
        }
        Ticket newTicket = new Ticket(0, ticket.flightId, currentCustomer.id);

        int id = ticket.flightId;
        Flight flight = flightDAO.Get(id);

        Flight updatedFlight = new Flight(0, flight.airlineCompanyId, flight.originCountryId,
                flight.destinationCountryId, flight.departureTime,
                flight.landingTime, ++flight.remainingTickets);
        this.flightDAO.Update(updatedFlight, flight.id);
        this.ticketDAO.Remove(ticket);
    }

    public List<Ticket> get_my_tickets() {
        return this.ticketDAO.get_tickets_by_customer(this.loginToken.getUser_id());
    }

}
