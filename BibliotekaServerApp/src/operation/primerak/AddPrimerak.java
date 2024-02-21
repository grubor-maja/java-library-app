/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.primerak;

import domain.ClanBiblioteke;
import domain.Primerak;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class AddPrimerak extends GenericOperation {
     Primerak p;
     List<Primerak> primerci;
    
    public AddPrimerak() throws Exception {
        super();
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        p=(Primerak) databaseBroker.insert((Primerak) object);
        
        
    }

    public Primerak getPrimerak() {
        
        return p;
    }
    

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Primerak)) {
            throw new Exception("Nevalidni podaci o clanu biblioteke.");
        }  
    }  
      
}

