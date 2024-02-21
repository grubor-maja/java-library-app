/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clanBliblioteke;

import domain.ClanBiblioteke;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class AddClanBiblioteke extends GenericOperation {

    ClanBiblioteke cb;
    
    public AddClanBiblioteke() throws Exception {
        super();
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        
        cb = (ClanBiblioteke) databaseBroker.insert((ClanBiblioteke) object);
        
    }

    public ClanBiblioteke getCb() {
        return cb;
    }
    

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof ClanBiblioteke)) {
            throw new Exception("Nevalidni podaci o clanu biblioteke.");
        }  
    }  
   
}
    

