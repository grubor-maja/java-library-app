/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domain.Administrator;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ClientThread;

/**
 *
 * @author Maja
 */
public class Server extends Thread {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private List<ClientThread> clientThreads = new ArrayList<>();
    private boolean signal = false;
    public List<Administrator> onlineUsers;

    public Server() {
        onlineUsers = new ArrayList<>();
    }
    
    
    
    
    public void startServer() throws Exception {   
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("app.config"));
            int port = Integer.valueOf(properties.getProperty("port"));            
            serverSocket = new ServerSocket(port);
            System.out.println("Cekam klijente...");
            start();
        } catch (IOException ex) {
            System.out.println("Greska u kreiranju server soketa.  "+ex.getLocalizedMessage());
        }        
    }
    public void stopServer() throws Exception {
        signal = true;
        for(ClientThread ct : clientThreads) {
            if(ct.isAlive() && ct!=null) {
                ct.stopThread();
                
            }
            
        }
        try {
            if(serverSocket!=null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Greska u zaustavljanju servera.");
        }
        System.out.println("Server je zaustavljen.");
    }

    @Override
    public void run() {
        try {
        while(!signal) {
            clientSocket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(clientSocket);
            clientThreads.add(clientThread);
            clientThread.start();
            System.out.println("Klijent je povezan na server.");
        }            
        } catch(SocketException ex) {
            System.out.println("Greska u prihvatanju klijentskog soketa.");
        } catch (IOException ex) {
            System.out.println("Greska u povezivanju sa klijentom.");
        }

    }
    
    
            
}
