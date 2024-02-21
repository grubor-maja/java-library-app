/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.pozajmica;

import controller.Controller;
import coordinator.Coordinator;
import domain.Administrator;
import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Pozajmica;
import domain.Primerak;
import form.FrmClanBiblioteke;
import form.FrmAddPozajmica;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.TabelaClanBiblioteke;
import model.TabelaKnjige;
import model.TabelaPrimerak;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class AddPozajmicaController implements Observer{
    
    private final FrmAddPozajmica frmIznajmljivanje;
    private List<ClanBiblioteke> clanovi;
    private List<Knjiga> knjige;
    public List<Primerak> primerciZaIznajmiti;
    

    public AddPozajmicaController(FrmAddPozajmica frmIznajmljivanje) {
        this.frmIznajmljivanje = frmIznajmljivanje;
        primerciZaIznajmiti = new ArrayList<>();
        Coordinator.getInstance().addObserver(this);
        addActionListener();
    }
    public void openForm() {
        frmIznajmljivanje.pack();
        frmIznajmljivanje.setVisible(true);
    }    
    
    private void addActionListener() {
        populateClanovi();
        populateKnjige();
        populatePozajmica();
        
        frmIznajmljivanje.getBtnIzaberiPrimerak().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmIznajmljivanje.getTabelaKnjige().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmIznajmljivanje, "Morate izabrati knjigu.");
                    
                }
                else {
                    Knjiga k = knjige.get(row);
                    Coordinator.getInstance().openIzborPrimerka(getFrmIznajmljivanje(), k);
                }
            }
        
        
        });


        frmIznajmljivanje.getBtnPozajmica().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {                   

//                validate(rowClan, rowKnjiga, rowPrimerak);
                ClanBiblioteke cb = (ClanBiblioteke) frmIznajmljivanje.getCbClanovi().getSelectedItem();
//                Knjiga k = knjige.get(rowKnjiga);
//                Primerak primerak = primerciZaIznajmiti.get(rowPrimerak);
                
                Date datum = new Date();
                Pozajmica p = new Pozajmica();
                p.setAdmin((Administrator) Coordinator.getInstance().getParam("admin"));
                p.setClan(cb);
                java.util.Date utilDate = new java.util.Date();      
                p.setDatumPozajmice(new java.sql.Date(utilDate.getTime()));
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getDatumPozajmice());
                cal.add(Calendar.DAY_OF_MONTH, 14);
                Date utilDatumVracanja = cal.getTime();
                java.sql.Date sqlDatumVracanja = new java.sql.Date(utilDatumVracanja.getTime());
                p.setDatumVracanja(sqlDatumVracanja);
                p = Controller.getInstance().addPozajmica(p);
                for (Primerak prim : primerciZaIznajmiti) {
                    prim.setPozajmica(p);
                    Controller.getInstance().editPrimerak(prim);
                }

                if(p!=null) {
                     
                    String message = String.format("Sistem je zapamtio pozajmicu:\nPozajmicaID: %d\nClanBiblioteke: %s\n",            
                p.getPozajmicaID(), p.getClan().getIme() + " " + p.getClan().getPrezime());  
                    JOptionPane.showMessageDialog(frmIznajmljivanje.getRootPane(), message);    
                    Coordinator.getInstance().notifyObservers();                    
                    frmIznajmljivanje.dispose();
                }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmIznajmljivanje, "Sistem ne moze da zapamti pozajmicu");
                    frmIznajmljivanje.getLblError().setText("<html>"+ex.getMessage()+"</html>");
                    frmIznajmljivanje.getLblError().setForeground(Color.red);
                }
            }

//            private void validate(int rowClan, int rowKnjiga, int rowPrimerak) throws Exception {
//                String errorMessage = "";
//                if(rowClan == -1) errorMessage+="Morate izabrati clana <br>";
//                if(rowKnjiga == -1) errorMessage+="Morate izabrati knjigu <br>";
//                if(rowPrimerak == -1) errorMessage+="Morate izabrati primerak <br>";
//                if(!errorMessage.isEmpty()) throw new Exception(errorMessage);
//            }
        
        
        });        
    }

    public FrmAddPozajmica getFrmIznajmljivanje() {
        return frmIznajmljivanje;
    }
    
    private void populateClanovi() {
        try {
            clanovi = Controller.getInstance().getAllClanBiblioteke();
            frmIznajmljivanje.getCbClanovi().setModel(new DefaultComboBoxModel(clanovi.toArray()));
        } catch (IOException ex) {
            Logger.getLogger(FrmAddPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAddPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateKnjige() {
        try {
            knjige = Controller.getInstance().getAllKnjiga();
            TabelaKnjige tk = new TabelaKnjige(knjige);
            frmIznajmljivanje.getTabelaKnjige().setModel(tk);
        } catch (IOException ex) {
            Logger.getLogger(FrmAddPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAddPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }



    


    @Override
    public void update() {
        populatePozajmica();
    }

    private void populatePozajmica() {
        TabelaPrimerak tp = new TabelaPrimerak(primerciZaIznajmiti);
        frmIznajmljivanje.getTabelaPozajmica().setModel(tp);
    }

}
