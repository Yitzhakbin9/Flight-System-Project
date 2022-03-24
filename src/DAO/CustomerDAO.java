package DAO;

import POCO.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAO<Customer> {

    List<Customer> Customers = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();

    @Override
    public Customer Get(int id) {
        Customer customer = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Customers\"" + "where \"Id\" = " + id);
            resultSet.next();
            customer = new Customer(resultSet.getInt("Id"),
                    resultSet.getString("First_Name"),
                    resultSet.getString("Last_Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone_No"),
                    resultSet.getString("Credit_Card_No"),
                    resultSet.getInt("User_Id"));
        } catch (SQLException e) {
            System.out.println("id " + id + " is not in the Customer table!");
        }
        return customer;
    }

    @Override
    public List<Customer> GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Customers\"");
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("Id"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone_No"),
                        resultSet.getString("Credit_Card_No"),
                        resultSet.getInt("User_Id"));
                this.Customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.Customers;
    }

    @Override
    public void Add(Customer customer) {
        try {
            stm.executeQuery("INSERT INTO \"Customers\"(\"First_Name\",\"Last_Name\",\"Address\",\"Phone_No\",\"Credit_Card_No\",\"User_Id\")\n" +
                    "VALUES\n" +
                    "('" + customer.firstName + "','" + customer.lastName + "','" + customer.address +
                    "','" + customer.phoneNumber + "','" + customer.creditCard + "'," + customer.userId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Customer customer) {
        try {
            stm.executeUpdate("DELETE from \"Customers\"  WHERE \"Id\"=" + customer.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Customer customer, int id) {
        try {
            stm.executeUpdate("UPDATE \"Customers\"\n" +
                    "SET \"First_Name\" = '" + customer.firstName +
                    "', \"Last_Name\"='" + customer.lastName +
                    "', \"Address\" = '" + customer.address +
                    "', \"Phone_No\"= '" + customer.phoneNumber +
                    "',\"Credit_Card_No\"='" + customer.creditCard +
                    "',\"User_Id\"=" + customer.userId + "\n" +
                    "WHERE \"Id\" =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * ADDITIONAL FUNCTIONS :
     */

    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_customer_by_username('" + username + "')");
            resultSet.next();
            customer = new Customer(resultSet.getInt("Id"),
                    resultSet.getString("First_Name"),
                    resultSet.getString("Last_Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone_No"),
                    resultSet.getString("Credit_Card_No"),
                    resultSet.getInt("User_Id"));
        } catch (SQLException e) {
            System.out.println("Username " + username + " is not in the Customer table!");
        }
        return customer;
    }


}
