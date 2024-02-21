/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.knjiga;

import domain.Knjiga;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Maja
 */
public class GetAllKnjigaSearched extends GenericOperation {

    private HashMap<String, String> kriterijum;
    private ArrayList<Knjiga> knjige;
    
    
    public GetAllKnjigaSearched() throws Exception {
    super();
}

    public void setKriterijum(HashMap<String, String> kriterijum) {
        this.kriterijum = kriterijum;
    }

    public void setKnjige(ArrayList<Knjiga> knjige) {
        this.knjige = knjige;
    }

    public HashMap<String, String> getKriterijum() {
        return kriterijum;
    }

    
    @Override
    protected void executeOperation(Object object) throws Exception {
        setKriterijum((HashMap<String, String>) object);
        knjige = databaseBroker.getAllSearched(kriterijum, new Knjiga());
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
    }
     
}
