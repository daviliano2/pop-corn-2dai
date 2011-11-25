/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import java.util.Date;
import popcorn.persistence.Comentario;

/**
 *
 * @author miguel
 */
public interface ComentarioService {
    
    void create(final Comentario comentario);
    
    void create(String contenido, Key idPelicula, Date fecha);

    Collection<Comentario> getAllComentarios(Key idPelicula);

    Collection<Comentario> getPaginaComentarios(int startPosition, int maxResult);

    int countAllComentarios();
}
