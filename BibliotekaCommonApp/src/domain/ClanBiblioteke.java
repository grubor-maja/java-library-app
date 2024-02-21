/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class ClanBiblioteke extends GenericEntity {
    
    private long clanBibliotekeID;
    private String ime;
    private String prezime;
    private String email;

    public ClanBiblioteke() {
    }

    public ClanBiblioteke(long clanBibliotekeID, String ime, String prezime,String email) {
        this.clanBibliotekeID = clanBibliotekeID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public ClanBiblioteke(String ime, String prezime,String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }
    public long getClanBibliotekeID() {
        return clanBibliotekeID;
    }

    public void setClanBibliotekeID(long clanBibliotekeID) {
        this.clanBibliotekeID = clanBibliotekeID;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTableName() {
        return "clan_biblioteke";
    }

    @Override
    public String getColumnsForInsert() {
        return "Ime,Prezime,Email";
    }
    @Override
    public String getColumnsForUpdate() {
        return "Ime=?,Prezime=?,Email=?";
    }
    public String getPrimaryKey() {
        return "ClanBibliotekeID";
    }
    @Override
    public String getParamsForInsert() {
        return "?,?,?";
    }
    @Override
    public long getPrimaryKeyValue(){return this.getClanBibliotekeID();} 
    
    @Override
    public int getParamsForUpdate(){return 3;}    

    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {
        setClanBibliotekeID(primaryKey);
        System.out.println("Clan biblioteke je dobio ID: " + primaryKey);
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public ClanBiblioteke getEntityFromResultSet(ResultSet rs) {
        ClanBiblioteke clan = new ClanBiblioteke();
        try {           
            clan.setClanBibliotekeID(rs.getInt("ClanBibliotekeID"));
            clan.setIme(rs.getString("Ime"));
            clan.setPrezime(rs.getString("Prezime"));
            clan.setEmail(rs.getString("Email"));
        } catch (SQLException ex) {
            Logger.getLogger(ClanBiblioteke.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clan;
    }
    @Override
    public String getParamsForGet() { return "";} 

        
    @Override
    public String getJoinClause() { return "";}    

    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
    }

    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
    }
    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {       
        ClanBiblioteke clanBiblioteke = (ClanBiblioteke) domainObject;
        statement.setString(1, clanBiblioteke.getIme());
        statement.setString(2, clanBiblioteke.getPrezime());
        statement.setString(3, clanBiblioteke.getEmail());
    }
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        ClanBiblioteke clanBiblioteke = (ClanBiblioteke) domainObject;
        statement.setString(1, clanBiblioteke.getIme());
        statement.setString(2, clanBiblioteke.getPrezime());
        statement.setString(3, clanBiblioteke.getEmail());
    } 

    
}
