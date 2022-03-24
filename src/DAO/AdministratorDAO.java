package DAO;

import POCO.Administrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO implements DAO<Administrator> {

    List<Administrator> administrators = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();


    @Override
    public Administrator Get(int id) {
        Administrator admin = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Administrators\" where \"Id\" =" + id);
            resultSet.next();
            admin = new Administrator(resultSet.getInt("Id"),
                    resultSet.getString("First_Name"),
                    resultSet.getString("Last_Name"),
                    resultSet.getInt("User_Id"));

        } catch (SQLException e) {
            System.out.println("id " + id + " is not in the Administrator table!");
        }
        return admin;
    }

    @Override
    public List<Administrator> GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Administrators\"");
            while (resultSet.next()) {
                Administrator admin = new Administrator(resultSet.getInt("Id"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getInt("User_Id"));
                this.administrators.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.administrators;
    }

    @Override
    public void Add(Administrator admin) {
        try {
            stm.executeQuery("INSERT INTO \"Administrators\" (\"Id\",\"First_Name\",\"Last_Name\",\"User_Id\")\n" +
                    "    VALUES\n" +
                    "            (" + admin.id + ",'" + admin.firstName + "','" + admin.lastName + "'," + admin.userId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Administrator admin) {
        try {
            stm.executeUpdate("DELETE from \"Administrators\"  WHERE \"Id\"=" + admin.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Administrator admin, int id) {
        try {
            stm.executeUpdate("UPDATE \"Administrators\"\n" +
                    "SET \"First_Name\" = '" + admin.firstName + "' , \"Last_Name\" = '" + admin.lastName + "' , \"User_Id\"=" + admin.userId + "\n" +
                    "WHERE \"Id\"=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int howManyAdmins() {
        int count = 0;
        try {
            ResultSet resultSet = stm.executeQuery("select Count(*)  from \"Administrators\"");
            resultSet.next();
            count = resultSet.getInt("count");
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
