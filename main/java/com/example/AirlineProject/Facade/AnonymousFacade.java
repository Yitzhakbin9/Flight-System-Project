package com.example.AirlineProject.Facade;

import com.example.AirlineProject.DAO.*;
import com.example.AirlineProject.POCO.Customer;
import com.example.AirlineProject.POCO.User;

public class AnonymousFacade extends FacadeBase {

    private static UserDAO user = new UserDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private UserDAO userDAO = new UserDAO();

    public AnonymousFacade() {}

    public FacadeBase login(String username, String password) throws Exception {
        String userName = user.getUserByUsername(username).username;
        String userPassword = user.getUserByUsername(username).password;
        int userRole = user.getUserByUsername(username).userRole;
        int userID = user.getUserByUsername(username).id;

        if (userName.equals(username) && userPassword.equals(password)) {

            LoginToken loginToken = new LoginToken(userID, userName, userRole);
            System.out.println(loginToken);
            switch (userRole) {
                case 1:
                    System.out.println("Customer");
                    return new CustomerFacade(loginToken);
                case 2:
                    System.out.println("Airline");
                    return new AirlineFacade(loginToken);
                case 3:
                    System.out.println("Admin");
                    return new AdministratorFacade(loginToken);
            }
        } else throw new Exception("Wrong password!");
        return null;
    }


    public void add_customer(User user, Customer customer) {

        if (!validPassword(user.password)) {
            System.out.println("Please insert password length between 8 to 20 characters!");
            return;
        }

        this.userDAO.Add(user);
        User newUser = this.userDAO.getUserByUsername(user.username);
        int newUserId = newUser.id;

        Customer newCustomer = new Customer(0, customer.firstName, customer.lastName, customer.address, customer.phoneNumber,
                customer.creditCard, newUserId);

        this.customerDAO.Add(newCustomer);
    }


    public boolean validPassword(String password) {
        if (password == null) return false;
        if (password.length() > 21 || password.length() < 8) return false;
        return true;
    }


}
