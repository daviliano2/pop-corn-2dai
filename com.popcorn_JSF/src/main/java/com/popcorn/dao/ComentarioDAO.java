package com.popcorn.dao;

import com.google.appengine.api.datastore.Key;

import java.util.List;
import com.popcorn.persistence.Comentario;

public interface ComentarioDAO extends GenericPopDAO<Comentario, Key> {
	
    List<Key> getComentariosTema(String tema);
            
}
