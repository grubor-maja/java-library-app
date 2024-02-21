/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.primerak;

import domain.Knjiga;
import domain.Primerak;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllPrimerakSearched extends GenericOperation {
    
    private List<Primerak> primerci;
    private Knjiga k;
    
    public GetAllPrimerakSearched() throws Exception {
    super();
}



    
    @Override
    protected void executeOperation(Object object) throws Exception {
        k = (Knjiga) object;
        primerci = databaseBroker.getAllPrimerakSearched(k);
    }

    public List<Primerak> getPrimerak() {
        return primerci;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
   
    }
    
}
