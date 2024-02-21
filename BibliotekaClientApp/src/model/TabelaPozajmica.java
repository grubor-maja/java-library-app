/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.ClanBiblioteke;
import domain.Pozajmica;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TabelaPozajmica extends AbstractTableModel {

    List<Pozajmica> pozajmice;
    
    public TabelaPozajmica(List<Pozajmica> pozajmice) {
        this.pozajmice = pozajmice;
    }
    @Override
    public int getRowCount() {
        return pozajmice.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    public Pozajmica getPozajmica(int row) {
        return pozajmice.get(row);
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pozajmica p = pozajmice.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return p.getPozajmicaID();
            case 1:
                return p.getDatumPozajmice();
            case 2:
                return p.getDatumVracanja();
            case 3:
                return p.getAdmin().getAdministratorID(); 
            case 4:
                return p.getClan().getClanBibliotekeID();

            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Pozajmica ID";
            case 1:
                return "Datum pozajmice";
            case 2:
                return "Datum vracanja";
            case 3:
                return "Admin ID";  
            case 4:
                return "Clan ID";

            default:
                return "NA";
        }        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
     public void setLista(ArrayList<Pozajmica> pozajmice) {
        this.pozajmice = pozajmice;
        this.fireTableDataChanged();
    }   
    
        
}
