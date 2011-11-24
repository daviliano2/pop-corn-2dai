package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Pelicula;

@Repository
public class PeliculaDAOImpl extends GenericPopDAOImpl<Pelicula, Key> implements PeliculaDAO {
	
}
