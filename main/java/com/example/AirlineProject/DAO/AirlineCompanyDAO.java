package com.example.AirlineProject.DAO;

import com.example.AirlineProject.POCO.AirlineCompany;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AirlineCompanyDAO implements DAO<AirlineCompany> {

    List<AirlineCompany> airlineCompanies = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();


    @Override
    public AirlineCompany Get(int id) {
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Airline_Companies\"" + "where \"Id\" = " + id);
            resultSet.next();
            airlineCompany = new AirlineCompany(resultSet.getInt("Id"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Country_Id"),
                    resultSet.getInt("User_Id"));
        } catch (SQLException e) {
            System.out.println("id " + id + " is not in the AirlineCompany table!");
        }
        return airlineCompany;
    }

    @Override
    public List<AirlineCompany> GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Airline_Companies\"");
            while (resultSet.next()) {
                AirlineCompany airlineCompany = new AirlineCompany(resultSet.getInt("Id"),
                        resultSet.getString("Name"),
                        resultSet.getInt("Country_Id"),
                        resultSet.getInt("User_Id"));
                this.airlineCompanies.add(airlineCompany);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return this.airlineCompanies;
    }

    @Override
    public void Add(AirlineCompany airlineCompany) {
        try {
            stm.executeQuery("insert into \"Airline_Companies\" (\"Name\",\"Country_Id\",\"User_Id\")\n" +
                    "VALUES\n" +
                    "('" + airlineCompany.Name + "'," + airlineCompany.countryId + "," + airlineCompany.userId + ")");
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void Remove(AirlineCompany airlineCompany) {
        try {
            stm.executeUpdate("DELETE from \"Airline_Companies\"  WHERE \"Id\"=" + airlineCompany.id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void Update(AirlineCompany airlineCompany, int id) {
        try {
            stm.executeUpdate("UPDATE \"Airline_Companies\"\n" +
                    "SET \"Name\" = '" + airlineCompany.Name +
                    "', \"Country_Id\"=" + airlineCompany.countryId +
                    ", \"User_Id\" = " + airlineCompany.userId +
                    "WHERE \"Id\" =" + id);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }


    /**
     * ADDITIONAL FUNCTIONS :
     */

    public AirlineCompany getAirlineByUsername(String username) {
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_airline_by_username('" + username + "')");
            resultSet.next();
            airlineCompany = new AirlineCompany(resultSet.getInt("Id"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Country_Id"),
                    resultSet.getInt("User_Id"));
        } catch (SQLException e) {
            System.out.println("username " + username + " is not in the AirlineCompany table!");
        }
        return airlineCompany;
    }

    public AirlineCompany getAirlinesByCountry(int country_id) {
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Airline_Companies\" where \"Country_Id\" = " + country_id);
            resultSet.next();
            airlineCompany = new AirlineCompany(resultSet.getInt("Id"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Country_Id"),
                    resultSet.getInt("User_Id"));
        } catch (SQLException e) {
            System.out.println("country_id " + country_id + " is not in AirlineCompany table!");
        }
        return airlineCompany;
    }

    public AirlineCompany getAirlinesByName(String name) {
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Airline_Companies\" where \"Name\" = '" + name + "'");
            resultSet.next();
            airlineCompany = new AirlineCompany(resultSet.getInt("Id"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Country_Id"),
                    resultSet.getInt("User_Id"));
        } catch (SQLException e) {
            System.out.println("Airline " + name + " is not in AirlineCompany table!");
        }
        return airlineCompany;
    }


}
