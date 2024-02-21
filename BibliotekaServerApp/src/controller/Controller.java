/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.GetConfig;
import domain.Administrator;
import domain.Autor;
import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Pozajmica;
import domain.Primerak;
import domain.Recenzija;
import exception.AlreadyLoggedInException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.*;
import operation.clanBliblioteke.AddClanBiblioteke;
import operation.knjiga.AddKnjiga;
import operation.pozajmica.AddPozajmica;
import operation.primerak.AddPrimerak;
import operation.recenzija.AddRecenzija;
import operation.knjiga.DeleteKnjiga;
import operation.pozajmica.DeletePozajmica;

import operation.clanBliblioteke.EditClanBiblioteke;
import operation.knjiga.EditKnjiga;
import operation.primerak.EditPrimerak;
import operation.GenericOperation;
import operation.autor.GetAllAutori;
import operation.clanBliblioteke.GetAllClanBiblioteke;
import operation.knjiga.GetAllKnjiga;
import operation.knjiga.GetAllKnjigaSearched;
import operation.pozajmica.GetAllPozajmica;
import operation.primerak.GetAllPrimerak;
import operation.primerak.GetAllPrimerakSearched;
import operation.recenzija.GetAllRecenzija;
import operation.autor.GetAutor;
import operation.Login;
import server.Server;
import view.controller.ViewController;
import view.form.FrmMain;

/**
 *
 * @author Maja
 */
public class Controller {
 
    private static Controller controller;
    private Server server;
    private ViewController viewController;
    
    private Controller() {
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }    
    
    public void startServer() throws Exception {
        server = new Server();
        server.startServer();
    }

    public void stopServer() throws Exception {
        server.stopServer();
        viewController.resetActiveUsers();
    }

    public void openMainForm() {
        viewController = new ViewController(new FrmMain());
        viewController.openForm();
    }
    
    private void addOnlineUser(Administrator admin) throws Exception {
        viewController.addOnlineUsers(admin);
    }    

    public Administrator login(Administrator admin) throws Exception {
            if(server.onlineUsers.contains(admin)) {
                throw new AlreadyLoggedInException("Ovaj admin je vec ulogovan.");
            }
            Login operation = new Login();
            operation.execute(admin);            
            server.onlineUsers.add(admin);
            return operation.getAdmin();
    }
  
 

    public Object getPort() throws Exception{
        return GetConfig.getServerPort();
    }    

    public List<ClanBiblioteke> getAllClanBiblioteke() throws Exception {
        GetAllClanBiblioteke operation = new GetAllClanBiblioteke();
        operation.execute(new ClanBiblioteke());
        return operation.getAllClanBiblioteke();
               
    }
    public List<Knjiga> getAllKnjiga() throws Exception {
        GetAllKnjiga operation = new GetAllKnjiga();
        operation.execute(new Knjiga());
        return operation.getKnjige();

    }
    public ClanBiblioteke editClanBiblioteke(ClanBiblioteke clan)  {
        try {
            EditClanBiblioteke operation = new EditClanBiblioteke();
            operation.execute(clan);
            return operation.getClanBiblioteke();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ClanBiblioteke addClanBiblioteke(ClanBiblioteke clan) {
        try {
            AddClanBiblioteke operation = new AddClanBiblioteke();
            operation.execute(clan);
            return operation.getCb();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Knjiga> getAllKnjigeSerached(HashMap<String, String> kriterijum) throws Exception {
        GetAllKnjigaSearched operation = new GetAllKnjigaSearched();
        operation.execute(kriterijum);
        return operation.getKnjige();
    }

    public List<Primerak> getAllPrimerak() throws Exception {
        GetAllPrimerak operation = new GetAllPrimerak();
        operation.execute(null);
        return operation.getPrimerak();
    }

    public List<Primerak> getAllPrimerakSearched(Knjiga k) throws Exception {
        GetAllPrimerakSearched operation = new GetAllPrimerakSearched();
        operation.execute(k);
        return operation.getPrimerak();
    }

//    public List<Primerak> deletePrimerak(Primerak p) throws Exception {
//        DeletePrimerak operation = new DeletePrimerak();
//        operation.execute(p);
//        return operation.getPrimerci();
//    }

    public Primerak addPrimerak(Primerak p) throws Exception {
        AddPrimerak operation = new AddPrimerak();
        operation.execute(p);
        return operation.getPrimerak();
    }

    public Primerak editPrimerak(Primerak p) throws Exception {
        EditPrimerak operation = new EditPrimerak();
        operation.execute(p);
        return operation.getP();
    }

//    public Knjiga deleteKnjiga(Knjiga k) throws Exception {
//        DeleteKnjiga operation = new DeleteKnjiga();
//        operation.execute(k);
//        return operation.getK();
//    }

    public Knjiga addKnjiga(Knjiga k) throws Exception {
        AddKnjiga operation = new AddKnjiga();
        operation.execute(k);
        return operation.getK();
    }

    public Autor getAutor(Knjiga k) throws Exception {
        GetAutor operation = new GetAutor();
        operation.execute(k);
        return operation.getA();
    }

//    public Autor addAutor(Autor a) throws Exception {
//        AddAutor operation = new AddAutor();
//        operation.execute(a);
//        return operation.getA();
//    }

    public Pozajmica addPozajmica(Pozajmica p) throws Exception {
        AddPozajmica operation = new AddPozajmica();
        operation.execute(p);
        return operation.getPozajmica();
    }

    public List<Pozajmica> getAllPozajmica() throws Exception {
        GetAllPozajmica operation = new GetAllPozajmica();
        operation.execute(null);
        return operation.getPozajmice();
    }

    public Pozajmica deletePozajmica(Pozajmica p) throws Exception {
        DeletePozajmica operation = new DeletePozajmica();
        operation.execute(p);
        return operation.getP();
    }

    public void logout(Administrator admin) {
        server.onlineUsers.remove(admin);
        System.out.println("Trenutno aktivni: "+server.onlineUsers);
    }

    public List<Recenzija> getAllRecenzija() throws Exception {
        GetAllRecenzija operation = new GetAllRecenzija();
        operation.execute(new Recenzija());
        return operation.getRecenzija();
    }


    public Recenzija addRecenzija(Recenzija r) throws Exception {
        AddRecenzija operation = new AddRecenzija();
        operation.execute(r);
        return operation.getRecenzija();
    }

    public List<Autor> getAllAutor() throws Exception {
        GetAllAutori operation = new GetAllAutori();
        operation.execute(null);
        return operation.getAutori();
    }

    public Knjiga editKnjiga(Knjiga k) throws Exception {
        EditKnjiga operation = new EditKnjiga();
        operation.execute(k);
        return operation.getKnjiga();
    }
}
