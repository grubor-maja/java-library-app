/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.repository;

import domain.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class DBBroker {

    private  Connection connection;

    public DBBroker() {
        this.connection = null;
    }


    public Connection getConnection() {
        return connection;
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
    
    public <T extends GenericEntity> ArrayList<T> getAllSearched(HashMap<String, String> criteria, T entity) throws Exception {
        String query = "SELECT * FROM " + entity.getTableName() + " " + entity.getJoinClause();
        List<T> resultList = new ArrayList<>();

        if (criteria != null && !criteria.isEmpty()) {
            query += " WHERE ";
            for (String key : criteria.keySet()) {
                query += key + " LIKE '%" + criteria.get(key) + "%' AND ";
            }
            query = query.substring(0, query.length() - 5); 
            System.out.println(query);
        }

        try {
            this.connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                T newEntity = (T) entity.getEntityFromResultSet(rs);
                resultList.add(newEntity);
                System.out.println(newEntity);
            }
            
            rs.close();
            statement.close();
            closeConnection();
        } catch (SQLException ex) {
            throw new Exception("Error while executing query for get all searched: " + ex.getMessage());
        }

        return (ArrayList<T>) resultList;
    }
    
    
    public GenericEntity getSpecific(GenericEntity entity) {
    String query = "SELECT * FROM "+entity.getTableName()+" WHERE "+entity.getParamsForGet();
    
    GenericEntity e =null;
    try {
        this.connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        entity.setParamsForGet(statement, entity);
        ResultSet rs = statement.executeQuery(); 
        if(rs.next()) {
            e = entity.getEntityFromResultSet(rs);            
        }
        rs.close();
        statement.close();
        closeConnection();        
    } catch (SQLException ex) {
    }   catch (Exception ex) {
            
        }
    
    return e;    
}
    public <T extends GenericEntity>List<T> getAll(T entity) {
    List<T> entities = new ArrayList<>();
    String query = "SELECT * FROM "+entity.getTableName()+entity.getJoinClause();
    try {
        this.connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery(); 
        while(rs.next()) {
            T newEntity = (T) entity.getEntityFromResultSet(rs);
            entities.add(newEntity);
            
        }
        rs.close();
        statement.close();
        closeConnection();        
    } catch (Exception e) {
    }
    
    return entities;
}
    
    public <T extends GenericEntity>List<T> delete(GenericEntity entity) {
    String query = "DELETE FROM "+entity.getTableName()+" WHERE "+entity.getPrimaryKey()+" =?";
    try {
        this.connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        entity.setParamsForDelete(statement, entity);
        int deletedRows = statement.executeUpdate();
        connection.commit();
        statement.close();
        closeConnection();

        if (deletedRows > 0) {
            System.out.println("Objekat uspesno obrisan.");
            return (List<T>) getAll(entity); 
        } else {
            System.out.println("Objekat nije pronadjen u bazi.");
            return null;
        }        
    } catch (Exception e) {
        System.out.println("Greska u Delete: "+e.getLocalizedMessage());
        return null;
    }
}
    public GenericEntity update(GenericEntity entity) {
        String tableName = entity.getTableName();
        String primaryKey = entity.getPrimaryKey();
        String query = "UPDATE "+tableName+" SET "+entity.getColumnsForUpdate()+ " WHERE "+primaryKey+" =?";
        try {
            this.connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            entity.setParamsForUpdate(statement, entity);
            statement.setLong(entity.getParamsForUpdate()+1, entity.getPrimaryKeyValue()); 
            statement.executeUpdate();
            connection.commit();

            statement.close();
            closeConnection();

        }catch(Exception ex){
            System.out.println("Greska u UPDATE: "+ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        return entity;
    }
    public GenericEntity insert(GenericEntity entity) throws SQLException {
        
        String query = "INSERT INTO " + entity.getTableName() + " (" + entity.getColumnsForInsert()+ ") VALUES (" + entity.getParamsForInsert() + ")";
        try {
            this.connection = DBConnection.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                entity.setParamsForInsert(statement, entity);
                
                int affectedRows = statement.executeUpdate();
                connection.commit();
                if (affectedRows == 0) {
                    throw new SQLException("Greska u insertovanju entiteta.");
                }
                
                try (var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setAutoIncrementPrimaryKey(generatedKeys.getInt(1));
                        System.out.println("Uspesno dodavanje automatsko generisanog PK.");
                    }
                }
            }
            
            closeConnection();
            
            
        }   catch (Exception ex) {
            
        }
        return entity;
    }
    public List<Primerak> getAllPrimerakSearched(Knjiga k) {
    List<Primerak> primerci = new ArrayList<>();
    String query = "SELECT PrimerakID, PozajmicaID, ISBN, Izdavac FROM primerak WHERE ISBN = ?";
    
    try {
        this.connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, k.getISBN());
        ResultSet rs = statement.executeQuery(); 
        
        while (rs.next()) {
            Primerak primerak = new Primerak();
            primerak.setPrimerakId(rs.getInt("PrimerakID"));
            Pozajmica p = new Pozajmica();
            p.setPozajmicaID(rs.getInt("PozajmicaID"));           
            primerak.setPozajmica(p);
            primerak.setKnjiga(k);
            primerak.setIzdavac(rs.getString("Izdavac"));
            
            primerci.add(primerak);
        }       
        rs.close();
        statement.close();
        closeConnection();
        
    } catch(Exception ex) {
    }
    
    return primerci;
} 
}
    





