/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.clanBiblioteke;

import controller.Controller;
import coordinator.Coordinator;
import domain.ClanBiblioteke;
import form.FrmAddClanBiblioteke;
import form.FrmClanBiblioteke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class AddClanBibliotekeController {

    private final FrmAddClanBiblioteke frmAddClanBiblioteke;
    private final ClanBibliotekeController cbController;
    
    
    public AddClanBibliotekeController(FrmAddClanBiblioteke frmAddClanBiblioteke, ClanBibliotekeController cbController) {
        this.frmAddClanBiblioteke = frmAddClanBiblioteke;
        this.cbController = cbController;
        addActionListener();
    }
    

    public void openForm() {
        frmAddClanBiblioteke.pack();
        frmAddClanBiblioteke.setVisible(true);
        
    }    
    
    private void addActionListener() {
        frmAddClanBiblioteke.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        try {        
        String ime = frmAddClanBiblioteke.getTxtIme().getText().trim();
        String prezime = frmAddClanBiblioteke.getTxtPrezime().getText().trim();
        String email = frmAddClanBiblioteke.getTxtEmail().getText().trim();
        validate(ime,prezime,email);
        ClanBiblioteke noviClan = new ClanBiblioteke( ime, prezime, email);

        
            noviClan = Controller.getInstance().addClanBiblioteke(noviClan);
            if(noviClan == null) {
                JOptionPane.showMessageDialog(frmAddClanBiblioteke.getRootPane(), "Sistem ne moze da kreira clana biblioteke.");

            } else {
                String message = String.format("Sistem je zapamtio clana biblioteke:\nClanBibliotekeID: %d\nIme: %s\nPrezime: %s\nEmail: %s\n",            
            noviClan.getClanBibliotekeID(), noviClan.getIme(), noviClan.getPrezime(), noviClan.getEmail());    
                        JOptionPane.showMessageDialog(frmAddClanBiblioteke.getRootPane(), message);
                Coordinator.getInstance().notifyObservers();
                frmAddClanBiblioteke.dispose();
            }
        } catch (IOException ex) {
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmAddClanBiblioteke.getRootPane(), "Sistem ne moze da kreira clana biblioteke.");
            
            frmAddClanBiblioteke.getLblError().setText("<html>"+ex.getMessage()+"</html>");
            frmAddClanBiblioteke.getLblError().setForeground(Color.red);
        }                
            }

            private void validate(String ime, String prezime, String email) throws Exception {
                String errorMessage="";
                if(ime.isEmpty()) errorMessage+="Morate uneti ime <br>";
                if(prezime.isEmpty()) errorMessage+="Morate uneti prezime <br>";
                if(email.isEmpty()) errorMessage+="Morate uneti email <br>";
                if(!ime.matches(".*[a-zA-Z].") && !ime.isEmpty()) errorMessage+="Morate uneti string za ime. <br>";
                if(!prezime.matches(".*[a-zA-Z].") && !prezime.isEmpty()) errorMessage+="Morate uneti string za prezime. <br>";
                if(!email.contains("@")&& !email.isEmpty()) errorMessage+="Morate uneti @ u email. <br>";
                if(!errorMessage.isEmpty() ) throw new Exception(errorMessage);
            }


        
        });
    }

    
}
