package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import com.popcorn.dao.TemaDAO;
import com.popcorn.persistence.Comentario;
import com.popcorn.persistence.Tema;
import java.util.Iterator;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Transactional
@Service
public class TemaServiceImpl implements TemaService {

    private TemaDAO temaDAO;
    
    
    @Autowired
    @Required
    public void setTemaDAO(final TemaDAO temaDao) {
        this.temaDAO = temaDao;
    }

    @Override
    public void create(final Tema tema) {
        temaDAO.insert(tema);
    }

    /*@Override
    public void create(final Tema tema, Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        pelicula.getTemas().add(Tema);
    }

    @Override
    public Collection<Tema> getAllTemas(Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        return pelicula.getTemas();                
    }*/
    
    @Override
    public Collection<Tema> getAll() {
        return temaDAO.getAll(Tema.class);
    }

    @Override
    public Collection<Tema> getPaginaTemas(int startPosition, int maxResult) {
        return temaDAO.getOrderedPaginated(Tema.class, startPosition, maxResult, "fecha", 2);
    }

    @Override
    public int countAllTemas() {
        return temaDAO.countAll(Tema.class);
    }
    
    @Override
    public void removeTema(Tema tema) {
        temaDAO.remove(Tema.class, tema.getId());
    }
    
}
