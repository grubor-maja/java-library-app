/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Maja
 */
public class GetConfig {
     public static int getServerPort() throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("app.config"));
            return Integer.valueOf(properties.getProperty("port"));
        } catch (FileNotFoundException ex) {
            throw new Exception("Cannot find config file.");
        } catch (IOException ex) {
            throw new Exception("Cannot find config file.");
        }
    }


    public static String getDBUsername() throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("app.config"));
            return properties.getProperty("username");
        } catch (Exception e) {
            throw new Exception("Cannot find config file.");
        }
    }

    public static String getDBPassword() throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("app.config"));
            return properties.getProperty("password");
        } catch (Exception e) {
            throw new Exception("Cannot find config file.");
        }
    }



   
}
