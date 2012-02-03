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
        String sql = "SELECT c FROM Comentario c WHERE c.autor='" + username + "' ORDER by fecha";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
    
    @Override
    public List<Comentario> getComentariosPeli(String titulo) {
        String sql = "SELECT c FROM Comentario c WHERE c.nomPeli='" + titulo + "' ORDER by fecha DESC";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
    
}