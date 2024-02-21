/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import static communication.Operation.*;
import communication.*;
import controller.Controller;
import domain.*;
import exception.AlreadyLoggedInException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class ClientThread extends Thread {
    Socket socket;
    Sender sender;
    Receiver receiver;
    private boolean signal = false;

    public ClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    public void stopThread() {
        signal = true;
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Greska u zaustavljanju niti za obradu zahteva klijenata.");
        }
        System.out.println("Nit za obradu zahteva klijenata je zaustavljena.");
    }

    @Override
    public void run() {
        while(true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                HashMap<String, String> kriterijum;               
                Primerak p;
                Knjiga k;
                List<Primerak> primerci;
                List<Pozajmica> pozajmice;
                List<Knjiga> knjige;
                List<Recenzija> recenzije;
                Autor a;                
                Administrator admin;
                Pozajmica pozajmica;              
                Recenzija r;
                ClanBiblioteke clan;
                try {
                    switch(request.getOperation()) {
                        case LOGIN:
                            admin = (Administrator) request.getArgument();                    
                            response.setResult(Controller.getInstance().login(admin));
                            break;                        
                        case LOGOUT:
                             admin = (Administrator) request.getArgument();
                             Controller.getInstance().logout(admin);
                             break;
                        case ADD_KNJIGA:
                            k = (Knjiga) request.getArgument();
                            k =Controller.getInstance().addKnjiga(k);
                            response.setResult(k);
                            System.out.println("Client thread vraca: "+k.getISBN());
                            break;
                        case ADD_CLAN_BIBLIOTEKE:
                            clan = (ClanBiblioteke) request.getArgument();
                            clan = Controller.getInstance().addClanBiblioteke(clan);
                            response.setResult(clan);
                            break;
                        case ADD_RECENZIJA:
                            r = (Recenzija) request.getArgument();
                            r = Controller.getInstance().addRecenzija(r);
                            response.setResult(r);
                            break;
                        case ADD_POZAJMICA:
                            pozajmica = (Pozajmica) request.getArgument();
                            pozajmica = Controller.getInstance().addPozajmica(pozajmica);
                            response.setResult(pozajmica);
                            break;
                        case GET_ALL_KNJIGA:
                            knjige = Controller.getInstance().getAllKnjiga();
                            response.setResult(knjige);
                            break;
                        case GET_ALL_CLAN_BIBLIOTEKE:
                            List<ClanBiblioteke> clanovi = Controller.getInstance().getAllClanBiblioteke();
                            response.setResult(clanovi);
                            break;  
                        case GET_ALL_POZAJMICA:
                            pozajmice =  Controller.getInstance().getAllPozajmica();
                            response.setResult(pozajmice);
                            break;
                        case GET_ALL_AUTOR:
                            List<Autor> autori = Controller.getInstance().getAllAutor();
                            response.setResult(autori);
                            break;
                        case GET_ALL_KNJIGA_SEARCHED:
                            kriterijum = (HashMap<String, String>) request.getArgument();
                            System.out.println("Krit: "+kriterijum.toString());
                            knjige = Controller.getInstance().getAllKnjigeSerached(kriterijum);
                            response.setResult(knjige);
                            break;
                        case GET_ALL_PRIMERAK:
                            primerci = Controller.getInstance().getAllPrimerak();
                            response.setResult(primerci);
                            break;
                        case GET_ALL_PRIMERAK_SEARCHED:
                            k = (Knjiga) request.getArgument();
                            primerci = Controller.getInstance().getAllPrimerakSearched(k);
                            response.setResult(primerci);
                            break;
                        case GET_ALL_RECENZIJA:
                            recenzije = Controller.getInstance().getAllRecenzija();
                            response.setResult(recenzije);
                            break;
                        case EDIT_CLAN_BIBLIOTEKE:
                            clan = (ClanBiblioteke) request.getArgument();
                            clan = Controller.getInstance().editClanBiblioteke(clan);
                            response.setResult(clan);
                            break;
                        case DELETE_POZAJMICA:
                            pozajmica = (Pozajmica) request.getArgument();
                            pozajmica = Controller.getInstance().deletePozajmica(pozajmica);
                            response.setResult(pozajmica);
                            break;
                        case ADD_PRIMERAK:
                            p = (Primerak) request.getArgument();
                            p = Controller.getInstance().addPrimerak(p);
                            response.setResult(p);
                            break;
                        case EDIT_PRIMERAK:
                            p = (Primerak) request.getArgument();
                            p = Controller.getInstance().editPrimerak(p);
                            response.setResult(p);                        
                            break;
                        case EDIT_KNJIGA:
                            k = (Knjiga) request.getArgument();
                            k = Controller.getInstance().editKnjiga(k);
                            response.setResult(k);
                            break;                   
                    }                    
                } catch (Exception e) {
                    response.setException(e);
                    e.printStackTrace();
                }
              
                sender.send(response);
            } catch (Exception ex) {
                
                
            }
        }
    }
    
    
    
    
}
