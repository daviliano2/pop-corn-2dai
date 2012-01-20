package com.popcorn.dao;

import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import com.popcorn.persistence.Categoria;

@Repository
public class CategoriaDAOImpl extends GenericPopDAOImpl<Categoria, Key> implements CategoriaDAO {
	
}