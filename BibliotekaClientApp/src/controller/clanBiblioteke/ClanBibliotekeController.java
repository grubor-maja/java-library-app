/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.clanBiblioteke;

import controller.Controller;
import coordinator.Coordinator;
import domain.ClanBiblioteke;
import form.FrmClanBiblioteke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TabelaClanBiblioteke;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class ClanBibliotekeController implements Observer{
    private final FrmClanBiblioteke frmClanBiblioteke;
    List<ClanBiblioteke> clanovi;

    public ClanBibliotekeController(FrmClanBiblioteke frmClanBiblioteke) {
        this.frmClanBiblioteke = frmClanBiblioteke;
        clanovi = new ArrayList<>();
        Coordinator.getInstance().addObserver(this);
    }

    public FrmClanBiblioteke getFrmClanBiblioteke() {
        return frmClanBiblioteke;
    }
    
    public void openForm() {
        frmClanBiblioteke.pack();
        frmClanBiblioteke.setVisible(true);
        addActionListener();
    }
    private void addActionListener() {
        populateTable();

        frmClanBiblioteke.btnIzmenibtnAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = frmClanBiblioteke.getjTblClanoviBiblioteke().getSelectedRow();
                if(selectedRow == -1) {
                    JOptionPane.showMessageDialog(frmClanBiblioteke, "Morate izabrati clana biblioteke da biste mogli da ga izmenite.");                
                } else {
                    
                    ClanBiblioteke clan = clanovi.get(selectedRow);
                    Coordinator.getInstance().openEditClanBiblioteke(getFrmClanBiblioteke(),clan);
                }
                
            }
        
        });          
    }

    public void populateTable() {
        try {
            clanovi = Controller.getInstance().getAllClanBiblioteke();
            TabelaClanBiblioteke tcb = new TabelaClanBiblioteke(clanovi);
            frmClanBiblioteke.getjTblClanoviBiblioteke().setModel(tcb);
        } catch (IOException ex) {
            System.out.println("Greska u popunjavanju tabele clanova biblioteke." +ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("Greska u popunjavanju tabele clanova biblioteke." +ex.getLocalizedMessage());
        }    }

    @Override
    public void update() {
        populateTable();
    }
    
    
}
