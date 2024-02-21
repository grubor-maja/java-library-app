/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.repository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Maja
 */
public class DBConnection {
    
    private Connection connection;
    private static DBConnection instance;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        try {
            Properties properties=new Properties();
            properties.load(new FileInputStream("app.config"));
            String url = "jdbc:mysql://127.0.0.1:3306/biblioteka2";
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);             
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return connection;
    } 
    
}
