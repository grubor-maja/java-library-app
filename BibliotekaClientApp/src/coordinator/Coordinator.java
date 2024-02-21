/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinator;

import controller.clanBiblioteke.AddClanBibliotekeController;
import controller.knjiga.AddKnjigaController;
import controller.primerak.AddPrimerakController;
import controller.recenzija.AddRecenzija;
import controller.clanBiblioteke.ClanBibliotekeController;
import controller.clanBiblioteke.EditClanBibliotekeController;
import controller.knjiga.EditKnjigaController;
import controller.pozajmica.AddPozajmicaController;
import controller.knjiga.KnjigaController;
import controller.main.LoginController;
import controller.main.MainController;
import controller.primerak.PrimerciController;
import controller.recenzija.RecenzijeController;
import controller.recenzija.RecenzijeDetailsController;
import controller.pozajmica.DeletePozajmicaController;
import controller.pozajmica.IzborPrimerkaController;
import form.FrmLogin;
import form.FrmPrimerak;
import form.FrmClanBiblioteke;
import form.FrmKnjiga;
import form.FrmAddRecenzija;
import domain.Administrator;
import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Primerak;
import domain.Recenzija;
import form.FrmAddPozajmica;
import form.FrmDetailsRecenzija;
import form.FrmIzborPrimerka;
import form.FrmMain;
import form.FrmRecenzije;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import observer.Observer;
import observer.Subject;

/**
 *
 * @author Maja
 */
public class Coordinator implements Subject{
 
private static Coordinator instance;
//private MainController mainContoller;
public ClanBibliotekeController cbController;
public PrimerciController primerakController;
public KnjigaController knjigeController;
public RecenzijeController recenzijeController;
public AddPozajmicaController iznajmljivanjeController;
public List<Observer> observers = new ArrayList<>();

public void addObserver(Observer observer) {
    observers.add(observer);
}

public void notifyObservers() {
    for(Observer o : observers) {
        o.update();
    }
}

public final Map<String, Object> params;

    private Coordinator() {
        
        params = new HashMap<>();
    }
    
    public static Coordinator getInstance() {
        if(instance == null) 
            instance = new Coordinator();
        return instance;
    }
    
    public void openLoginForm() {
        LoginController loginContoller = new LoginController(new FrmLogin());
        loginContoller.openForm();
    }  
    public void openMainForm() {
        MainController mainController = new MainController(new FrmMain());
        mainController.openForm();
    }    
    
    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }    

    public void openClanoviBibliotekeForm() {
        cbController = new ClanBibliotekeController(new form.FrmClanBiblioteke());
        cbController.openForm();
    }

    public void openPrimerciForm() {
        primerakController = new PrimerciController(new form.FrmPrimerak());
        primerakController.openForm();
    }

    public void openKnjigaForm(JFrame parent) {
        knjigeController = new KnjigaController(new form.FrmKnjiga());
        knjigeController.openForm();
    }

    public void openIznajmljivanjeForm(JFrame parent) {
        iznajmljivanjeController = new AddPozajmicaController(new form.FrmAddPozajmica(parent,true));
        iznajmljivanjeController.openForm();
    }

    public void openVratiForm(JFrame parent) {
        DeletePozajmicaController vracanjeController = new DeletePozajmicaController(new form.FrmDeletePozajmica(parent,true));
        vracanjeController.openForm();
    }

    public void openRecenzijeForm() {
        recenzijeController = new RecenzijeController(new form.FrmRecenzije());
        recenzijeController.openForm();
    }

    public void openAddClanBiblioteke(JFrame fcb) {
        AddClanBibliotekeController addCBController = new AddClanBibliotekeController(new form.FrmAddClanBiblioteke(fcb, true),cbController);
        addCBController.openForm();
    }

    public void openEditClanBiblioteke(FrmClanBiblioteke fcb, ClanBiblioteke clan) {
        EditClanBibliotekeController editCBController = new EditClanBibliotekeController(new form.FrmEditClanBiblioteke(fcb, true),cbController,clan);
        editCBController.openForm();
    }

    public void openAddPrimerak(FrmPrimerak frmPrimerak, Knjiga k) {
        AddPrimerakController addPrimerakController = new AddPrimerakController(new form.FrmAddPrimerak(frmPrimerak, true), primerakController,k);
        addPrimerakController.openForm();
    }

    public void openEditKnjiga(FrmKnjiga frmKnjiga, Knjiga k) {
        EditKnjigaController editKnjigaController = new EditKnjigaController(new form.FrmEditKnjiga(frmKnjiga, true), knjigeController, k);
        editKnjigaController.openForm();
    }

    public void openAddKnjiga(JFrame frmKnjiga) {
        AddKnjigaController addKnjigaController = new AddKnjigaController(new form.FrmAddKnjiga(frmKnjiga, true), knjigeController);
        addKnjigaController.openForm();
    }

    public void openAddRecenzija(JFrame parent) {
        AddRecenzija addRecenzija = new AddRecenzija(new FrmAddRecenzija(parent, true), recenzijeController);
        addRecenzija.openForm();
    }

    public void openRecenzijeDetails(FrmRecenzije frmRecenzije, Recenzija re) {
        RecenzijeDetailsController recenzijaDetails = new RecenzijeDetailsController(new FrmDetailsRecenzija(frmRecenzije, true),re);
        recenzijaDetails.openForm();
    }
    public void openIzborPrimerka(FrmAddPozajmica parent,Knjiga k) {
        IzborPrimerkaController izborPrimerka = new IzborPrimerkaController(new FrmIzborPrimerka(parent, true), iznajmljivanjeController, k);
        izborPrimerka.openForm();
    }    


}