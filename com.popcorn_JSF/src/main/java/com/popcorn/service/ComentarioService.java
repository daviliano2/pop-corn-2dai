/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import com.popcorn.persistence.Comentario;
import com.popcorn.persistence.Tema;

/**
 *
 * @author miguel
 */
public interface ComentarioService {
    
    void create(final Comentario comentario);
    
    void create(final Comentario comentario, Key idTema);
    
    void addComentario(Comentario comentario, Tema tema);

    Comentario getComentario(Key idComentario);
    
    Collection<Key> getAllComentarios(Key idTema);
    
    Collection<Comentario> getAll() ;
    
    Collection<Comentario> getAllColum(Tema tema);
    
    Collection<Key> getTemaComentarios(Tema tema);

    Collection<Comentario> getPaginaComentarios(int startPosition, int maxResult);

    public void removeComentario(Comentario comen);    
    
    int countAllComentarios();
}
