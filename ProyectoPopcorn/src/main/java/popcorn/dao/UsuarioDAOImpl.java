package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Usuario;

@Repository
public class UsuarioDAOImpl extends GenericPopDAOJPAImpl<Usuario, Key> implements UsuarioDAO {
	
}