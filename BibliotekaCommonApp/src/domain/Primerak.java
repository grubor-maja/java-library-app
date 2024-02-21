/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class Primerak extends GenericEntity {
    private long primerakId;
    private Pozajmica pozajmica;
    private Knjiga knjiga;
    private String izdavac;

    public Primerak() {
    }

    public Primerak(long primerakId, Pozajmica pozajmica, Knjiga knjiga, String izdavac) {
        this.primerakId = primerakId;
        this.pozajmica = pozajmica;
        this.knjiga = knjiga;
        this.izdavac = izdavac;
    }


    

    public long getPrimerakId() {
        return primerakId;
    }

    public void setPrimerakId(long primerakId) {
        this.primerakId = primerakId;
    }

    public Pozajmica getPozajmica() {
        return pozajmica;
    }

    public void setPozajmica(Pozajmica pozajmica) {
        this.pozajmica = pozajmica;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }
    

    @Override
    public String getTableName() {
        return "primerak";
    }

    @Override
    public String getColumnsForInsert() {
        return "ISBN,Izdavac";
    }
    @Override
    public String getColumnsForUpdate() {
        return "Izdavac=?,PozajmicaID=?";
    }
    @Override
    public String getParamsForInsert() {
        return "?,?";
    }
    public String getPrimaryKey() {
        return "PrimerakID";
    }
    @Override
    public long getPrimaryKeyValue(){return this.getPrimerakId();}            
  
    
    @Override
    public int getParamsForUpdate(){return 2;}
    
    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {
        setPrimerakId(primaryKey);
        System.out.println("Primerak je dobio ID: " + primaryKey);
    }

    @Override
    public String toString() {
        return "Primerak{" + "primerakId=" + primerakId + ", pozajmica=" + pozajmica + ", knjiga=" + knjiga + ", izdavac=" + izdavac + '}';
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        Primerak primerak = new Primerak();
        try {
            
            primerak.setPrimerakId(rs.getInt("PrimerakID"));
            Pozajmica p = new Pozajmica();
            p.setPozajmicaID(rs.getInt("PozajmicaID"));           
            primerak.setPozajmica(p);
            Knjiga k = new Knjiga();
            k.setISBN(rs.getLong("ISBN"));              
            primerak.setKnjiga(k);
            primerak.setIzdavac(rs.getString("Izdavac"));
        } catch (SQLException ex) {
            Logger.getLogger(Primerak.class.getName()).log(Level.SEVERE, null, ex);
        }
        return primerak;
    }
    
    @Override
    public String getParamsForGet()  { return "";} 
    
    @Override
    public String getJoinClause() { return "";} 
    
    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Primerak p = (Primerak) domainObject;
        statement.setLong(1, p.getPrimerakId());
    }
    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Primerak primerak = (Primerak) domainObject;
        statement.setLong(1,primerak.getKnjiga().getISBN());
        statement.setString(2, primerak.getIzdavac());
    }
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Primerak primerak = (Primerak) domainObject;
        statement.setString(1, primerak.getIzdavac());
        
        if (primerak.getPozajmica() != null) {
            statement.setInt(2, (int) primerak.getPozajmica().getPozajmicaID());
        } else {
            statement.setNull(2, java.sql.Types.INTEGER);
        }

        
    }   
    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {

    }


    
}
