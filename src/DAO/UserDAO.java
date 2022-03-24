package DAO;

import POCO.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    List<User> users = new ArrayList<>();
    SqlConnection sqlConnection = new SqlConnection();
    Connection connection = sqlConnection.getConnection(URL, USER, PASSWORD);
    Statement stm = sqlConnection.getStatement();

    @Override
    public User Get(int id) {
        User user = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Users\"" + "where \"Id\" = " + id);
            resultSet.next();
            user = new User(resultSet.getInt("Id"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Email"),
                    resultSet.getInt("User_Role"));
        } catch (SQLException e) {
            System.out.println("id " + id + " is not in the User table");
        }
        return user;
    }

    @Override
    public List GetAll() {
        try {
            ResultSet resultSet = stm.executeQuery("select * from \"Users\"");
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("Id"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getInt("User_Role"));
                this.users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.users;
    }

    @Override
    public void Add(User user) {
        try {
            stm.executeQuery("INSERT INTO \"Users\"(\"Username\",\"Password\",\"Email\",\"User_Role\")\n" +
                    "VALUES\n" +
                    "('" + user.username + "','" + user.password + "','" + user.email + "'," + user.userRole + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(User user) {
        try {
            stm.executeUpdate("DELETE from \"Users\"  WHERE \"Id\"=" + user.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(User user, int id) {
        try {
            stm.executeUpdate("UPDATE \"Users\"\n" +
                    "SET \"Username\" = '" + user.username +
                    "', \"Password\"='" + user.password +
                    "', \"Email\" = '" + user.email +
                    "', \"User_Role\"=" + user.userRole +
                    "WHERE \"Id\" =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ADDITIONAL FUNCTIONS :
     */

    public User getUserByUsername(String username) {
        User user = null;
        try {
            ResultSet resultSet = stm.executeQuery("select * from get_user_by_username('" + username + "')");
            resultSet.next();
            user = new User(resultSet.getInt("Id"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Email"),
                    resultSet.getInt("User_Role"));
        } catch (SQLException e) {
            System.out.println("username " + username + " is not in the database!");
        }
        return user;
    }


}
