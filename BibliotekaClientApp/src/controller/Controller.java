/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Administrator;
import domain.Autor;
import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Pozajmica;
import domain.Primerak;
import domain.Recenzija;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Maja
 */
public class Controller {
    private static Controller instance;
    Sender sender;
    Socket socket;
    Receiver receiver;
    
    private Controller() throws IOException {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    public static Controller getInstance() throws IOException {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    public Administrator login(Administrator admin) throws Exception {
        Request request = new Request(admin, Operation.LOGIN);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Administrator) response.getResult();
        } else {
            throw response.getException();
        }        
        
    }

    public List<Knjiga> getAllKnjiga() throws Exception {
        List<Knjiga> knjige = new ArrayList<>();
        Request request = new Request(null, Operation.GET_ALL_KNJIGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        knjige =  (List<Knjiga>) response.getResult();
        return knjige;
    }

    public List<ClanBiblioteke> getAllClanBiblioteke() throws Exception {
        List<ClanBiblioteke> clanovi = new ArrayList<>();
        Request request = new Request(clanovi, Operation.GET_ALL_CLAN_BIBLIOTEKE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        clanovi =  (List<ClanBiblioteke>) response.getResult();
        return clanovi;    
    }

    public ClanBiblioteke editClanBiblioteke(ClanBiblioteke noviClan) throws Exception {
        Request request = new Request(noviClan, Operation.EDIT_CLAN_BIBLIOTEKE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        noviClan =   (ClanBiblioteke) response.getResult();
        System.out.println("Novi clan: "+noviClan);
        return noviClan;         
    }

    public ClanBiblioteke addClanBiblioteke(ClanBiblioteke noviClan) throws Exception {
        Request request = new Request(noviClan, Operation.ADD_CLAN_BIBLIOTEKE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        noviClan =   (ClanBiblioteke) response.getResult();
        System.out.println("Novi clan: "+noviClan);
        return noviClan;
    }

    public List<Knjiga> getAllKnjigaSearched(HashMap<String, String> kriterijum) throws Exception {
        Request request = new Request(kriterijum, Operation.GET_ALL_KNJIGA_SEARCHED);
        sender.send(request);
        Response response = (Response) receiver.receive();
        List<Knjiga> knjige =   (List<Knjiga>) response.getResult();       
        return knjige;
    }

    public List<Primerak> getAllPrimerakSearched(Knjiga k) throws Exception {
        Request request = new Request(k, Operation.GET_ALL_PRIMERAK_SEARCHED);
        sender.send(request);
        Response response = (Response) receiver.receive();
        List<Primerak> primerci =   (List<Primerak>) response.getResult();       
        return primerci;    
    }

    public List<Primerak> getAllPrimerak() throws Exception {
        Request request = new Request(null, Operation.GET_ALL_PRIMERAK);
        sender.send(request);
        Response response = (Response) receiver.receive();
        List<Primerak> primerci =   (List<Primerak>) response.getResult();       
        return primerci;   
    }



    public Primerak addPrimerak(Primerak p) throws Exception {
        Request request = new Request(p, Operation.ADD_PRIMERAK);
        sender.send(request);
        Response response = (Response) receiver.receive();
        Primerak primerak =   (Primerak) response.getResult();     
        return primerak;    
    }

    public Primerak editPrimerak(Primerak p) throws Exception {
        Request request = new Request(p, Operation.EDIT_PRIMERAK);
        sender.send(request);
        Response response = (Response) receiver.receive();
        p =   (Primerak) response.getResult();
        return p;     
    }



    public Knjiga addKnjiga(Knjiga k) throws Exception {
        Request request = new Request(k, Operation.ADD_KNJIGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        k = (Knjiga) response.getResult();     
        return k;     
    }

    public Pozajmica addPozajmica(Pozajmica p) throws Exception {
        Request request = new Request(p, Operation.ADD_POZAJMICA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Pozajmica) response.getResult();
        } else {
            throw response.getException();
        }         
    }

    public List<Pozajmica> getAllPozajmica() throws Exception {
        List<Pozajmica> pozajmice = new ArrayList<>();
        Request request = new Request(pozajmice, Operation.GET_ALL_POZAJMICA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        pozajmice =   (List<Pozajmica>) response.getResult();       
        return pozajmice;  
    }

    public Pozajmica deletePozajmica(Pozajmica p) throws Exception {
        Request request = new Request(p, Operation.DELETE_POZAJMICA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        p  =   (Pozajmica) response.getResult();       
        return p;  
    }

    public void logout(Administrator admin) throws Exception {
        Request request = new Request(admin, Operation.LOGOUT);
        sender.send(request);
        
    }

    public List<Recenzija> getAllRecenzija() throws Exception, Exception {
        List<Recenzija> recenzije = new ArrayList<>();
        Request request = new Request(recenzije, Operation.GET_ALL_RECENZIJA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        recenzije =   (List<Recenzija>) response.getResult();       
        return recenzije;
    }

    public Recenzija addRecenzija(Recenzija r) throws Exception {
        Request request = new Request(r, Operation.ADD_RECENZIJA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        r = (Recenzija) response.getResult();     
        return r;          
    }

    public Knjiga editKnjiga(Knjiga k) throws Exception {
        Request request = new Request(k, Operation.EDIT_KNJIGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        k =   (Knjiga) response.getResult();
        return k;    
    }

    public List<Autor> getAllAutori() throws Exception {
        List<Autor> autori = new ArrayList<>();
        Request request = new Request(null, Operation.GET_ALL_AUTOR);
        sender.send(request);
        Response response = (Response) receiver.receive();
        autori =  (List<Autor>) response.getResult();
        return autori;
    }
    
}
