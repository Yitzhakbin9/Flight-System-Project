package com.example.AirlineProject;

import com.example.AirlineProject.DAO.TicketDAO;
import com.example.AirlineProject.POCO.Ticket;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class CustomerTests {

    TicketDAO ticketDAO = new TicketDAO();

    // Tests list of tickets of Customer number 2 (I did it, so it will match the login token I sent on customer controller)
    @Test
    void getCustomerFromAdministratorControllerTest() {
        String url = "http://localhost:8080/customer/my_tickets";
        var current = ticketDAO.get_tickets_by_customer(2);

        var x = Arrays.asList(HttpGetTestManager.getResponse(url, Ticket[].class));
//        List<Ticket> expected = Arrays.asList(HttpGetTestManager.getResponse(url, Ticket[].class));
        Assert.assertEquals(current, x);
    }

}
