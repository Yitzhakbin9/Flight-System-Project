package com.example.AirlineProject.DAO;

import com.example.AirlineProject.POCO.Customer;
import com.example.AirlineProject.POCO.Flight;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightDAO implements DAO<Flight> {

    List<Flight> flights = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();


    @Override
    public Flight Get(int id) {
        Flight flight = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Flights\"" + "where \"Id\" = " + id);
            resultSet.next();
            flight = new Flight(resultSet.getInt("Id"),
                    resultSet.getInt("Airline_Company_Id"),
                    resultSet.getInt("Origin_Country_Id"),
                    resultSet.getInt("Destination_Country_Id"),
                    resultSet.getString("Departure_Time"),
                    resultSet.getString("Landing_Time"),
                    resultSet.getInt("Remaining_Tickets"));
        } catch (SQLException e) {
            System.out.println("id " + id + " is not in Flight table!");
        }
        return flight;
    }

    @Override
    public List<Flight> GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Flights\"");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                this.flights.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return this.flights;
    }

    @Override
    public void Add(Flight flight) {
        try {
            stm.executeQuery("INSERT INTO \"Flights\"(\"Airline_Company_Id\",\"Origin_Country_Id\",\"Destination_Country_Id\",\"Departure_Time\",\"Landing_Time\",\"Remaining_Tickets\")\n" +
                    "VALUES\n" +
                    "(" + flight.airlineCompanyId + "," + flight.originCountryId + "," + flight.destinationCountryId +
                    ",'" + flight.departureTime + "','" + flight.landingTime + "'," + flight.remainingTickets + ")");
        } catch (SQLException e) {
//            e.printStackTrace();

        }
    }

    @Override
    public void Remove(Flight flight) {
        try {
            stm.executeUpdate("DELETE from \"Flights\"  WHERE \"Id\"=" + flight.id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void Update(Flight flight, int id) {
        try {
            stm.executeUpdate("UPDATE \"Flights\"\n" +
                    "SET \"Airline_Company_Id\" = " + flight.airlineCompanyId +
                    ", \"Origin_Country_Id\"=" + flight.originCountryId +
                    ", \"Destination_Country_Id\" = " + flight.destinationCountryId +
                    ", \"Departure_Time\"= '" + flight.departureTime +
                    "',\"Landing_Time\"='" + flight.landingTime + "'" +
                    ",\"Remaining_Tickets\"=" + flight.remainingTickets + "\n" +
                    "WHERE \"Id\" =" + id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    /**
     * ADDITIONAL FUNCTIONS :
     */

    // date = departure time
    public List<Flight> getFlightsByParameters(int originCountryId, int destinationCountryId, String date) {
        List<Flight> result = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_flights_by_parameters(" + originCountryId + "," + destinationCountryId +
                    ",'" + date + "')\n");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                result.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return result;
    }

    public List<Flight> getFlightsByAirline_id(int airlineId) {
        List<Flight> result = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_flights_by_airline_id(" + airlineId + ")");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                result.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return result;
    }

    public List<Flight> getArrivalFlights(int countryId) {
        List<Flight> result = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_arrival_flights(" + countryId + ")");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                result.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return result;
    }

    public List<Flight> getDepartureFlights(int countryId) {
        List<Flight> result = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_departure_flights(" + countryId + ")");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                result.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return result;
    }

    public List<Flight> getFlightsByOriginCountryId(int country_id) {
        List<Flight> flightsByOriginCountryId = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Flights\" where \"Origin_Country_Id\" = " + country_id);
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                flightsByOriginCountryId.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return flightsByOriginCountryId;
    }

    public List<Flight> getFlightsByDestinationCountryId(int country_id) {
        List<Flight> flightsByDestinationCountryId = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Flights\" where \"Destination_Country_Id\" = " + country_id);
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                flightsByDestinationCountryId.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return flightsByDestinationCountryId;
    }

    public List<Flight> getFlightsByDepartureDate(String date) {
        List<Flight> flightsByDepartureDate = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Flights\" where \"Departure_Time\" = '" + date + "'");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                flightsByDepartureDate.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return flightsByDepartureDate;
    }

    public List<Flight> getFlightsByLandingDate(String date) {
        List<Flight> flightsByLandingDate = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Flights\" where \"Landing_Time\" = '" + date + "'");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                flightsByLandingDate.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return flightsByLandingDate;
    }

    // This function returns list of flight by the customer id
    public List<Flight> getFlightsByCustomer(Customer customer) {
        List<Flight> flightsByCustomer = new ArrayList<>();
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_flights_by_customer(" + customer.id + ")");
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt("Id"),
                        resultSet.getInt("Airline_Company_Id"),
                        resultSet.getInt("Origin_Country_Id"),
                        resultSet.getInt("Destination_Country_Id"),
                        resultSet.getString("Departure_Time"),
                        resultSet.getString("Landing_Time"),
                        resultSet.getInt("Remaining_Tickets"));
                flightsByCustomer.add(flight);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return flightsByCustomer;
    }

    // pay attention - you need to remove all the corresponding tickets first
    public void removeFlightsByAirlineId(int id) {
        try {
            stm.executeUpdate("DELETE from \"Flights\"  WHERE \"Flights\".\"Airline_Company_Id\" = " + id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }


}
