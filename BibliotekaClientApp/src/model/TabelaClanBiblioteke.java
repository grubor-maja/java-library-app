/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.ClanBiblioteke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TabelaClanBiblioteke extends AbstractTableModel {

    List<ClanBiblioteke> clanovi;
    
    public TabelaClanBiblioteke(List<ClanBiblioteke> clanovi) {
        this.clanovi = clanovi;
    }
    @Override
    public int getRowCount() {
        return clanovi.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    public ClanBiblioteke getClan(int row) {
        return clanovi.get(row);
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClanBiblioteke cb = clanovi.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return cb.getIme();
            case 1:
                return cb.getPrezime();
            case 2:
                return cb.getEmail(); 
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Ime";
            case 1:
                return "Prezime";
            case 2:
                return "Email";  
            default:
                return "NA";
        }        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
     public void setLista(ArrayList<ClanBiblioteke> clanovi) {
        this.clanovi = clanovi;
        this.fireTableDataChanged();
    }   
    
    
    
}
