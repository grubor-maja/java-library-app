/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.primerak;

import controller.Controller;
import coordinator.Coordinator;
import domain.Autor;
import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Primerak;
import form.FrmKnjiga;
import form.FrmMain;
import form.FrmPrimerak;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.TabelaKnjige;
import model.TabelaPrimerak;
import observer.Observer;

/**
 *
 * @author Maja
 */
public class PrimerciController {

    private final FrmPrimerak frmPrimerak;
    List<Knjiga> knjige;
    List<Primerak> primerci;
    List<Autor> autori;
    Knjiga k;
    

    public PrimerciController(FrmPrimerak frmPrimerak) {
        this.frmPrimerak = frmPrimerak;
        populateTableKnjige();
        populateTablePrimerci();
        populateComboBox();
        addActionListener();
        addTableListener();
    }
    public void openForm() {
        frmPrimerak.pack();
        frmPrimerak.setVisible(true);
    }     
    
    private void addActionListener() {
        
        frmPrimerak.getBtnPronadjiKnjigu().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pronadjiKnjigu();
            }

            private void pronadjiKnjigu() {
                HashMap<String, String> kriterijum = new HashMap<>();
                if (frmPrimerak.getCbAutori().getSelectedItem()!=null) {
                    Autor autor = (Autor) frmPrimerak.getCbAutori().getSelectedItem();
                    String ime = String.valueOf(autor.getIme());
                    kriterijum.put("Ime", ime);
                }
                if (!frmPrimerak.getTxtNaslov().getText().isEmpty()) {
                    kriterijum.put("Naslov", frmPrimerak.getTxtNaslov().getText());
                }
                if (!frmPrimerak.getTxtISBN().getText().isEmpty()) {
                    kriterijum.put("ISBN", frmPrimerak.getTxtISBN().getText());
                }
                if (!frmPrimerak.getTxtGodinaIzdanja().getText().isEmpty()) {
                    kriterijum.put("GodinaIzdanja", frmPrimerak.getTxtGodinaIzdanja().getText());
                }
                if(!frmPrimerak.getTxtZanr().getText().isEmpty()) {
                    kriterijum.put("Zanr", frmPrimerak.getTxtZanr().getText());
                }
                try {
                    knjige = Controller.getInstance().getAllKnjigaSearched(kriterijum);
                        
                    populateTableKnjige2();
                    if(!knjige.isEmpty())
                        JOptionPane.showMessageDialog(frmPrimerak, "Sistem je nasao knjigu po zadatoj vrednosti.");
                    else
                        JOptionPane.showMessageDialog(frmPrimerak, "Sistem ne moze da nadje knjigu po zadatoj vrednosti.");
                } catch (IOException ex) {
                    System.out.println("Greska u pronalazenu date knjige. "+ex.getLocalizedMessage());
                } catch (Exception ex) {
                    System.out.println("Greska u pronalazenu date knjige. "+ex.getLocalizedMessage());
                }            
            }

            private void populateTableKnjige2() {
               TabelaKnjige tk = new TabelaKnjige(knjige);
               frmPrimerak.getTableKnjige().setModel(tk);
             
            }


        
            
        
        });
        
   
        frmPrimerak.getBtnDodaj().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = frmPrimerak.getTableKnjige().getSelectedRow();
                if(selectedRow == -1) {
                    JOptionPane.showMessageDialog(frmPrimerak, "Morate izabrati knjigu da biste mogli da dodate primerak.");                
                } else {
                    
                    Knjiga k = knjige.get(selectedRow);
                    Coordinator.getInstance().openAddPrimerak(getFrmPrimerak(),k);
                }                 
                
            }
        
        });
       
    }

    public FrmPrimerak getFrmPrimerak() {
        return frmPrimerak;
    }
    

    public void populateTablePrimerci() {
         try {
             primerci = Controller.getInstance().getAllPrimerak();
             TabelaPrimerak tp = new TabelaPrimerak(primerci);
            frmPrimerak.getTablePrimerci().setModel(tp);
        } catch (IOException ex) {
             System.out.println("Greska u popunjavanju tabele primeraka. "+ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("Greska u popunjavanju tabele primeraka. "+ex.getLocalizedMessage());
        }
    }

    private void populateTableKnjige() {
         try {
             knjige = Controller.getInstance().getAllKnjiga();
             TabelaKnjige tk = new TabelaKnjige(knjige);
            frmPrimerak.getTableKnjige().setModel(tk);
        } catch (IOException ex) {
             System.out.println("Greska u popunjavanju tabele knjiga. "+ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("Greska u popunjavanju tabele knjiga. "+ex.getLocalizedMessage());
        }
    }

    void populateTablePrimerci2() {
             TabelaPrimerak tp = new TabelaPrimerak(primerci);
             frmPrimerak.getTablePrimerci().setModel(tp);
    }
    private void populateComboBox() {
        try {
            autori = Controller.getInstance().getAllAutori();
            frmPrimerak.getCbAutori().setModel(new DefaultComboBoxModel(autori.toArray()));
            frmPrimerak.getCbAutori().setSelectedItem(null);
        } catch (IOException ex) {
            Logger.getLogger(PrimerciController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PrimerciController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

   private void addTableListener() {
        frmPrimerak.getTableKnjige().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = frmPrimerak.getTableKnjige().getSelectedRow();
                    if (selectedRow != -1) {
                        Knjiga k = knjige.get(selectedRow);

                        try {
                            primerci = Controller.getInstance().getAllPrimerakSearched(k);
                            populateTablePrimerci2();
                        } catch (IOException ex) {
                            System.out.println("Greska u pronalazenu primeraka date knjige. " + ex.getLocalizedMessage());
                        } catch (Exception ex) {
                            System.out.println("Greska u pronalazenu primeraka date knjige. " + ex.getLocalizedMessage());
                        }
                    }
                }
            }
        });
    }


}
