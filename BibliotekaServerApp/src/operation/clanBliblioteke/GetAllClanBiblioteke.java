/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clanBliblioteke;

import domain.ClanBiblioteke;
import domain.GenericEntity;
import domain.Knjiga;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllClanBiblioteke extends GenericOperation{
    
    private List<ClanBiblioteke> clanovi;
    
    public GetAllClanBiblioteke() throws Exception {
    super();
}

    @Override
    protected void executeOperation(Object object) throws Exception {
        clanovi = databaseBroker.getAll(new ClanBiblioteke());
    }

    public List<ClanBiblioteke> getAllClanBiblioteke() {
        return clanovi;
    }
    
    
    

    @Override
    protected void validate(Object object) throws Exception {
 
    }    
}
