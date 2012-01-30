package popcorn.service;

import com.google.appengine.api.datastore.Key;
import popcorn.dao.NoticiaDAO;
import popcorn.persistence.Noticia;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
/**
 *
 * @author david
 */
@Service
public class NoticiaServiceImpl implements NoticiaService {
    
    private NoticiaDAO noticiaDAO;
    
    @Autowired
    @Required
    public void setNoticiaDAO(final NoticiaDAO noticiaDAO) {
        this.noticiaDAO = noticiaDAO;
    }
    
    @Override
    public void create(final Noticia noticia) {
        noticiaDAO.insert(noticia);
    }    
      
    @Override
    public Noticia getNoticia(Key idNoticia) {
        return noticiaDAO.findByPK(Noticia.class, idNoticia);
    }
    
    @Override
    public Collection<Noticia> getAllNoticias() {
        return noticiaDAO.getAll(Noticia.class);
    }

    @Override
    public int countAllNoticias() {
        return noticiaDAO.countAll(Noticia.class);
    }    
    
}
