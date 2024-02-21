/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.knjiga;

import domain.ClanBiblioteke;
import domain.Knjiga;
import domain.Primerak;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class DeleteKnjiga extends GenericOperation {
    Knjiga k;
    List<Knjiga> knjige;

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public Knjiga getK() {
        return k;
    }
    
    
    
    public DeleteKnjiga() throws Exception {
        super();
        
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        knjige = databaseBroker.delete((Knjiga) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Knjiga)) {
            throw new Exception("Nevalidni podaci o knjizi.");
        } 
    }    
}
