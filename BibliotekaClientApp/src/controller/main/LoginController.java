/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.main;

import controller.Controller;
import coordinator.Coordinator;
import domain.Administrator;
import exception.AlreadyLoggedInException;
import exception.UserDoesntExistException;
import form.FrmLogin;
import form.FrmMain;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class LoginController {
 
private final FrmLogin frmLogin;

public LoginController(FrmLogin frmLogin) {
    this.frmLogin = frmLogin;
    addActionListener();
}

public void openForm() {
    frmLogin.setVisible(true);
}    

    private void addActionListener() {
        frmLogin.btnLoginAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ulogujKorisnika(e);
            }

            private void ulogujKorisnika(ActionEvent e) {
                resetujFormu();
            }

            private void resetujFormu() {
                try {
                frmLogin.getLblMessage().setText("");
                String username = frmLogin.getTxtUsername().getText().trim();
                String password = frmLogin.getTxtPassword().getText().trim();
                validateForm(username,password);
                Administrator admin = new Administrator(username, password);
                
                    admin = Controller.getInstance().login(admin);
                  
                        frmLogin.dispose();
                        Coordinator.getInstance().addParam("admin", admin);
                        Coordinator.getInstance().openMainForm();

                } catch (Exception ex) {
                    frmLogin.getLblMessage().setText(ex.getMessage());
                    frmLogin.getLblMessage().setForeground(Color.red);
                } 
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if(username.isEmpty() && password.isEmpty()) {
                    errorMessage = "Please fill in your username and password!\n";
                } else if (username.isEmpty()) {
                    frmLogin.getLblMessage().setText("Username can not be empty!");
                    errorMessage = "Please fill in your username!\n";
                } else if(password.isEmpty()) {
                    frmLogin.getLblMessage().setText("Password can not be empty!");
                    errorMessage = "Please fill in your password!\n";
                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }
        
        
        });
    }
}
