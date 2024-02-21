/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class Pozajmica extends GenericEntity {
    private long pozajmicaID;
    private Date datumPozajmice;
    private Date datumVracanja;
    private Administrator admin;
    private ClanBiblioteke clan;

    

    public Pozajmica() {
    }

    public Pozajmica(long pozajmicaID, Date datumPozajmice, Date datumVracanja, Administrator admin, ClanBiblioteke clan) {
        this.pozajmicaID = pozajmicaID;
        this.datumPozajmice = datumPozajmice;
        this.datumVracanja = datumVracanja;
        this.admin = admin;
        this.clan = clan;

    }


    

    public long getPozajmicaID() {
        return pozajmicaID;
    }

    public void setPozajmicaID(long pozajmicaID) {
        this.pozajmicaID = pozajmicaID;
    }

    public Date getDatumPozajmice() {
        return datumPozajmice;
    }

    public void setDatumPozajmice(Date datumPozajmice) {
        this.datumPozajmice = datumPozajmice;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public ClanBiblioteke getClan() {
        return clan;
    }

    public void setClan(ClanBiblioteke clan) {
        this.clan = clan;
    }

    @Override
    public String getTableName() {
        return "pozajmica";
    }

    @Override
    public String getColumnsForInsert() {
        return "DatumPozajmice,DatumVracanja,AdministratorID,ClanBibliotekeID";
    }
    @Override
    public String getColumnsForUpdate() {
        return "DatumPozajmice=?,DatumVracanja=?,AdministratorID=?,ClanBibliotekeID=?";
    }
    @Override
    public String getParamsForInsert() {
        return "?,?,?,?";
    }
    public String getPrimaryKey() {
        return "PozajmicaID";
    }
    @Override
    public long getPrimaryKeyValue(){return this.getPozajmicaID();}         
    @Override
    public void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Pozajmica pozajmica = (Pozajmica) domainObject;
        java.sql.Date datumPozajmice = (java.sql.Date) pozajmica.getDatumPozajmice();
        Calendar cal = Calendar.getInstance();
        cal.setTime(pozajmica.getDatumPozajmice());
        cal.add(Calendar.DAY_OF_MONTH, 14);
        Date datumVracanja = cal.getTime();
        java.sql.Date sqlDatumVracanja = new java.sql.Date(datumVracanja.getTime());
        statement.setDate(1, datumPozajmice);
        statement.setDate(2, sqlDatumVracanja);
        statement.setLong(3, pozajmica.getAdmin().getAdministratorID());
        statement.setLong(4, pozajmica.getClan().getClanBibliotekeID());  
    }
    @Override
    public void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Pozajmica pozajmica = (Pozajmica) domainObject;
        java.sql.Date datumPozajmice = (java.sql.Date) pozajmica.getDatumPozajmice();
        java.sql.Date datumVracanja = (java.sql.Date) pozajmica.getDatumVracanja();
        statement.setDate(1, datumPozajmice);
        statement.setDate(2, datumVracanja);
        statement.setLong(3, pozajmica.getAdmin().getAdministratorID());
        statement.setLong(4, pozajmica.getClan().getClanBibliotekeID());  

        
    } 
    @Override
    public void setAutoIncrementPrimaryKey(long primaryKey) {
        setPozajmicaID(primaryKey);
        System.out.println("Pozajmica je dobila ID: " + primaryKey);
    }

    @Override
    public int getParamsForUpdate(){return 4;}

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
        final Pozajmica other = (Pozajmica) obj;
        if (this.pozajmicaID != other.pozajmicaID) {
            return false;
        }
        if (!Objects.equals(this.datumPozajmice, other.datumPozajmice)) {
            return false;
        }
        if (!Objects.equals(this.datumVracanja, other.datumVracanja)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        return Objects.equals(this.clan, other.clan);
    }

    

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        Pozajmica pozajmica = new Pozajmica();
        try {
            pozajmica.setPozajmicaID(rs.getInt("PozajmicaID"));
            pozajmica.setDatumPozajmice(rs.getDate("DatumPozajmice"));
            pozajmica.setDatumVracanja(rs.getDate("DatumVracanja"));
            Administrator a = new Administrator();
            a.setAdministratorID(rs.getInt("AdministratorID"));
            pozajmica.setAdmin(a);
            ClanBiblioteke cb = new ClanBiblioteke();
            cb.setClanBibliotekeID(rs.getInt("ClanBibliotekeID"));
            pozajmica.setClan(cb);

        } catch (SQLException ex) {
            Logger.getLogger(Pozajmica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pozajmica;
 }
    @Override
    public String getJoinClause() {
        return "";
    }    

    @Override
    public void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
        Pozajmica p = (Pozajmica) domainObject;
        statement.setInt(1, (int) p.getPozajmicaID()); 
    }

    @Override
    public void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException {
    }

    @Override
    public String getParamsForGet() {
        return "";
    }

    @Override
    public String toString() {
        return "Pozajmica{" + "pozajmicaID=" + pozajmicaID + ", datumPozajmice=" + datumPozajmice + ", datumVracanja=" + datumVracanja + ", admin=" + admin + ", clan=" + clan + '}';
    }
    
}
