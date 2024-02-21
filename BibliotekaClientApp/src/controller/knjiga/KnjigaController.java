/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.knjiga;

import controller.Controller;
import coordinator.Coordinator;
import domain.Knjiga;
import form.FrmKnjiga;
import form.FrmAddPozajmica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.TabelaKnjige;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class KnjigaController implements Observer{

    private final FrmKnjiga frmKnjiga;
    List<Knjiga> knjige;

    public KnjigaController(FrmKnjiga frmKnjiga) {
        this.frmKnjiga = frmKnjiga;
        knjige = new ArrayList<>();
        Coordinator.getInstance().addObserver(this);
        addActionListener();
        
    }
    public void openForm() {
        frmKnjiga.pack();
        frmKnjiga.setVisible(true);
    }   
   
    private void addActionListener() {
        populateTableKnjige();

        frmKnjiga.getBtnIzmeni().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = frmKnjiga.getTableKnjige().getSelectedRow();
                if(selectedRow == -1) {
                    JOptionPane.showMessageDialog(frmKnjiga, "Morate izabrati knjigu da biste mogli da dodate primerak.");                
                } else {
                    
                    Knjiga k = knjige.get(selectedRow);
                    Coordinator.getInstance().openEditKnjiga(getFrmKnjiga(),k);
                }        
            }
        
        });        
    }

    public FrmKnjiga getFrmKnjiga() {
        return frmKnjiga;
    }

    public void populateTableKnjige() {
         try {
             knjige = Controller.getInstance().getAllKnjiga();
             TabelaKnjige tk = new TabelaKnjige(knjige);
            frmKnjiga.getTableKnjige().setModel(tk);
        } catch (IOException ex) {
             System.out.println("Greska u popunjavanju tabele knjiga. "+ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("Greska u popunjavanju tabele knjiga. "+ex.getLocalizedMessage());
        }  
    }

    public void populateTableKnjige2() {
        TabelaKnjige tk = new TabelaKnjige(knjige);
        frmKnjiga.getTableKnjige().setModel(tk);

    }    

    @Override
    public void update() {
        populateTableKnjige();
    }
}
