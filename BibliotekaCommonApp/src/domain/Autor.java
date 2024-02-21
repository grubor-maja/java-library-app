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
public class Autor extends GenericEntity{
    
    private long autorID;
    private String ime;
    private String prezime;

    public Autor() {
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    public Autor(long autorID, String ime, String prezime) {
        this.autorID = autorID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public long getAutorID() {
        return autorID;
    }

    public void setAutorID(long autorID) {
        this.autorID = autorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String getTableName() {
        return "autor";
    }

    @Override
    public String getColumnsForInsert() {
        return "Ime,Prezime";
    }
    @Override
    public String getColumnsForUpdate() {
        return "Ime=?, Prezime=?";
    }
    @Override
    public String getParamsForInsert() {
        return "?,?";
    }
    public String getPrimaryKey() {
        return "AutorID";
    }
    @Override
    public long getPrimaryKeyValue(){return this.getAutorID();}    

    
    @Override
    public int getParamsForUpdate(){return 2;}


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        Autor autor = new Autor();
        try {
            autor.setAutorID(rs.getInt("AutorID"));
            autor.setIme(rs.getString("Ime"));
            autor.setPrezime(rs.getString("Prezime"));
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autor;
    }
    @Override
    public String getJoinClause()  { return "";} 
    
    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {

    }

    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Autor a = (Autor) domainObject;
        statement.setInt(1, (int)a.getAutorID());
    }
    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {
        setAutorID(primaryKey);   
    }    
    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Autor autor = (Autor) domainObject;
        statement.setString(1, autor.getIme());
        statement.setString(2, autor.getPrezime());    
    }
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Autor autor = (Autor) domainObject;
        statement.setString(1, autor.getIme());
        statement.setString(2, autor.getPrezime()); 
    }     
    @Override
    public String getParamsForGet() {
        return "AutorID=?";
    }
    
}
