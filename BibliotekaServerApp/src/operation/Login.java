/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import controller.Controller;
import domain.Administrator;
import domain.Knjiga;
import exception.UserDoesntExistException;
import java.util.List;

/**
 *
 * @author Maja
 */
public class Login extends GenericOperation {

    private Administrator admin;

    public Login() throws Exception {
        super();
    }
    @Override
    protected void executeOperation(Object object) throws Exception {
        admin = (Administrator) databaseBroker.getSpecific((Administrator) object);
        if (admin == null) {
            throw new UserDoesntExistException("Korisnik ne postoji.");
        }
    }

    public Administrator getAdmin() {
        return admin;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Administrator)) {
            throw new Exception("Object is not valid");
        }
    }
}
