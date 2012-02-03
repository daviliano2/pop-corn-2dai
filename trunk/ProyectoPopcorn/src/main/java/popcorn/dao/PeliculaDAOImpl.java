package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Pelicula;

@Repository
public class PeliculaDAOImpl extends GenericPopDAOImpl<Pelicula, Key> implements PeliculaDAO {
    
    @Override
    public List<Pelicula> getPeliculas() {
        String sql = "SELECT p FROM Pelicula p ORDER by fechEstreno DESC";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
    
}
