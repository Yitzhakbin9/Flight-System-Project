package com.example.AirlineProject;

import com.example.AirlineProject.DAO.FlightDAO;
import com.example.AirlineProject.POCO.Flight;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AirlineTests {

    FlightDAO flightDAO = new FlightDAO();

    // Tests list of flight of AirlineCompany 5
    @Test
    void getCustomerFromAdministratorControllerTest() {
        String url = "http://localhost:8080/airline/my_flights";
        var current = flightDAO.getFlightsByAirline_id(5);
        List<Flight> expected = Arrays.asList(HttpGetTestManager.getResponse(url, Flight[].class));
        Assert.assertEquals(current, expected);
    }

}
