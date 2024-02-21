/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.main;

import controller.Controller;
import coordinator.Coordinator;
import form.FrmMain;
import domain.Administrator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import coordinator.Coordinator;
import javax.swing.JRootPane;

/**
 *
 * @author Maja
 */
public class MainController {

    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }    

    public FrmMain getFrmMain() {
        return frmMain;
    }
    
    
    
    public void openForm() {
        Administrator admin = (Administrator) Coordinator.getInstance().getParam("admin");
        frmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frmMain.pack();
        frmMain.getLblWelcomeMessage().setText("Welcome "+admin.getIme()+" to your library.");
        frmMain.setVisible(true);        
    }
    

    private void addActionListener() {
        frmMain.getMenuItemViewAllClanovi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openClanoviBibliotekeForm();
            }
        
        
        });
        frmMain.getMenuItemAddClan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddClanBiblioteke(frmMain);
            }
        
        
        });        
        frmMain.getMenuItemViewAllPrimerci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openPrimerciForm();
            }
            
        });
        frmMain.getMenuItemViewAllKnjiga().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openKnjigaForm(frmMain);
            }
            
        }); 
        frmMain.getMenuItemAddKnjiga().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddKnjiga(frmMain);
            }
            
        });         
        frmMain.getMenuItemKreirajPozajmicu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openIznajmljivanjeForm(frmMain);
            }
            
        }); 
        frmMain.getMenuItemIzbrisiPozajmicu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openVratiForm(frmMain);
            }
            
        });   
        
        frmMain.getMenuItemViewAllRecenzije().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openRecenzijeForm();
            }
            
        });
        frmMain.getMenuItemAddRecenzija().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openAddRecenzija(frmMain);
            }
            
        });        
        frmMain.getBtnLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().logout((Administrator) Coordinator.getInstance().getParam("admin"));
                    frmMain.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });        
    }


}
