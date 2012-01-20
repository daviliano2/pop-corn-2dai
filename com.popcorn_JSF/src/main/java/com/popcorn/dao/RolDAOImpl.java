/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.dao;

import com.google.appengine.api.datastore.Key;
import com.popcorn.persistence.Rol;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImpl extends GenericPopDAOImpl<Rol, Key> implements RolDAO {
   
}
