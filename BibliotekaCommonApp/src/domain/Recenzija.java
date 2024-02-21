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
public class Recenzija extends GenericEntity {
    
    private Knjiga knjiga;
    private ClanBiblioteke clanBiblioteke;
    private String tekstRecenzije;
    private int ocena;

    public Recenzija() {
    }

    public Recenzija(Knjiga knjiga, ClanBiblioteke clanBiblioteke, String tekstRecenzije, int ocena) {
        this.knjiga = knjiga;
        this.clanBiblioteke = clanBiblioteke;
        this.tekstRecenzije = tekstRecenzije;
        this.ocena = ocena;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public ClanBiblioteke getClanBiblioteke() {
        return clanBiblioteke;
    }

    public void setClanBiblioteke(ClanBiblioteke clanBiblioteke) {
        this.clanBiblioteke = clanBiblioteke;
    }

    public String getTekstRecenzije() {
        return tekstRecenzije;
    }

    public void setTekstRecenzije(String tekstRecenzije) {
        this.tekstRecenzije = tekstRecenzije;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String getTableName() {
        return "recenzija";
    }

    @Override
    public String getColumnsForInsert() {
        return "ISBN,ClanBibliotekeID,TekstRecenzije,Ocena";
    }

    @Override
    public String getParamsForInsert() {
        return "?,?,?,?";
    }
    public String getPrimaryKey() {
        return "";
    }
    @Override
    public String getColumnsForUpdate() {
        return "ISBN=?,ClanBibliotekeID=?,TekstRecenzije=?,Ocena=?";
    }    
    @Override
    public long getPrimaryKeyValue(){return this.getClanBiblioteke().getClanBibliotekeID();}      
   
    @Override
    public int getParamsForUpdate(){return 4;}
    
    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {   
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        Recenzija r = new Recenzija();
        try {
            
            Knjiga k = new Knjiga();
            k.setISBN(rs.getLong("ISBN"));
            k.setNaslov(rs.getString("Naslov"));
            // Postavite ostale atribute knjige
            r.setKnjiga(k);
            ClanBiblioteke cb = new ClanBiblioteke();
            cb.setClanBibliotekeID(rs.getInt("ClanBibliotekeID"));
            cb.setIme(rs.getString("Ime"));
            cb.setPrezime(rs.getString("Prezime"));
            r.setClanBiblioteke(cb);
            r.setTekstRecenzije(rs.getString("TekstRecenzije"));
            r.setOcena(rs.getInt("Ocena"));
        } catch (SQLException ex) {
            Logger.getLogger(Recenzija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
 }
    @Override
    public String getParamsForGet() { return "";}
        
     @Override
    public String getJoinClause() {
        return " JOIN knjiga k ON recenzija.ISBN = k.ISBN JOIN clan_biblioteke cb ON recenzija.ClanBibliotekeID = cb.ClanBibliotekeID";
    }   

    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
    }

    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
    }
    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Recenzija recenzija = (Recenzija) domainObject;
        statement.setLong(1, recenzija.getKnjiga().getISBN());
        statement.setInt(2, (int) recenzija.getClanBiblioteke().getClanBibliotekeID()); 
        statement.setString(3, recenzija.getTekstRecenzije()); 
        statement.setInt(4, recenzija.getOcena()); 
    }
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Recenzija recenzija = (Recenzija) domainObject;
        statement.setLong(1, recenzija.getKnjiga().getISBN());
        statement.setLong(2, recenzija.getClanBiblioteke().getClanBibliotekeID()); 
        statement.setString(3, recenzija.getTekstRecenzije()); 
        statement.setInt(4, recenzija.getOcena()); 
    }  


    @Override
    public String toString() {
        return "Recenzija{" + "knjiga=" + knjiga + ", clanBiblioteke=" + clanBiblioteke + ", tekstRecenzije=" + tekstRecenzije + ", ocena=" + ocena + '}';
    }
    
}
