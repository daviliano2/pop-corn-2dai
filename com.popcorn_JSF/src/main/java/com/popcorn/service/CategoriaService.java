/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import com.popcorn.persistence.Categoria;

/**
 *
 * @author miguel
 */
public interface CategoriaService {
    
    void create(final Categoria categoria);

    Collection<Categoria> getAllCategorias();
    
    void createCategoria();
}
