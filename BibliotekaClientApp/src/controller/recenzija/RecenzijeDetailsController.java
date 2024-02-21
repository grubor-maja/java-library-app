package controller.recenzija;

import domain.Recenzija;
import form.FrmAddRecenzija;
import form.FrmDetailsRecenzija;
import form.FrmRecenzije;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maja
 */
public class RecenzijeDetailsController {

private final FrmDetailsRecenzija frmDetailsRecenzija;
private Recenzija recenzija;
    public RecenzijeDetailsController(FrmDetailsRecenzija frmDetailsRecenzija,Recenzija recenzija) {
        this.frmDetailsRecenzija = frmDetailsRecenzija;
        this.recenzija = recenzija;
        addActionListener();
    }

    public void openForm() {
        frmDetailsRecenzija.pack();
        frmDetailsRecenzija.setVisible(true);
    }  

    public FrmDetailsRecenzija getFrmRecenzije() {
        return frmDetailsRecenzija;
    }

    private void addActionListener() {
        frmDetailsRecenzija.getTxtClan().setText(recenzija.getClanBiblioteke().getIme()+" "+recenzija.getClanBiblioteke().getPrezime());
        frmDetailsRecenzija.getTxtKnjiga().setText(recenzija.getKnjiga().getNaslov());
        frmDetailsRecenzija.getTxtOcena().setText(recenzija.getOcena()+"");
        frmDetailsRecenzija.getTxtTekst().setText(recenzija.getTekstRecenzije());
    }
}
