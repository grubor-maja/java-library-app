/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.primerak;

import controller.Controller;
import controller.primerak.PrimerciController;
import coordinator.Coordinator;
import domain.Knjiga;
import domain.Pozajmica;
import domain.Primerak;
import form.FrmAddClanBiblioteke;
import form.FrmAddPrimerak;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Maja
 */
public class AddPrimerakController {
    
    private final FrmAddPrimerak frmAddPrimerak;
    private final PrimerciController primerakController;
    private final Knjiga k;

    public AddPrimerakController(FrmAddPrimerak frmAddPrimerak, PrimerciController primerakController,Knjiga k) {
        this.frmAddPrimerak = frmAddPrimerak;
        this.primerakController = primerakController;
        frmAddPrimerak.getTxtISBN().setText(k.getISBN()+"");
        this.k =k;
        addActionListener();
        
    }

    public void openForm() {
        frmAddPrimerak.pack();
        frmAddPrimerak.setVisible(true);  
        frmAddPrimerak.getLblErrorMessage().setText("");
    } 

    private void addActionListener() {
        frmAddPrimerak.getBtnDodaj().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                frmAddPrimerak.getLblErrorMessage().setText("");
                String izdavac = frmAddPrimerak.getTxtIzdavac().getText().trim();
                validate(izdavac);
                Primerak p = new Primerak();
                p.setKnjiga(k);
                p.setIzdavac(izdavac);
                p.setPozajmica(null);
                    p = Controller.getInstance().addPrimerak(p);
                    Pozajmica p2 = new Pozajmica();                   
                    primerakController.primerci.add(p);
                    primerakController.populateTablePrimerci2();
                    String message = String.format("Sistem je zapamtio primerak:\nISBN: %d\nIzdavac: %s\n",
                p.getKnjiga().getISBN(), p.getIzdavac());            
                    JOptionPane.showMessageDialog(frmAddPrimerak.getRootPane(),message);
                    Coordinator.getInstance().notifyObservers();
                    frmAddPrimerak.getLblErrorMessage().setText("");
                    frmAddPrimerak.dispose();
                }  catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAddPrimerak.getRootPane(),"Sistem ne moze da zapamti primerak.");
                    frmAddPrimerak.getLblErrorMessage().setText("<html>"+ex.getMessage()+"</html>");
                    frmAddPrimerak.getLblErrorMessage().setForeground(Color.red);
                }
            }
        
        
        });
    }
    private void validate(String izdavac) throws Exception {
        String errorMessage="";
        if(izdavac.isEmpty()) errorMessage+="Morate uneti ime <br>";
        if(!izdavac.matches(".*[a-zA-Z].") && !izdavac.isEmpty()) errorMessage+="Izdavac sme da sadrzi samo karaktere. <br>";
        if(!errorMessage.isEmpty() ) throw new Exception(errorMessage);
    }      
}
