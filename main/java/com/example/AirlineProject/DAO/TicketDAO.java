package com.example.AirlineProject.DAO;

import com.example.AirlineProject.POCO.Ticket;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDAO implements DAO<Ticket> {

    List<Ticket> tickets = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();

    @Override
    public Ticket Get(int id) {
        Ticket ticket = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Tickets\"" + "where \"Id\" = " + id);
            resultSet.next();
            ticket = new Ticket(resultSet.getInt("Id"),
                    resultSet.getInt("Flight_Id"),
                    resultSet.getInt("Customer_Id"));
        } catch (SQLException e) {
            System.out.println("id " + id + " is not in Ticket table!");
        }
        return ticket;
    }

    @Override
    public List GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Tickets\"");
            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getInt("Id"),
                        resultSet.getInt("Flight_Id"),
                        resultSet.getInt("Customer_Id"));
                this.tickets.add(ticket);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return this.tickets;
    }

    @Override
    public void Add(Ticket ticket) {
        try {
            stm.executeQuery("INSERT INTO \"Tickets\"(\"Flight_Id\",\"Customer_Id\")\n" +
                    "VALUES (" + ticket.flightId + "," + ticket.customerId + ")");
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Ticket ticket) {
        try {
            stm.executeUpdate("DELETE from \"Tickets\"  WHERE \"Id\"=" + ticket.id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void Update(Ticket ticket, int id) {
        try {
            stm.executeUpdate("UPDATE \"Tickets\"\n" +
                    "SET \"Flight_Id\" = " + ticket.flightId +
                    ", \"Customer_Id\"=" + ticket.customerId +
                    "WHERE \"Id\" =" + id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    /**
     * ADDITIONAL FUNCTIONS :
     */


    public List<Ticket> get_tickets_by_customer(int customerId) {
        this.tickets.clear();
        List<Ticket> result = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_tickets_by_customer(" + customerId + ")");
            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getInt("Id"),
                        resultSet.getInt("Flight_Id"),
                        resultSet.getInt("Customer_Id"));
                this.tickets.add(ticket);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return this.tickets;
    }

    public void removeTicketsByFlightId(int id) {
        try {
            stm.executeUpdate("DELETE from \"Tickets\"  WHERE \"Tickets\".\"Flight_Id\" = " + id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    public void removeTicketsByCustomerId(int id) {
        try {
            stm.executeUpdate("DELETE from \"Tickets\"  WHERE \"Tickets\".\"Customer_Id\" = " + id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }


}
