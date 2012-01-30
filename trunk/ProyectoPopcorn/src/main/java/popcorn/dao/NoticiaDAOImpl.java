package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Noticia;

@Repository
public class NoticiaDAOImpl extends GenericPopDAOImpl<Noticia, Key> implements NoticiaDAO {
	
}
