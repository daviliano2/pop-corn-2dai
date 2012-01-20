package com.popcorn.service;

import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import com.popcorn.dao.CategoriaDAO;
import com.popcorn.persistence.Categoria;


/**
 *
 * @author miguel
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaDAO categoriaDAO;
   
    @Autowired
    @Required
    public void setCategoriaDAO(final CategoriaDAO categoriaDao) {
        this.categoriaDAO = categoriaDao;
    }

    @Override
    public void create(final Categoria categoria) {
        categoriaDAO.insert(categoria);
    }

    @Override
    public Collection<Categoria> getAllCategorias() {
        return categoriaDAO.getAll(Categoria.class);
        
    }
    
    @PostConstruct
    @Override
    public void createCategoria() {
        if(categoriaDAO.countAll(Categoria.class) == 0) {
            categoriaDAO.removeAll(Categoria.class);
            Categoria cat1 = new Categoria();
            Categoria cat2 = new Categoria();
            Categoria cat3 = new Categoria();
            Categoria cat4 = new Categoria();
            Categoria cat5 = new Categoria();
            Categoria cat6 = new Categoria();
            Categoria cat7 = new Categoria();
            Categoria cat8 = new Categoria();
            Categoria cat9 = new Categoria();
            Categoria cat10 = new Categoria();
            Categoria cat11 = new Categoria();
            Categoria cat12 = new Categoria();
            cat1.setNombre("Accion");
            cat2.setNombre("Animacion");
            cat3.setNombre("Belico");
            cat4.setNombre("Ciencia Ficcion");
            cat5.setNombre("Comedia");
            cat6.setNombre("Documental");
            cat7.setNombre("Fantanstica");
            cat8.setNombre("Musical");
            cat9.setNombre("Romantica");
            cat10.setNombre("Suspense");
            cat11.setNombre("Terror");
            cat12.setNombre("Western");
            categoriaDAO.insert(cat1);
            categoriaDAO.insert(cat2);
            categoriaDAO.insert(cat3);
            categoriaDAO.insert(cat4);
            categoriaDAO.insert(cat5);
            categoriaDAO.insert(cat6);
            categoriaDAO.insert(cat7);
            categoriaDAO.insert(cat8);
            categoriaDAO.insert(cat9);
            categoriaDAO.insert(cat10);
            categoriaDAO.insert(cat11);
            categoriaDAO.insert(cat12);
        }
    }
}
