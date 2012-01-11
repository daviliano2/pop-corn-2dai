package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Valoracion;

@Repository
public class ValoracionDAOImpl extends GenericPopDAOImpl<Valoracion, Key> implements ValoracionDAO {

    @Override
    public Valoracion getValoracion(String username) {
        String sql = "SELECT v FROM Valoracion v WHERE v.autor='" + username + "' ";
        Query query = em.createQuery(sql);
        return (Valoracion) query.getSingleResult();
    }
    
    @Override
    public List<Valoracion> getValoraciones(String username) {
        String sql = "SELECT v FROM Valoracion v WHERE v.autor='" + username + "' ";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}
