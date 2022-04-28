package com.example.AirlineProject.Facade;

import com.example.AirlineProject.DAO.*;
import com.example.AirlineProject.POCO.*;

import java.util.List;


public class AdministratorFacade extends AnonymousFacade {

    private LoginToken loginToken; // userID , userName , userRole
    private AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private UserDAO userDAO = new UserDAO();
    private AdministratorDAO administratorDAO = new AdministratorDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private FlightDAO flightDAO = new FlightDAO();

    public AdministratorFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }


    public void print(){
        System.out.println("THIS IS FROM ADMINISTRATOR FACADE");
    }



    public List<Customer> get_all_customers() {
        return this.customerDAO.GetAll();
    }

    // The admin has to insert new user in addition to new airline.
    // We also must insert the airline with a matching userId.
    public void add_airline(User user, AirlineCompany airline) {

        if (!validPassword(user.password)) {
            System.out.println("Please insert password length between 8 to 20 characters!");
            return;
        }

        this.userDAO.Add(user);

        User newUser = this.userDAO.getUserByUsername(user.username);
        int newUserId = newUser.id;

        AirlineCompany airlineCompany = new AirlineCompany(0, airline.Name, airline.countryId, newUserId);
        this.airlineCompanyDAO.Add(airlineCompany);
    }


    public void add_customer(User user, Customer customer) {
        super.add_customer(user, customer);
    }


    public void add_administrator(User user, Administrator administrator) {
        if (!validPassword(user.password)) {
            System.out.println("Please insert password length between 8 to 20 characters!");
            return;
        }

        this.userDAO.Add(user);
        User newUser = this.userDAO.getUserByUsername(user.username);
        int newUserId = newUser.id;

        Administrator newAdmin = new Administrator(0, administrator.firstName, administrator.lastName, newUserId);
        this.administratorDAO.Add(newAdmin);
    }


    /*
        In order to remove airline we must:
         - remove all its tickets from "Tickets" table
         - remove all its flights from "Flights" table
         - remove it from "Airline" table
         - remove its user from "Users" table
     */

    public void remove_airline(AirlineCompany airline) {

        // remove tickets
        List<Flight> airlineFlights = this.flightDAO.getFlightsByAirline_id(airline.id);
        for (Flight f : airlineFlights) {
            this.ticketDAO.removeTicketsByFlightId(f.id);
        }

        // remove flights
        this.flightDAO.removeFlightsByAirlineId(airline.id);

        // remove airline
        this.airlineCompanyDAO.Remove(airline);

        // remove user
        // userDAO.Remove deletes the user by its id, so It doesn't matter if we send empty values
        User user = new User(airline.userId, "", "", "", 0);
        userDAO.Remove(user);
    }


    /*
        In order to remove customer we must:
         - remove all its tickets from "Tickets" table
         - remove it from "Customer" table
         - remove its user from "Users" table
     */

    public void remove_customer(Customer customer) {

        this.ticketDAO.removeTicketsByCustomerId(customer.id);
        this.customerDAO.Remove(customer);

        User user = new User(customer.userId, "", "", "", 0);
        userDAO.Remove(user);
    }


    /*
        In order to remove administrator we must:
         - remove it from "Administrator" table
         - remove its user from "Users" table
     */
    public void remove_administrator(Administrator administrator) {

        int adminsNumber = this.administratorDAO.howManyAdmins();
        if (adminsNumber == 1) {
            System.out.println("You are the only administrator!\n" +
                    "If you delete yourself the world will collapse into itself!");
            return;
        }
        this.administratorDAO.Remove(administrator);

        User user = new User(administrator.userId, "", "", "", 0);
        userDAO.Remove(user);

    }


    public boolean validPassword(String password) {
        if (password == null) return false;
        if (password.length() > 21 || password.length() < 8) return false;
        return true;
    }

}
