package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import com.popcorn.dao.ComentarioDAO;
import com.popcorn.dao.TemaDAO;
import com.popcorn.persistence.Comentario;
import com.popcorn.persistence.Tema;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author miguel
 */
@Transactional
@Service("comentarioService")
public class ComentarioServiceImpl implements ComentarioService {

    private ComentarioDAO comentarioDAO;
    private TemaDAO temaDAO;

    @Autowired
    @Required
    public void setComentarioDAO(final ComentarioDAO comentarioDao) {
        this.comentarioDAO = comentarioDao;
    }

    @Autowired
    @Required
    public void setPeliculaDAO(final TemaDAO temaDAO) {
        this.temaDAO = temaDAO;
    }

    @Override
    public void create(final Comentario comentario) {
        comentarioDAO.insert(comentario);
    }

    @Override
    public void create(final Comentario comentario, Key idTema) {
        comentarioDAO.insert(comentario);        
        //Tema tema = temaDAO.findByPK(Tema.class, idTema);
        //addComentario(comentario, tema);
       // create(comentario);       
    }
    
    @Transactional
    @Override
    public void addComentario(Comentario comentario, Tema tema) {
        Tema tema2 = temaDAO.findByPK(Tema.class, tema.getId());
        tema2.getComentarios().add(comentario.getId());        
    }
    
    @Override
    public Comentario getComentario(Key idComentario) {
        return comentarioDAO.findByPK(Comentario.class, idComentario);
    }

    @Override
    public Collection<Key> getAllComentarios(Key idTema) {
        //System.out.println("AQUI comentarioServiceImpl getAllComentarios idTema: " + idTema);
        Tema tema = temaDAO.findByPK(Tema.class, idTema);
        //System.out.println("AQUI comentarioServiceImpl getAllComentarios 2 Tema: " + tema.getTitulo());
        return tema.getComentarios();
    }
    
    @Override
    public Collection<Comentario> getAllColum(Tema tema) {        
        return comentarioDAO.getAllColum(Comentario.class, "tema", tema.getTitulo());
    }
    
    @Override
    public Collection<Key> getTemaComentarios(Tema tema) {
        System.out.println("AQUI comentarioServiceImpl tema: " + tema);
        return comentarioDAO.getComentariosTema(tema.getTitulo());
    }

    @Override
    public Collection<Comentario> getAll() {
        return comentarioDAO.getAll(Comentario.class);
    }

    @Override
    public Collection<Comentario> getPaginaComentarios(int startPosition, int maxResult) {
        return comentarioDAO.getOrderedPaginated(Comentario.class, startPosition, maxResult, "fecha", 2);
    }

    @Override
    public int countAllComentarios() {
        return comentarioDAO.countAll(Comentario.class);
    }   
   
    @Override
    public void removeComentario(Comentario comen) {
        System.out.println("AQUI comentarioService removeComentario");        
        comentarioDAO.remove(Comentario.class,comen.getId());  
        Tema tema = temaDAO.findByPK(Tema.class, comen.getIdTema());
        tema.getComentarios().remove(comen.getId());
    }
   
}
