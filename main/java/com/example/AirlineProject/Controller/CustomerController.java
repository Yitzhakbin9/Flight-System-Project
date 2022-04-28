package com.example.AirlineProject.Controller;

import com.example.AirlineProject.Facade.CustomerFacade;
import com.example.AirlineProject.Facade.LoginToken;
import com.example.AirlineProject.POCO.Customer;
import com.example.AirlineProject.POCO.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    LoginToken l = new LoginToken(2, "MilaH", 1);
    CustomerFacade customerFacade = new CustomerFacade(l);


    @PutMapping("/")
    public void updateCostumer(@RequestBody Customer customer) {
        customerFacade.update_customer(customer);
    }


    @PostMapping("/add_ticket")
    public void addTicket(@RequestBody Ticket ticket) throws Exception {
        customerFacade.add_ticket(ticket);
    }


    @DeleteMapping("/remove_ticket")
    public void removeTicket(@RequestBody Ticket ticket) {
        customerFacade.remove_ticket(ticket);
    }


    @GetMapping("/my_tickets")
    public List<Ticket> getMyTickets() {
        return customerFacade.get_my_tickets();
    }


}
