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
import domain.Primerak;
import form.FrmAddKnjiga;
import form.FrmEditKnjiga;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Maja
 */
public class EditKnjigaController {
    
    private final FrmEditKnjiga frmEditKnjiga;
    private final KnjigaController knjigeController;
    Knjiga k;
    List<Autor> autori;

    public EditKnjigaController(FrmEditKnjiga frmEditKnjiga, KnjigaController knjigeController,Knjiga k) {
        this.frmEditKnjiga = frmEditKnjiga;
        this.knjigeController = knjigeController;
        this.k =k;
        autori = new ArrayList<>();
        frmEditKnjiga.getTxtISBN().setText(k.getISBN()+"");
        addActionListener();
    }



    public void openForm() {
        frmEditKnjiga.pack();
        frmEditKnjiga.setVisible(true);
        
    } 
    
    private void addActionListener() {
        populateCombo();
        frmEditKnjiga.getBtnIzmeni().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                frmEditKnjiga.getLblErrorMessage().setText("");
                String naslov = frmEditKnjiga.getTxtNaslov().getText().trim();
                Autor autor = (Autor) frmEditKnjiga.getCbAutori().getSelectedItem();                    
                String zanr = frmEditKnjiga.getTxtZanr().getText().trim();                               
                int godinaIzdanja = Integer.parseInt(frmEditKnjiga.getTxtGodinaIzdanja().getText().trim());
                validate(naslov,zanr,godinaIzdanja);
                k.setNaslov(naslov);
                k.setAutor(autor);
                k.setGodinaIzdanja(godinaIzdanja);
                k.setZanr(zanr);
                k = Controller.getInstance().editKnjiga(k);
                knjigeController.populateTableKnjige();
                String message = String.format("Sistem je zapamtio knjigu:\nNaslov: %s\nAutor: %s\nISBN: %d\nZanr: %s\nGodina izdanja: %d",
                k.getNaslov(), k.getAutor(), k.getISBN(), k.getZanr(), k.getGodinaIzdanja());   
                JOptionPane.showMessageDialog(frmEditKnjiga.getRootPane(), message);
                Coordinator.getInstance().notifyObservers();
                frmEditKnjiga.dispose();
                } catch(NumberFormatException nefx) {
                    frmEditKnjiga.getLblErrorMessage().setText("<html> Morate uneti ceo broj od 0 do 2024 za godinu </html>");
                    frmEditKnjiga.getLblErrorMessage().setForeground(Color.red);
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmEditKnjiga.getRootPane(), "Sistem ne moze da zapamti knjigu.");
                    frmEditKnjiga.getLblErrorMessage().setText("<html>"+ex.getMessage()+"</html>");
                    frmEditKnjiga.getLblErrorMessage().setForeground(Color.red);
                }
            }


        
        
        });
    }    

    private void populateCombo() {
        try {
            autori = Controller.getInstance().getAllAutori();
            frmEditKnjiga.getCbAutori().setModel(new DefaultComboBoxModel(autori.toArray()));
        } catch (IOException ex) {
            Logger.getLogger(EditKnjigaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditKnjigaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            private void validate(String naslov,String zanr,int godina) throws Exception {
                String errorMessage="";
                if(godina<0 || godina >2024) errorMessage+="Morate uneti broj od 0 do 2024 za godinu. <br>";
                if(naslov.isEmpty() || zanr.isEmpty()) errorMessage+="Morate popuniti sva polja. <br>";
                if((!naslov.matches(".*[a-zA-Z].")) && (!naslov.isEmpty()) ) errorMessage+="Morate uneti karaktere za naslov. <br>";
                if((!zanr.matches(".*[a-zA-Z].")) && (!zanr.isEmpty()) ) errorMessage+="Morate uneti karaktere za zanr. <br>";
                if(!errorMessage.isEmpty() ) throw new Exception(errorMessage);
            }    
}
