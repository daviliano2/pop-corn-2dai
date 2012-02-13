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
import java.util.Iterator;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Transactional
@Service
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
        Tema tema = temaDAO.findByPK(Tema.class, idTema);
        tema.getComentarios().add(comentario);
    }

    @Override
    public Collection<Comentario> getAllComentarios(Key idTema) {
        Tema tema = temaDAO.findByPK(Tema.class, idTema);
        return tema.getComentarios();                
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
        public void delComentario(Comentario comen) {
        System.out.println("delComentario");
        Tema tema = comen.getTema();
        boolean encontrado = false;
        Comentario p = null;
        Iterator<Comentario> it= tema.getComentarios().iterator();
        while (it.hasNext() && !encontrado) {
            p = it.next();
            if (p.getId().equals(comen.getId())) {
                encontrado = true;
            }
        }
       /* if (encontrado) {
            tema.getComentarios().remove(p);
            
        }
        * 
        */
        comentarioDAO.remove(comen);
        
    }
}
