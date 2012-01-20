/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import com.popcorn.persistence.*;
/**
 *
 * @author david
 */
public interface RolService {
   
    void createRol();
    Rol getRol(Key idRol);
    Collection<Rol> getAllRoles();
}
