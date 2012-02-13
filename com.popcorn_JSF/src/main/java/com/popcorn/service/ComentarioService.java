/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import java.util.Date;
import com.popcorn.persistence.Comentario;

/**
 *
 * @author miguel
 */
public interface ComentarioService {
    
    void create(final Comentario comentario);
    
    void create(final Comentario comentario, Key idPelicula);

    Collection<Comentario> getAllComentarios(Key idPelicula);
    
    Collection<Comentario> getAll() ;

    Collection<Comentario> getPaginaComentarios(int startPosition, int maxResult);

    public void delComentario(Comentario comen);    
    
    int countAllComentarios();
}
