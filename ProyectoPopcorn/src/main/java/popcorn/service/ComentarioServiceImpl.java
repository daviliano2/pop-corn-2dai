package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import popcorn.dao.ComentarioDAO;
import popcorn.dao.NoticiaDAO;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Comentario;
import popcorn.persistence.Noticia;
import popcorn.persistence.Pelicula;

/**
 *
 * @author miguel
 */
@Service
@Transactional
public class ComentarioServiceImpl implements ComentarioService {

    private ComentarioDAO comentarioDAO;
    private PeliculaDAO peliculaDAO;
    private NoticiaDAO noticiaDAO;
    
    
    @Autowired
    @Required
    public void setComentarioDAO(final ComentarioDAO comentarioDao) {
        this.comentarioDAO = comentarioDao;
    }

    @Autowired
    @Required
    public void setPeliculaDAO(final PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }    
    
    @Autowired
    @Required
    public void setNoticiaDAO(final NoticiaDAO noticiaDAO) {
        this.noticiaDAO = noticiaDAO;
    }

    @Override
    public void create(final Comentario comentario) {
        comentarioDAO.insert(comentario);
    }

    @Override
    public void create(final Comentario comentario, Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        pelicula.getComentarios().add(comentario);
    }
    
    @Override
    public void create2(final Comentario comentario, Key idNoticia) {
        System.out.println("Aki comentarioServiceImpl create2 0 noticia ID: " + idNoticia);
        Noticia noticia = noticiaDAO.findByPK(Noticia.class, idNoticia);
        System.out.println("Aki comentarioServiceImpl create2 1 noticia: "+ noticia);
        System.out.println("Aki comentarioServiceImpl create2 2 comentarioID, comentario: "+ comentario + comentario.getContent());
        noticia.getComentarios().add(comentario.getId());
        
        /*
        Usuario u1 = new Usuario();
        u1.setUsername("pepe");
        u1.setPassword("pepe");        

        Rol r1 = new Rol();
        r1.setNombre("ROLE_ADMIN");

        rolDao.insert(r1);
        rolDao.insert(r2);

        addRol(u1, r1);*/
    }

    @Override
    public Collection<Comentario> getAllComentarios(Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        return pelicula.getComentarios();                
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
}
