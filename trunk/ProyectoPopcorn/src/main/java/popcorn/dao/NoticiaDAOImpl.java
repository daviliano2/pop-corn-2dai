package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Noticia;

@Repository
public class NoticiaDAOImpl extends GenericPopDAOImpl<Noticia, Key> implements NoticiaDAO {
	
    @Override
    public List<Noticia> getNoticias() {
        String sql = "SELECT n FROM Noticia n ORDER by fecha DESC";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}
