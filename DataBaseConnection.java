package com.example.dataproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "clinic";
        String databaseUser = "root";
        String databasePassword = "1200430";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }

        return databaseLink;
    }


}
