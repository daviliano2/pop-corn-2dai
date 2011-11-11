package popcorn.dao;

import com.google.appengine.api.datastore.Key;

import org.springframework.stereotype.Repository;
import popcorn.persistence.Comentario;

@Repository
public class ComentarioDAOImpl extends GenericPopDAOJPAImpl<Comentario, Key> implements ComentarioDAO {
	
}