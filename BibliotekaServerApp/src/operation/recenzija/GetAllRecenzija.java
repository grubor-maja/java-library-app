/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.recenzija;

import domain.Primerak;
import domain.Recenzija;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllRecenzija extends GenericOperation {

    private List<Recenzija> recenzije;
    
    public GetAllRecenzija() throws Exception {
    super();
}

    @Override
    protected void executeOperation(Object object) throws Exception {
        recenzije = databaseBroker.getAll(new Recenzija());
    }

    public List<Recenzija> getRecenzija() {
        return recenzije;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
   
    }    
}
