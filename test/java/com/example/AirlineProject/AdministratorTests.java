package com.example.AirlineProject;

import com.example.AirlineProject.DAO.CustomerDAO;
import com.example.AirlineProject.POCO.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdministratorTests {

    CustomerDAO customerDAO = new CustomerDAO();


    // Tests only one customer! the first one of the list
    @Test
    void getCustomerFromAdministratorControllerTest() {
        String url = "http://localhost:8080/administrator/all_customers";
        var current = customerDAO.Get(2);
        var expected = HttpGetTestManager.getResponse(url, Customer[].class);
        Assert.assertEquals(current, expected[0]);
    }

}
