package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import popcorn.dao.ComentarioDAO;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Comentario;
import popcorn.persistence.Pelicula;

/**
 *
 * @author miguel
 */
@Service
public class ComentarioServiceImpl implements ComentarioService {

    private ComentarioDAO comentarioDAO;
    private PeliculaDAO peliculaDAO;
   
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

    @Override
    public void create(final Comentario comentario) {
        comentarioDAO.insert(comentario);
    }

    @Override
    public void create(String contenido, Key idPelicula, Date fecha) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        Comentario comentario = new Comentario();
        comentario.setContent(contenido);        
        comentario.setFecha(fecha);
        comentario.setPelicula(pelicula);
        pelicula.getComentarios().add(comentario);
    }

    @Override
    public Collection<Comentario> getAllComentarios(Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        return pelicula.getComentarios();                
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
