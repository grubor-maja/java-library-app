/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.pozajmica;

import controller.Controller;
import coordinator.Coordinator;
import domain.Pozajmica;
import domain.Primerak;
import form.FrmRecenzije;
import form.FrmDeletePozajmica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TabelaPozajmica;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class DeletePozajmicaController implements Observer {
    
    private final FrmDeletePozajmica frmVracanje;
    private List<Pozajmica> pozajmice;
    private List<Primerak> primerci;

    public DeletePozajmicaController(FrmDeletePozajmica frmVracanje) {
        this.frmVracanje = frmVracanje;
        Coordinator.getInstance().addObserver(this);
        addActionListener();
    }
    public void openForm() {
        frmVracanje.setVisible(true);
    }     
    
    private void addActionListener() {
        populateTable();
        frmVracanje.getBtnIzbrisi().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                try {
                int selectedRow = frmVracanje.getTabelaPozajmice().getSelectedRow();
                Pozajmica p = pozajmice.get(selectedRow);
                primerci = Controller.getInstance().getAllPrimerak();
                for(Primerak p2 : primerci) {
                if(p2.getPozajmica().getPozajmicaID() == p.getPozajmicaID()) {
                    p2.setPozajmica(null);                   
                    Controller.getInstance().editPrimerak(p2);
                }
                }
                
                Pozajmica poz2 = Controller.getInstance().deletePozajmica(p);
                if(poz2!=null) {
                    populateTable();
                    String message = String.format("Sistem je obrisao pozajmicu:\nPozajmicaID: %d\n",            
                p.getPozajmicaID());
                    JOptionPane.showMessageDialog(frmVracanje.getRootPane(), message);    
                    frmVracanje.dispose();
                }
            } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frmVracanje.getRootPane(),"Sistem ne moze da obrise pozajmicu. " ); 
                    System.out.println("Sistem ne moze da obrise pozajmicu. "+ex.getLocalizedMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmVracanje.getRootPane(),"Sistem ne moze da obrise pozajmicu. " ); 
                System.out.println("Sistem ne moze da obrise pozajmicu. "+ex.getLocalizedMessage());
            }                
            }
        
        });
    }
    
    private void populateTable() {
        try {
            pozajmice = Controller.getInstance().getAllPozajmica();
            TabelaPozajmica ti = new TabelaPozajmica(pozajmice);
            frmVracanje.getTabelaPozajmice().setModel(ti);
        } catch (IOException ex) {
            System.out.println("Sistem ne moze da ucita pozajmice. "+ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("Sistem ne moze da ucita pozajmice. "+ex.getLocalizedMessage());
        }
    }    

    @Override
    public void update() {
        populateTable();
    }
}
