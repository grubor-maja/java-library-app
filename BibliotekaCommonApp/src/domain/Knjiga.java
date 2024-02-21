/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class Knjiga extends GenericEntity{
    
    private long ISBN;
    private String naslov;
    private Autor autor;
    private int godinaIzdanja;
    private String zanr;

    public Knjiga() {
    }

    public Knjiga(long ISBN, String naslov, Autor autor, int godinaIzdanja, String zanr) {
        this.ISBN = ISBN;
        this.naslov = naslov;
        this.autor = autor;
        this.godinaIzdanja = godinaIzdanja;
        this.zanr = zanr;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }
    

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public String getTableName() {
        return "knjiga";
    }

    @Override
    public String getColumnsForInsert() {
        return "ISBN, Naslov, AutorID, GodinaIzdanja, Zanr ";
    }
    @Override
    public String getColumnsForUpdate() {
        return "Naslov=?, AutorID=?, GodinaIzdanja=?, Zanr=? ";
    }
    @Override
    public String getParamsForInsert() {
        return "?,?,?,?,?";
    }
    public String getPrimaryKey() {
        return "ISBN";
    }
    @Override
    public long getPrimaryKeyValue(){return this.getISBN();}     
    
    @Override
    public int getParamsForUpdate(){return 4;}
    
    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {

    }

    @Override
    public String toString() {
        return naslov;
    }
    @Override
    public String getParamsForGet() { return ""; }    

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        Knjiga knjiga = new Knjiga();
        try {          
            Autor autor = new Autor();
            knjiga.setISBN(rs.getLong("ISBN"));
            knjiga.setNaslov(rs.getString("Naslov"));
            autor.setAutorID(rs.getInt("a.AutorID"));
            autor.setIme(rs.getString("a.Ime"));
            autor.setPrezime(rs.getString("a.Prezime"));
            knjiga.setAutor(autor);
            knjiga.setGodinaIzdanja(rs.getInt("GodinaIzdanja"));
            knjiga.setZanr(rs.getString("Zanr"));
        } catch (SQLException ex) {
            Logger.getLogger(Knjiga.class.getName()).log(Level.SEVERE, null, ex);
        }
        return knjiga;
 }
    @Override
    public String getJoinClause() {
        return " JOIN autor a ON knjiga.AutorID = a.AutorID";
    }

    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Knjiga k = (Knjiga) domainObject;
        statement.setLong(1, k.getISBN());
    }
    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Knjiga knjiga = (Knjiga) domainObject;
        statement.setLong(1, knjiga.getISBN());
        statement.setString(2, knjiga.getNaslov());
        statement.setLong(3, knjiga.getAutor().getAutorID());
        statement.setLong(4, knjiga.getGodinaIzdanja());
        statement.setString(5, knjiga.getZanr());       
    }   
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Knjiga knjiga = (Knjiga) domainObject;
        statement.setString(1, knjiga.getNaslov());
        statement.setLong(2,knjiga.getAutor().getAutorID());
        statement.setLong(3, knjiga.getGodinaIzdanja());
        statement.setString(4, knjiga.getZanr());   
    } 
    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {

    }




}


    

