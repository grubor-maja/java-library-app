/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.knjiga;

import domain.ClanBiblioteke;
import domain.Knjiga;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class AddKnjiga extends GenericOperation {

    Knjiga k;

    public Knjiga getK() {
        return k;
    }
    
    public AddKnjiga() throws Exception {
        super();
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        k =(Knjiga) databaseBroker.insert((Knjiga) object);
        
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Knjiga)) {
            throw new Exception("Nevalidni podaci o knjizi.");
        } 
    }
    
}
