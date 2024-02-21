/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.recenzija;

import controller.Controller;
import coordinator.Coordinator;
import domain.Recenzija;
import form.FrmRecenzije;
import form.FrmPrimerak;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TabelaRecenzije;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class RecenzijeController implements Observer{

    private final FrmRecenzije frmRecenzije;
    List<Recenzija> recenzije;

    public RecenzijeController(FrmRecenzije frmRecenzije) {
        this.frmRecenzije = frmRecenzije;
        Coordinator.getInstance().addObserver(this);
        addActionListener();
        
    }
    public void openForm() {
        frmRecenzije.pack();
        frmRecenzije.setVisible(true);
    }  

    public FrmRecenzije getFrmRecenzije() {
        return frmRecenzije;
    }
    
    private void addActionListener() {
        populateTable();
        frmRecenzije.getBtnDetails().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = frmRecenzije.getTableRecenzije().getSelectedRow();
                if(selectedRow == -1)
                    JOptionPane.showMessageDialog(frmRecenzije, "Morate izabrati recenziju da biste videli njene detalje.");
                else {
                    Recenzija re = recenzije.get(selectedRow);
                    Coordinator.getInstance().openRecenzijeDetails(getFrmRecenzije(),re);
                }
                
            }
        
        });
 
    }
    
    public void populateTable() {
        try {
            recenzije = Controller.getInstance().getAllRecenzija();
            TabelaRecenzije tr = new TabelaRecenzije(recenzije);
            frmRecenzije.getTableRecenzije().setModel(tr);
        } catch (IOException ex) {
            Logger.getLogger(FrmRecenzije.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmRecenzije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        populateTable();
    }
}
