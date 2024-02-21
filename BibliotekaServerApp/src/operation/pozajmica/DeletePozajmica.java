/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.pozajmica;

import domain.Pozajmica;
import domain.Primerak;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class DeletePozajmica extends GenericOperation {

    Pozajmica p;

    public Pozajmica getP() {
        return p;
    }


    
    
    
    
    public DeletePozajmica() throws Exception {
        super();
        
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        Pozajmica p2 = (Pozajmica) object;
        p =p2;
        databaseBroker.delete(p2);
    }

    @Override
    protected void validate(Object object) throws Exception {

    }    
}
