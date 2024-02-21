/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class Administrator extends GenericEntity{
    
    private long administratorID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String loznika;

    public Administrator() {
    }

    public Administrator(long administratorID, String ime, String prezime, String korisnickoIme, String loznika) {
        this.administratorID = administratorID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.loznika = loznika;
    }
    public Administrator(String korisnickoIme, String loznika) {
        this.korisnickoIme = korisnickoIme;
        this.loznika = loznika;    
        
    }    
    

    public long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(long administratorID) {
        this.administratorID = administratorID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLoznika() {
        return loznika;
    }

    public void setLoznika(String loznika) {
        this.loznika = loznika;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.loznika, other.loznika);
    }
    
    

    @Override
    public String getTableName() {
        return "administrator";
    }

    @Override
    public String getColumnsForInsert() {
        return "Ime, Prezime, KorisnickoIme, Lozinka";
    }
    @Override
    public String getColumnsForUpdate() {
        return "Ime=?, Prezime=?, KorisnickoIme=?, Lozinka=?";
    }
    @Override
    public String getParamsForInsert() {
        return "(?,?,?,?)";
    }
    public String getPrimaryKey() {
        return "AdministratorID";
    }
   
    @Override
    public long getPrimaryKeyValue(){return this.getAdministratorID();}
    
    @Override
    public int getParamsForUpdate(){return 4;}
    
    @Override
    public String getParamsForGet() {
        return "KorisnickoIme=? AND Lozinka=?";
    }
    
    @Override
    public String getJoinClause() { return ""; }
    


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        Administrator administrator = new Administrator();
        try {           
            administrator.setAdministratorID(rs.getLong("AdministratorID"));
            administrator.setIme(rs.getString("Ime"));
            administrator.setPrezime(rs.getString("Prezime"));
            administrator.setKorisnickoIme(rs.getString("KorisnickoIme"));
            administrator.setLoznika(rs.getString("Lozinka"));
        } catch (SQLException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return administrator;
    }

    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {
        setAdministratorID(primaryKey);
        System.out.println("Admin je dobio ID: " + primaryKey);    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Administrator administrator = (Administrator) domainObject;
        statement.setString(1, administrator.getIme());
        statement.setString(2, administrator.getPrezime());
        statement.setString(3, administrator.getKorisnickoIme());
        statement.setString(4, administrator.getLoznika());
       
    }
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Administrator administrator = (Administrator) domainObject;
        statement.setString(1, administrator.getIme());
        statement.setString(2, administrator.getPrezime());
        statement.setString(3, administrator.getKorisnickoIme());
        statement.setString(4, administrator.getLoznika());
    }    
    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {

    }

    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
            Administrator administrator = (Administrator) domainObject;
            statement.setString(1, administrator.getKorisnickoIme());
            statement.setString(2, administrator.getLoznika());
    }





}
