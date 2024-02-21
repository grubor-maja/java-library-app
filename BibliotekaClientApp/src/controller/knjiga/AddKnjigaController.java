/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.knjiga;

import controller.Controller;
import controller.knjiga.KnjigaController;
import coordinator.Coordinator;
import domain.Autor;
import domain.Knjiga;
import form.FrmAddClanBiblioteke;
import form.FrmAddKnjiga;
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
public class AddKnjigaController {
    
    private final FrmAddKnjiga frmAddKnjiga;
    private final KnjigaController knjigeController;
    private List<Autor> autori;

    public AddKnjigaController(FrmAddKnjiga frmAddKnjiga, KnjigaController knjigeController) {
            this.frmAddKnjiga = frmAddKnjiga;
            this.knjigeController = knjigeController;    
        try {

            autori = Controller.getInstance().getAllAutori();
            frmAddKnjiga.getCbAutori().setModel(new DefaultComboBoxModel(autori.toArray()));
            addActionListener();
        } catch (IOException ex) {
            Logger.getLogger(AddKnjigaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddKnjigaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void openForm() {
        frmAddKnjiga.pack();
        frmAddKnjiga.setVisible(true);
        
    } 
    
    private void addActionListener() {
        frmAddKnjiga.getBtnDodaj().addActionListener(new ActionListener(){


            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                frmAddKnjiga.getLblErrorMessage().setText("");
                Autor a = (Autor) frmAddKnjiga.getCbAutori().getSelectedItem();
                String naslov = frmAddKnjiga.getTxtNaslov().getText();            
                String zanr =frmAddKnjiga.getTxtZanr().getText().trim();               
                int godinaIzdanja = Integer.parseInt(frmAddKnjiga.getTxtGodinaIzdanja().getText());
                long ISBN = Long.parseLong(frmAddKnjiga.getTxtISBN().getText().trim());               
                validate(naslov,zanr,a,godinaIzdanja,ISBN);               
                Knjiga k = new Knjiga();
                k.setAutor(a);
                k.setGodinaIzdanja(godinaIzdanja);
                k.setNaslov(naslov);
                k.setZanr(zanr);
                k.setISBN(ISBN);
                k = Controller.getInstance().addKnjiga(k);       
                System.out.println("ISBN je: "+k.getISBN());
                String message = String.format("Sistem je zapamtio knjigu:\nNaslov: %s\nAutor: %s\nISBN: %d\nZanr: %s\nGodina izdanja: %d",
            k.getNaslov(), k.getAutor(), k.getISBN(), k.getZanr(), k.getGodinaIzdanja());            
                JOptionPane.showMessageDialog(frmAddKnjiga.getRootPane(),message); 
                Coordinator.getInstance().notifyObservers();
                frmAddKnjiga.dispose();
            } catch (NumberFormatException nefx) {
                JOptionPane.showMessageDialog(frmAddKnjiga.getRootPane(), "Sistem ne moze da zapamtki knjigu.");
                frmAddKnjiga.getLblErrorMessage().setText("<html> Morate uneti godinu od 0 do 2024 <br>i ISBN od 13 cifara. </html>");
                frmAddKnjiga.getLblErrorMessage().setForeground(Color.red);
            }  catch (Exception ex) {
                JOptionPane.showMessageDialog(frmAddKnjiga.getRootPane(), "Sistem ne moze da zapamtki knjigu.");
                frmAddKnjiga.getLblErrorMessage().setText("<html>"+ex.getMessage()+"</html>");
                frmAddKnjiga.getLblErrorMessage().setForeground(Color.red);
            }
        }
        });
    }
            private void validate(String naslov, String zanr,Autor a, int godinaIzdanja,long ISBN) throws Exception {
                String errorMessage="";
                if(String.valueOf(ISBN).length()!=13) errorMessage+="Morate uneti 13 cifara za ISBN. <br>";
                if(godinaIzdanja<0 || godinaIzdanja>2024) errorMessage+="Morate uneti broj od 0 do 2024 za godinu. <br>";
                if(naslov.isEmpty() || zanr.isEmpty()) errorMessage+="Morate popuniti sva polja. <br>";
                if((!naslov.matches(".*[a-zA-Z].")) && (!naslov.isEmpty()) ) errorMessage+="Morate uneti karaktere za naslov. <br>";
                if((!zanr.matches(".*[a-zA-Z].")) && (!zanr.isEmpty()) ) errorMessage+="Morate uneti karaktere za zanr. <br>";
                if(a==null) errorMessage+="Morate selektovati autora. <br>";
                if(!errorMessage.isEmpty() ) throw new Exception(errorMessage);
            }       
}
