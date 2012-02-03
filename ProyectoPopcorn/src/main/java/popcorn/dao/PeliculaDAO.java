package popcorn.dao;

import com.google.appengine.api.datastore.Key;

import java.util.List;
import popcorn.persistence.Comentario;
import popcorn.persistence.Pelicula;

public interface PeliculaDAO extends GenericPopDAO<Pelicula, Key> {
	List<Pelicula> getPeliculas();
        
}
