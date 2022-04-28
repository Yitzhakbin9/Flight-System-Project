package com.example.AirlineProject.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {

    private Connection connection = null;
    private Statement statement = null;

    public SqlConnection() {
    }

    public Statement getStatement() {
        try {
            statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public Connection getConnection(String url, String user, String password) {
            try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.connection;
    }
}
