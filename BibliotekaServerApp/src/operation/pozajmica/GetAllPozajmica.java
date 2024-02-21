/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.pozajmica;

import domain.Knjiga;
import domain.Pozajmica;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllPozajmica extends GenericOperation {
 
        private List<Pozajmica> pozajmice;
    
    public GetAllPozajmica() throws Exception {
    super();
}

    @Override
    protected void executeOperation(Object object) throws Exception {
        pozajmice = databaseBroker.getAll(new Pozajmica());
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {

    }
    
}
