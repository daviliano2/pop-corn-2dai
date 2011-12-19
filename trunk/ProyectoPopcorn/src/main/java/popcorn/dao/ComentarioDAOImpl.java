package popcorn.dao;

import com.google.appengine.api.datastore.Key;


import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Comentario;

@Repository
public class ComentarioDAOImpl extends GenericPopDAOImpl<Comentario, Key> implements ComentarioDAO {

    @Override
    public List<Comentario> getComentarios(String username) {
        String sql = "SELECT c FROM Comentario c WHERE c.autor='" + username + "' ";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}