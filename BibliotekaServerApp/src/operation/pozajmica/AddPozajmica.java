/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.pozajmica;

import controller.Controller;
import domain.ClanBiblioteke;
import domain.Pozajmica;
import domain.Primerak;
import exception.PozajmicaExistsException;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class AddPozajmica extends GenericOperation{

   Pozajmica pozajmica;
   List<Pozajmica> pozajmice;

    
    public AddPozajmica() throws Exception {
        super();

        
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        pozajmica = (Pozajmica) object;
        pozajmica = (Pozajmica) databaseBroker.insert(pozajmica);

        
    }

    public Pozajmica getPozajmica() {
        return pozajmica;
    }
    

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Pozajmica)) {
            throw new Exception("Nevalidni podaci o pozajmici.");
        }
        Pozajmica poz = (Pozajmica) object;
        pozajmice = Controller.getInstance().getAllPozajmica();
        for(Pozajmica p : pozajmice) {
            if(p.getClan().getClanBibliotekeID()==poz.getClan().getClanBibliotekeID())
                throw new Exception("Ovaj clan nije razduzio prethodnu pozajmicu.");
        }
    }      
}
