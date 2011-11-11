/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import java.util.Collection;
import popcorn.persistence.Comentario;

/**
 *
 * @author miguel
 */
public interface ComentarioService {
    
    void create(final Comentario comentario);

    Collection<Comentario> getAllComentarios();

    Collection<Comentario> getPaginaComentarios(int startPosition, int maxResult);

    int countAllComentarios();
}
