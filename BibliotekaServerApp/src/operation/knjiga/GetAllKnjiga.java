/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.knjiga;

import domain.Knjiga;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllKnjiga extends GenericOperation {
    
    private List<Knjiga> knjige;
    
    public GetAllKnjiga() throws Exception {
    super();
}

    @Override
    protected void executeOperation(Object object) throws Exception {
        knjige = databaseBroker.getAll(new Knjiga());
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
 
    }
    
}
