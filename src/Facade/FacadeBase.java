package Facade;

import DAO.AirlineCompanyDAO;
import DAO.CountryDAO;
import DAO.FlightDAO;
import POCO.AirlineCompany;
import POCO.Country;
import POCO.Flight;
import POCO.User;

import java.util.List;

public abstract class FacadeBase {

    private static FlightDAO flights = new FlightDAO();
    private static AirlineCompanyDAO airlineCompany = new AirlineCompanyDAO();
    private static CountryDAO countries = new CountryDAO();


    public List<Flight> get_all_flights() {
        return this.flights.GetAll();
    }

    public Flight get_flight_by_id(int id) {
        return this.flights.Get(id);
    }

    public List<Flight> get_flights_by_parameters(int origin_country_id, int destination_country_id, String date) {
        return this.flights.getFlightsByParameters(origin_country_id, destination_country_id, date);
    }

    public List<AirlineCompany> get_all_airlines() {
        return this.airlineCompany.GetAll();
    }

    public AirlineCompany get_airline_by_id(int id) {
        return this.airlineCompany.Get(id);
    }

    public AirlineCompany get_airline_by_parameters(String name) {
        return this.airlineCompany.getAirlinesByName(name);
    }

    public List<Country> get_all_countries() {
        return this.countries.GetAll();
    }

    public Country get_country_by_id(int id) {
        return this.countries.Get(id);
    }

    // for internal usage
    public User create_new_user(User user) {
        User newUser = new User(user.id, user.username, user.password, user.email, user.userRole);
        return newUser;
    }
}
