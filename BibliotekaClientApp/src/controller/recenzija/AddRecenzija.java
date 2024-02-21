/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.recenzija;

import controller.Controller;
import coordinator.Coordinator;
import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Recenzija;
import form.FrmAddKnjiga;
import form.FrmAddRecenzija;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Maja
 */
public class AddRecenzija {
    private final FrmAddRecenzija frmAddRecenzija;
    private  RecenzijeController recenzijaController;
    private List<Knjiga> knjige;
    private List<ClanBiblioteke> clanovi;
    

    public AddRecenzija(FrmAddRecenzija frmAddRecenzija, RecenzijeController recenzijaController) {
        this.frmAddRecenzija = frmAddRecenzija;
        this.recenzijaController = recenzijaController;
        addActionListener();
    }

    public void openForm() {
        frmAddRecenzija.pack();
        frmAddRecenzija.setVisible(true);
        
    }     
    
    private void addActionListener() {
        populateCombo();
        frmAddRecenzija.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                frmAddRecenzija.getLblErrorMessage().setText("");
                String ocenaString =frmAddRecenzija.getTxtOcena().getText();
                String tekst = frmAddRecenzija.getTxtTekst().getText();
                validate(ocenaString,tekst);
                int ocena = Integer.parseInt(frmAddRecenzija.getTxtOcena().getText());
                if(ocena<0 || ocena >10) throw new NumberFormatException();
                Knjiga k = (Knjiga) frmAddRecenzija.getCbKnjige().getSelectedItem();
                ClanBiblioteke cb = (ClanBiblioteke) frmAddRecenzija.getCbClanovi().getSelectedItem();
                Recenzija r = new Recenzija(k, cb, tekst, ocena);           
                r = Controller.getInstance().addRecenzija(r);
                if(r!=null) {
                    String message = String.format("Sistem je zapamtio recenziju:\nClan: %s\nTekst: %s\nOcena: %d\n",            
                    r.getClanBiblioteke().getIme()+" "+r.getClanBiblioteke().getPrezime(), r.getTekstRecenzije(), r.getOcena());    
                    JOptionPane.showMessageDialog(frmAddRecenzija.getRootPane(), message);                
                    frmAddRecenzija.getLblErrorMessage().setText("");
                    Coordinator.getInstance().notifyObservers();
                    frmAddRecenzija.dispose();
                } else {
                    JOptionPane.showMessageDialog(frmAddRecenzija.getRootPane(), "Ovaj clan biblioteke je vec dodao recenziju za datu knjigu.");    
                    frmAddRecenzija.getLblErrorMessage().setText("");    
                    }
                } catch(NumberFormatException nfex) {
                    JOptionPane.showMessageDialog(frmAddRecenzija.getRootPane(), "Sistem ne moze da zapamti recenziju.");
                    frmAddRecenzija.getLblErrorMessage().setText("<html> Morate uneti ceo broj od 0 do 10 za ocenu. </html>");
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAddRecenzija.getRootPane(), "Sistem ne moze da zapamti recenziju.");
                    frmAddRecenzija.getLblErrorMessage().setText("<html>"+ex.getMessage()+"</html>");
                    frmAddRecenzija.getLblErrorMessage().setForeground(Color.red);
                }                
            }
        
        });
    }
    private void populateCombo() {
        try {
            clanovi = Controller.getInstance().getAllClanBiblioteke();
            knjige  = Controller.getInstance().getAllKnjiga();
            frmAddRecenzija.getCbClanovi().setModel(new DefaultComboBoxModel(clanovi.toArray()));
            frmAddRecenzija.getCbKnjige().setModel(new DefaultComboBoxModel(knjige.toArray()));
        } catch (IOException ex) {
            System.out.println("Greska pri ucitavanju clanova biblioteke. "+ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("Greska pri ucitavanju clanova biblioteke. "+ex.getLocalizedMessage());
        }
        
    }  
    private void validate(String ocena, String tekst) throws Exception {
        String errorMessage="";
        if(tekst.isEmpty()) errorMessage+="Morate uneti tekst recenzije. <br>";
        if(ocena.isEmpty()) errorMessage+="Morate uneti ocenu. <br>"; 
        if(!errorMessage.isEmpty() ) throw new Exception(errorMessage);
    }       
}
