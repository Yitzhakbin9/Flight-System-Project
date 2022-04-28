package com.example.AirlineProject.Controller;

import com.example.AirlineProject.Facade.AirlineFacade;
import com.example.AirlineProject.Facade.LoginToken;
import com.example.AirlineProject.POCO.AirlineCompany;
import com.example.AirlineProject.POCO.Flight;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    LoginToken l = new LoginToken(5, "AirCanada", 2); // TEMPORARY!
    AirlineFacade airlineFacade = new AirlineFacade(l);


    //    get_my_flights ()
    @GetMapping("/my_flights")
    public List<Flight> getMyFlights() {
        return airlineFacade.get_my_flights();
    }


    //- update_airline (airline)
    @PutMapping("/")
    public void updateAirline(@RequestBody AirlineCompany airline) {
        airlineFacade.update_airline(airline);
    }


    //- add_flight (flight)
    @PostMapping("/add_flight")
    public void addFlight(@RequestBody Flight flight) {
        System.out.println("THIS IS FROM ADD_FLIGHT AIRLINT CONTROLLER");

        airlineFacade.add_flight(flight);
    }


    //- update_flight (flight)
    @PutMapping("/update_flight")
    public void updateFlight(@RequestBody Flight flight) {
        airlineFacade.update_flight(flight);
    }


    //- remove_flight (flight)
    @DeleteMapping("/remove_flight")
    public void removeFlight(@RequestBody Flight flight) {
        airlineFacade.remove_flight(flight);
    }
}
