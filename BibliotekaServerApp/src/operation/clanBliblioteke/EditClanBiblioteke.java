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
public class EditClanBiblioteke extends GenericOperation {
    
    ClanBiblioteke cb;

    public EditClanBiblioteke() throws Exception {
        super();
    }
    
    @Override
    protected void executeOperation(Object object) throws Exception {
        cb = (ClanBiblioteke) databaseBroker.update((ClanBiblioteke) object);
        
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof ClanBiblioteke)) {
            throw new Exception("Nevalidni podaci o clanu biblioteke.");
        }         
    }

    public ClanBiblioteke getClanBiblioteke() {
        return cb;
    }
    
}
