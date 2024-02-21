/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Primerak;
import domain.Recenzija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TabelaRecenzije extends AbstractTableModel {

    List<Recenzija> recenzije;
    
    public TabelaRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }
    @Override
    public int getRowCount() {
        return recenzije.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recenzija r = recenzije.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return r.getKnjiga().getNaslov();
            case 1:
                return r.getClanBiblioteke().getIme()+" "+r.getClanBiblioteke().getPrezime();
            case 2:
                return r.getTekstRecenzije();
            case 3:
                return r.getOcena();

            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Naslov";
            case 1:
                return "Clan biblioteke";
            case 2:
                return "Tekst";
            case 3:
                return "Ocena";
            default:
                return "NA";
        }        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }    
}
