/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.autor;

import domain.Autor;
import domain.ClanBiblioteke;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class AddAutor extends GenericOperation{

    Autor a;

    public Autor getA() {
        return a;
    }
    
    public AddAutor() throws Exception {
        super();
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        a = (Autor) databaseBroker.insert((Autor) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Autor)) {
            throw new Exception("Nevalidni podaci o autoru.");
        }    
    }
    
    
}
