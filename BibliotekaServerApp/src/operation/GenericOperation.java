package operation;

import db.repository.DBBroker;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maja
 */
public abstract class GenericOperation  {

    protected DBBroker databaseBroker;

    public GenericOperation() throws Exception {
        databaseBroker = new DBBroker();
    }

    public void execute(Object object) throws Exception {
        try {
            validate(object);
            executeOperation(object);
            commit();
        } catch (Exception ex) {
            rollback();
            throw ex;
        }
    }

    private void commit() throws SQLException {
//        databaseBroker.getConnection().commit();
    }

    private void rollback() throws SQLException {
//        databaseBroker.getConnection().rollback();
    }

    protected abstract void executeOperation(Object object) throws Exception;

    protected abstract void validate(Object object) throws Exception;    
}
