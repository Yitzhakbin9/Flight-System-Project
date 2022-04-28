package com.example.AirlineProject.Controller;

import com.example.AirlineProject.DAO.CustomerDAO;
import com.example.AirlineProject.Facade.AdministratorFacade;
import com.example.AirlineProject.Facade.LoginToken;
import com.example.AirlineProject.POCO.Administrator;
import com.example.AirlineProject.POCO.AirlineCompany;
import com.example.AirlineProject.POCO.Customer;
import com.example.AirlineProject.POCO.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    LoginToken l = new LoginToken(0, null, 3); // TEMPORARY!
    AdministratorFacade administratorFacade = new AdministratorFacade(l);


    @GetMapping("/all_customers")
    public List<Customer> getAllCustomers() {
        return administratorFacade.get_all_customers();
    }

    // FOR TESTING ***
    @GetMapping("/customer/{id}")
    public Customer getAllCustomers(@PathVariable int id) {
        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.Get(id);
    } // ***



    @PostMapping("/add_airline")
    public void addAirline(@RequestBody User user,
                           @RequestBody AirlineCompany airline) {
        administratorFacade.add_airline(user, airline);
    }


    @PostMapping("/add_customer")
    public void addCustomer(@RequestBody User user,
                            @RequestBody Customer customer) {
        administratorFacade.add_customer(user, customer);
    }


    @PostMapping("/add_admin")
    public void addAdministrator(@RequestBody User user,
                                 @RequestBody Administrator administrator) {
        administratorFacade.add_administrator(user, administrator);
    }


    @DeleteMapping("/remove_airline")
    public void removeAirline(@RequestBody AirlineCompany airline) {
        administratorFacade.remove_airline(airline);
    }


    @DeleteMapping("/remove_customer")
    public void removeCustomer(@RequestBody Customer customer) {
        administratorFacade.remove_customer(customer);
    }


    @DeleteMapping("/remove_admin")
    public void removeAdministrator(@RequestBody Administrator administrator) {
        administratorFacade.remove_administrator(administrator);
    }

}
