/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import popcorn.persistence.Pelicula;
/**
 *
 * @author david
 */
public interface PeliculaService {
    
    void create(final Pelicula pelicula);
    
    Pelicula getPelicula(Key idPelicula);
    
    Collection<Pelicula> getAllPeliculas();
    
    Collection<Pelicula> getPeliculasOrdenadas();

    int countAllPeliculas();
    
    void borrarPeli(Key idPelicula) ;
    
    void editarPeli(Key idPelicula, final Pelicula pelicula);
}