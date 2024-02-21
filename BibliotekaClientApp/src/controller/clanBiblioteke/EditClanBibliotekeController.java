/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.clanBiblioteke;

import controller.Controller;
import controller.clanBiblioteke.ClanBibliotekeController;
import domain.ClanBiblioteke;
import form.FrmEditClanBiblioteke;
import form.FrmAddClanBiblioteke;
import form.FrmClanBiblioteke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Maja
 */
public class EditClanBibliotekeController {

    private final FrmEditClanBiblioteke frmEditClanBiblioteke;
    private final ClanBibliotekeController cbController;
    private final ClanBiblioteke clanBiblioteke;

    public EditClanBibliotekeController(FrmEditClanBiblioteke frmEditClanBiblioteke2, ClanBibliotekeController cbController, ClanBiblioteke clanBiblioteke) {
        this.frmEditClanBiblioteke = frmEditClanBiblioteke2;
        this.cbController = cbController;
        this.clanBiblioteke = clanBiblioteke;
        addActionListener();
    }


    
    public void openForm() {
        populate();
        frmEditClanBiblioteke.pack();
        frmEditClanBiblioteke.setVisible(true);
        
        
    }  
    private void addActionListener() {
        
        frmEditClanBiblioteke.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmEditClanBiblioteke.getLblErrorMessage().setText("");
                String ime = frmEditClanBiblioteke.getTxtIme().getText().trim();
                String prezime = frmEditClanBiblioteke.getTxtPrezime().getText().trim();
                String email = frmEditClanBiblioteke.getTxtEmail().getText().trim();
                try {
                validate(ime,prezime,email);
                int clanID = Integer.parseInt(frmEditClanBiblioteke.getTxtID().getText().trim());
                ClanBiblioteke noviClan = new ClanBiblioteke(clanID, ime, prezime, email);
                noviClan = Controller.getInstance().editClanBiblioteke(noviClan);
                if(noviClan == null) {
                    System.out.println("Neuspesno");
                    frmEditClanBiblioteke.getLblErrorMessage().setText("");
                    JOptionPane.showMessageDialog(frmEditClanBiblioteke.getRootPane(), "Sistem ne moze da zapamti clana biblioteke.");

                } else {
                    System.out.println("Uspesno");
            String message = String.format("Sistem je zapamtio clana biblioteke:\nClanBibliotekeID: %d\nIme: %s\nPrezime: %s\nEmail: %s\n",            
        noviClan.getClanBibliotekeID(), noviClan.getIme(), noviClan.getPrezime(), noviClan.getEmail());    
                    JOptionPane.showMessageDialog(frmEditClanBiblioteke.getRootPane(), message);                        cbController.populateTable();
                    frmEditClanBiblioteke.getLblErrorMessage().setText("");
                    frmEditClanBiblioteke.dispose();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmEditClanBiblioteke.getRootPane(), "Sistem ne moze da zapamti clana biblioteke.");
                frmEditClanBiblioteke.getLblErrorMessage().setText("<html>"+ex.getMessage()+"</html>");
                frmEditClanBiblioteke.getLblErrorMessage().setForeground(Color.red);
            }                
                    }

        });
    }    

    private void populate() {
        System.out.println("Clan je: "+clanBiblioteke.toString());
        frmEditClanBiblioteke.getTxtIme().setText(clanBiblioteke.getIme());
        frmEditClanBiblioteke.getTxtPrezime().setText(clanBiblioteke.getPrezime());
        frmEditClanBiblioteke.getTxtEmail().setText(clanBiblioteke.getEmail());
        frmEditClanBiblioteke.getTxtID().setText(String.valueOf(clanBiblioteke.getClanBibliotekeID()));
        
    }
    private void validate(String ime, String prezime, String email) throws Exception {
        String errorMessage="";
        if(ime.isEmpty()) errorMessage+="Morate uneti ime <br>";
        if(prezime.isEmpty()) errorMessage+="Morate uneti prezime <br>";
        if(email.isEmpty()) errorMessage+="Morate uneti email <br>";
        if(!ime.matches(".*[a-zA-Z].") && !ime.isEmpty()) errorMessage+="Morate uneti string za ime. <br>";
        if(!prezime.matches(".*[a-zA-Z].") && !prezime.isEmpty()) errorMessage+="Morate uneti string za prezime. <br>";        
        if(!email.contains("@")&& !email.isEmpty()) errorMessage+="Morate uneti @ u email. <br>";
        if(!email.matches("^[^\\d].+@.+\\.com$"))errorMessage+="Format email-a: ime@domen.com <br>";
        if(!errorMessage.isEmpty() ) throw new Exception(errorMessage);
    }    
    
    
}
