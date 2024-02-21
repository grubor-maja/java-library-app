/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.autor;

import domain.Autor;
import domain.ClanBiblioteke;
import domain.Knjiga;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAutor extends GenericOperation {
    
    Autor a;
    
    public GetAutor() throws Exception {
        super();
    }

    public Autor getA() {
        return a;
    }

    public void setA(Autor a) {
        this.a = a;
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        Knjiga k = (Knjiga) object;
        a.setAutorID(k.getAutor().getAutorID());
        a= (Autor) databaseBroker.getSpecific(a);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Autor)) {
            throw new Exception("Nevalidni podaci o autoru.");
        }      
    }
}
