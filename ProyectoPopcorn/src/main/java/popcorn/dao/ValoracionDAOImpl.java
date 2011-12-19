package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Valoracion;

@Repository
public class ValoracionDAOImpl extends GenericPopDAOImpl<Valoracion, Key> implements ValoracionDAO {

    @Override
    public Valoracion getValoracion(String username) {
        String sql = "SELECT c FROM Comentario c WHERE c.autor='" + username + "' ";
        Query query = em.createQuery(sql);
        return (Valoracion) query.getSingleResult();
    }
}
