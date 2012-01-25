package com.popcorn.dao;

import com.google.appengine.api.datastore.Key;

import java.util.List;
import com.popcorn.persistence.Tema;

public interface TemaDAO extends GenericPopDAO<Tema, Key> {
	
    List<Tema> getTemas(String username);
            
}
