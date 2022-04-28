package com.example.AirlineProject;

import com.example.AirlineProject.DAO.FlightDAO;
import com.example.AirlineProject.POCO.Flight;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnonymousTests {

    FlightDAO flightDAO = new FlightDAO();

    @Test
    void getFlightFromAnonymousControllerTest() {
        String url = "http://localhost:8080/flight/8";
        var current = flightDAO.Get(8);
        var expected = HttpGetTestManager.getResponse(url, Flight.class);
        Assert.assertEquals(current, expected);
    }


}
