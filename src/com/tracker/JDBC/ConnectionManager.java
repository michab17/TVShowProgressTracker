package com.tracker.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static Connection connection = null;

    private static void makeConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/student_grade_book";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(connection == null) {
            makeConnection();
        }
        return connection;
    }
}
