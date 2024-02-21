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
public class SetConfig {

    public static void customConfiguration(int portNumber, String username, String password) throws Exception {

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("app.config"));
            
            String url = "jdbc:mysql://127.0.0.1:3306/biblioteka2";
            properties.setProperty("username", username);
            properties.setProperty("password", password);
            properties.setProperty("port", portNumber + "");
            properties.store(new FileOutputStream("app.config"), "updated..");
        } catch (FileNotFoundException ex) {
            throw new Exception("Database configuration error.");
        } catch (IOException ex) {
            throw new Exception("Database configuration error.");
        }

    }    
}
