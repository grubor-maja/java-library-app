/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.recenzija;

import domain.ClanBiblioteke;
import domain.Recenzija;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class AddRecenzija extends GenericOperation {
    Recenzija recenzija;

    public Recenzija getRecenzija() {
        return recenzija;
    }
    
    public AddRecenzija() throws Exception {
        super();
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        Recenzija recenzija2 = (Recenzija) object;
        recenzija = (Recenzija) databaseBroker.insert(recenzija2);
        System.out.println("VRACAM TI: ");
        System.out.println(recenzija);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Recenzija)) {
            throw new Exception("Nevalidni podaci o recenziji.");
        }      
    }    
}
