/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Knjiga;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TabelaKnjige extends AbstractTableModel {

    List<Knjiga> knjige;
    
    public TabelaKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }
    @Override
    public int getRowCount() {
        return knjige.size();
        
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga k = knjige.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return k.getISBN();
            case 1:
                return k.getNaslov();
            case 2:
                return k.getAutor().getIme()+" "+k.getAutor().getPrezime();
            case 3:
                return k.getGodinaIzdanja();
            case 4:
                return k.getZanr();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "ISBN";
            case 1:
                return "Naslov";
            case 2:
                return "Autor";
            case 3:
                return "Godina izdanja";
            case 4:
                return "Zanr";
            default:
                return "NA";
        }        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
    
    
    
}
