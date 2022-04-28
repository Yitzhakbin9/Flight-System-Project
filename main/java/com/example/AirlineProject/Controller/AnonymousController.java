package com.example.AirlineProject.Controller;

import com.example.AirlineProject.Facade.AnonymousFacade;
import com.example.AirlineProject.POCO.AirlineCompany;
import com.example.AirlineProject.POCO.Country;
import com.example.AirlineProject.POCO.Flight;
import com.example.AirlineProject.POCO.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnonymousController {

    AnonymousFacade anonymousFacade = new AnonymousFacade();

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return anonymousFacade.get_all_flights();
    }


    @GetMapping("/flight/{id}")
    public Flight getFlight(@PathVariable("id") int id) {
        return anonymousFacade.get_flight_by_id(id);
    }

    @GetMapping("/flightsByParameters/{O_id}/{D_id}/{date}")
    public List<Flight> getFlightsByParameters(@PathVariable("O_id") int origin_country_id,
                                               @PathVariable("D_id") int destination_country_id,
                                               @PathVariable("date") String date) {
        return anonymousFacade.get_flights_by_parameters(origin_country_id, destination_country_id, date);

    }

    @GetMapping("/airlines")
    public List<AirlineCompany> getAllAirlines() {
        return anonymousFacade.get_all_airlines();
    }

    @GetMapping("/airline/{id}")
    public AirlineCompany getAirline(@PathVariable("id") int id) {
        return anonymousFacade.get_airline_by_id(id);
    }


    @GetMapping("/airlinesByParameters/{name}")
    public AirlineCompany getAirline(@PathVariable("name") String name) {
        return anonymousFacade.get_airline_by_parameters(name);
    }


    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return anonymousFacade.get_all_countries();
    }


    @GetMapping("/country/{id}")
    public Country getCountry(@PathVariable("id") int id) {
        return anonymousFacade.get_country_by_id(id);
    }


    @PostMapping("/add_user")
    public User addUser(@RequestBody User user) {
        return anonymousFacade.create_new_user(user);
    }


}



