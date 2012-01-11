package popcorn.dao;

import javax.persistence.Query;
import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Usuario;

@Repository
public class UsuarioDAOImpl extends GenericPopDAOImpl<Usuario, Key> implements UsuarioDAO {
    
    @Override
    public Usuario findByString(String username) {
        try {
            String sql = "SELECT u FROM Usuario u WHERE u.username='" + username + "'";
            Query query = em.createQuery(sql);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}