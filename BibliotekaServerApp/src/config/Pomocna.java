/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class Pomocna {
    
    public void odradi() {
        Properties prop = new Properties();
        OutputStream out = null;
        try { 
            out = new FileOutputStream("app.config");
            prop.setProperty("port", "9000");
            prop.setProperty("username", "root");
            prop.setProperty("password", "root");
            prop.store(out, null);
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(Pomocna.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pomocna.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(out!=null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Pomocna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static void main(String[] args) {
        Pomocna p = new Pomocna();
        p.odradi();
    }
}
