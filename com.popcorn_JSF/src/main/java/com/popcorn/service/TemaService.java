/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import java.util.Date;
import com.popcorn.persistence.Tema;

/**
 *
 * @author miguel
 */
public interface TemaService {
    
    void create(final Tema tema);
    
    /*void create(final Tema tema, Key idPelicula);

    Collection<Tema> getAllTemas(Key idPelicula);*/
    
    Collection<Tema> getAll() ;

    Collection<Tema> getPaginaTemas(int startPosition, int maxResult);

    int countAllTemas();
    
    void removeTema(Tema tema);
}
