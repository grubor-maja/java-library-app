/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller;

import config.GetConfig;
import config.SetConfig;
import controller.Controller;
import domain.Administrator;
import domain.ClanBiblioteke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import view.form.FrmCustomSettings;
import view.form.FrmDefaultSettings;
import view.form.FrmMain;

/**
 *
 * @author Maja
 */
public class ViewController {
    private FrmMain frmMain;
    private List<Administrator> onlineUsers;

    public ViewController(FrmMain frmMain) {
        this.frmMain = frmMain;
        onlineUsers = new ArrayList<>();
        setListener();
        setForm();
        
    }

    public void openForm() {
        frmMain.setLocationRelativeTo(null);
        frmMain.setVisible(true);
    }
    private void setListener() {
        frmMain.getBtnStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                startServer();
            }

            private void startServer() {
                try {
                    Controller.getInstance().startServer();
                    frmMain.getBtnStart().setVisible(false);
                    frmMain.getBtnStop().setVisible(true);
                    frmMain.getLblMessage().setText("Status: Server is running");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmMain, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmMain.getBtnStop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                stopServer();
                frmMain.getBtnStart().setVisible(true);
                frmMain.getBtnStop().setVisible(false);
                frmMain.getLblMessage().setText("Server status: Stopped");
            }

            private void stopServer() {
                try {
                    Controller.getInstance().stopServer();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmMain, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmMain.getMenuItemDefaultConfig().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentConfig();
            }

            private void setCurrentConfig() {
                FrmDefaultSettings formDefault = new FrmDefaultSettings(frmMain, true);
                try {
                    formDefault.getTxtPort().setEditable(false);
                    formDefault.getTxtUsername().setEditable(false);
                    formDefault.getTxtPassword().setEditable(false);
                    

                    
                    formDefault.getTxtUsername().setText(GetConfig.getDBUsername());
                    formDefault.getTxtPassword().setText(GetConfig.getDBPassword());
                    formDefault.getTxtPort().setText(GetConfig.getServerPort() + "");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formDefault, ex.getMessage(), "Configuration error.", JOptionPane.ERROR_MESSAGE);
                }

                formDefault.setLocationRelativeTo(null);
                formDefault.setVisible(true);
            }

        });

        frmMain.getMenuItemCustomConfig().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                customConfiguration();
            }
        });
    }

   private void setForm() {
        frmMain.getBtnStop().setVisible(false);
        frmMain.getLblMessage().setEnabled(true);
    }

    public void addOnlineUsers(Administrator admin) throws Exception {
        if (onlineUsers.contains(admin)) {
            throw new Exception("Ovaj admin je vec ulogovan.");
        }
        onlineUsers.add(admin);
    }

    private void customConfiguration() {
        FrmCustomSettings form = new FrmCustomSettings(frmMain, true);
       
        form.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //port
                if (form.getTxtPort1().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(form, "Unesite broj porta.");
                    return;
                }
                int portNumber = 0;
                try {
                    portNumber = Integer.parseInt(form.getTxtPort1().getText().trim());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(form, "Broj porta mora biti integer!");
                    return;
                }




                String username = form.getTxtUsername().getText().trim();
                String password = String.valueOf(form.getTxtPassword().getText().trim());

                try {
                    SetConfig.customConfiguration(portNumber, username, password);
                    JOptionPane.showMessageDialog(form, "Konfiguracija je azurirana.");
                    form.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(form, e.getMessage());
                }
                 

            }
           

        });
        form.setLocationRelativeTo(null);
        form.setVisible(true);
         

    }

    public void resetActiveUsers() {
        onlineUsers = new ArrayList<>();
    }

    public void addOnlineUser(Administrator admin) {
    }
    
}
