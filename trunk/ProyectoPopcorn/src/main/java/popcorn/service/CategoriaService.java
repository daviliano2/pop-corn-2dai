/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import java.util.Collection;
import popcorn.persistence.Categoria;

/**
 *
 * @author miguel
 */
public interface CategoriaService {
    
    void create(final Categoria categoria);

    Collection<Categoria> getAllCategorias();
    
    int getNumCategorias();
    
    void createCategoria();
}
