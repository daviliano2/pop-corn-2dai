package popcorn.service;

import com.google.appengine.api.datastore.Key;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Pelicula;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
/**
 *
 * @author david
 */
@Service
public class PeliculaServiceImpl implements PeliculaService {
    
    private PeliculaDAO peliculaDAO;
    
    @Autowired
    @Required
    public void setPeliculaDAO(final PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }
    
    @Override
    public void create(final Pelicula pelicula) {
        peliculaDAO.insert(pelicula);
    }    
      
    @Override
    public Pelicula getPelicula(Key idPelicula) {
        return peliculaDAO.findByPK(Pelicula.class, idPelicula);
    }
    
    @Override
    public Collection<Pelicula> getAllPeliculas() {
        return peliculaDAO.getAll(Pelicula.class);
    }

    @Override
    public int countAllPeliculas() {
        return peliculaDAO.countAll(Pelicula.class);
    }
    
    @Override
    public void borrarPeli(Key idPelicula) {
        peliculaDAO.remove(Pelicula.class, idPelicula);
    }
    
    @Override
    public void editarPeli(Key idPelicula, final Pelicula pelicula) {
        Pelicula peli = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        peli.setActores(pelicula.getActores());
        peli.setDirector(pelicula.getDirector());
        peli.setDuracion(pelicula.getDuracion());
        peli.setTitulo(pelicula.getTitulo());
        peli.setSinopsis(pelicula.getSinopsis());
        peli.setCategoria(pelicula.getCategoria());
    } 
}
