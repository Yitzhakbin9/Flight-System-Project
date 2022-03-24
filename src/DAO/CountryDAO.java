package DAO;

import POCO.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements DAO<Country> {

    List<Country> countries = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();

    @Override
    public Country Get(int id) {
        Country country = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Countries\"" + "where \"Id\" = " + id);
            resultSet.next();
            country = new Country(resultSet.getInt("Id"),
                    resultSet.getString("Name"));

        } catch (SQLException e) {
            System.out.println("id "+id+" is not in the Country table!");
        }
        return country;
    }

    @Override
    public List<Country> GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Countries\"");
            while (resultSet.next()) {
                Country country = new Country(resultSet.getInt("Id"),
                        resultSet.getString("Name"));
                this.countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.countries;
    }

    @Override
    public void Add(Country country) {
        try {
            stm.executeQuery("INSERT INTO \"Countries\"(\"Name\") VALUES('" + country.name + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Country country) {
        try {
            stm.executeUpdate("DELETE from \"Countries\"  WHERE \"Id\"=" + country.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Country country, int id) {
        try {
            stm.executeUpdate("UPDATE \"Countries\"\n" +
                    "SET \"Name\" = '"+country.name+"'"+
                    "WHERE \"Id\" =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
