/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.autor;

import domain.Autor;
import domain.Primerak;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllAutori extends GenericOperation {
    
   private List<Autor> autori;
    
    public GetAllAutori() throws Exception {
    super();
}

    @Override
    protected void executeOperation(Object object) throws Exception {
        autori = databaseBroker.getAll(new Autor());
    }

    public List<Autor> getAutori() {
        return autori;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
   
    }    
}
