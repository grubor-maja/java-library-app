/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.pozajmica;

import controller.Controller;
import controller.clanBiblioteke.ClanBibliotekeController;
import coordinator.Coordinator;
import domain.Knjiga;
import domain.Primerak;
import form.FrmAddClanBiblioteke;
import form.FrmAddPozajmica;
import form.FrmIzborPrimerka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TabelaPrimerak;

/**
 *
 * @author Maja
 */
public class IzborPrimerkaController {
    
    private final FrmIzborPrimerka frmIzborPrimerka;
    private Knjiga knjiga;
    private final AddPozajmicaController pozajmicaController;
    private List<Primerak> primerci;

    public IzborPrimerkaController(FrmIzborPrimerka frmIzborPrimerka,AddPozajmicaController pozajmicaController,Knjiga knjiga) {
        this.frmIzborPrimerka = frmIzborPrimerka;
        this.pozajmicaController = pozajmicaController;
        this.knjiga = knjiga;
        populateTable();
        addActionListener();
    }

    public void openForm() {
        frmIzborPrimerka.pack();
        frmIzborPrimerka.setVisible(true);
        
    }      

    private void addActionListener() {
        frmIzborPrimerka.getBtnIzaberi().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = frmIzborPrimerka.getTabelaPrimerci().getSelectedRow();
                if(selectedRow == -1) {
                    JOptionPane.showMessageDialog(frmIzborPrimerka, "Morate izbrati primerak");
                }
                else {
                    Primerak primerak = primerci.get(selectedRow);
                    pozajmicaController.primerciZaIznajmiti.add(primerak);
                    Coordinator.getInstance().notifyObservers();
                    frmIzborPrimerka.dispose();
                }
            }
        
        
        });
    }

    private void populateTable() {
        try {
            primerci = Controller.getInstance().getAllPrimerak();
            primerci = primerciOveKnjige();
            primerci = filtrirajPrimerke();
            TabelaPrimerak tp = new TabelaPrimerak(primerci);
            frmIzborPrimerka.getTabelaPrimerci().setModel(tp);
        } catch (IOException ex) {
            Logger.getLogger(IzborPrimerkaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IzborPrimerkaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private List<Primerak> filtrirajPrimerke() {
        List<Primerak> slobodniPrimerci = new ArrayList<>();
        for(Primerak p : primerci) {
            if(p.getPozajmica().getPozajmicaID() == 0) {
                slobodniPrimerci.add(p);
            }
        }
        return slobodniPrimerci;
    }

    private List<Primerak> primerciOveKnjige() {
        List<Primerak> primerciKnjige = new ArrayList<>();
        for(Primerak p :primerci) {
            if(p.getKnjiga().getISBN() == knjiga.getISBN()) {
                primerciKnjige.add(p);
            }
                
        }
        return primerciKnjige;
    }
}
