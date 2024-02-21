/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Knjiga;
import domain.Primerak;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TabelaPrimerak extends AbstractTableModel {

    List<Primerak> primerci;
    
    public TabelaPrimerak(List<Primerak> primerci) {
        this.primerci = primerci;
    }
    @Override
    public int getRowCount() {
        return primerci.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Primerak p = primerci.get(rowIndex);
        switch(columnIndex) {

            case 0:
                return p.getKnjiga().getISBN();
            case 1:
                return p.getIzdavac();

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
                return "Izdavac";
            default:
                return "NA";
        }        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
    
    
    
}
