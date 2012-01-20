/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import com.popcorn.persistence.Pelicula;
/**
 *
 * @author david
 */
public interface PeliculaService {
    
    void create(final Pelicula pelicula);
    
    Pelicula getPelicula(Key idPelicula);
    
    Collection<Pelicula> getAllPeliculas();

    int countAllPeliculas();
}