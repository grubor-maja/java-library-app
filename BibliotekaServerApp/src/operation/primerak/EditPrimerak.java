/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.primerak;

import domain.ClanBiblioteke;
import domain.Primerak;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class EditPrimerak extends GenericOperation {

    Primerak p;
    
    public EditPrimerak() throws Exception {
        super();
    }

    public Primerak getP() {
        return p;
    }
    
    @Override
    protected void executeOperation(Object object) throws Exception {
        Primerak p2 = (Primerak) object;
        p = (Primerak) databaseBroker.update(p2);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Primerak)) {
            throw new Exception("Nevalidni podaci o primerku.");
        }      
    }
    
}
