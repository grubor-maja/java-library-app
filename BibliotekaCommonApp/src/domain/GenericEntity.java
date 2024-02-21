/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maja
 */
public abstract class GenericEntity implements Serializable{

    public abstract String getTableName();
    public abstract String getColumnsForInsert();
    public abstract String getColumnsForUpdate();
    public abstract String getParamsForInsert();    
    public abstract int getParamsForUpdate();    
    public abstract String getParamsForGet();
    public abstract String getPrimaryKey();
    public abstract long getPrimaryKeyValue();
    public abstract GenericEntity getEntityFromResultSet(ResultSet rs);
    public abstract String getJoinClause();
    
    public abstract void setParamsForInsert(PreparedStatement statement, GenericEntity domainObject) throws SQLException;
    public abstract void setParamsForUpdate(PreparedStatement statement, GenericEntity domainObject) throws SQLException;
    public abstract void setParamsForGet(PreparedStatement statement, GenericEntity domainObject) throws SQLException;
    public abstract void setParamsForDelete(PreparedStatement statement, GenericEntity domainObject) throws SQLException;
    public abstract void setAutoIncrementPrimaryKey(long primaryKey);
    
    public boolean containsAutoIncrementPK() {
        return true;
    }
    

}
