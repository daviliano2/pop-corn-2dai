/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import java.util.Collection;
import popcorn.persistence.*;
/**
 *
 * @author david
 */
public interface RolService {
   
    void createRol();
    Rol getRol(String rol);
    Collection<Rol> getAllRoles();
}
