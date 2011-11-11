package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Valoracion;

@Repository
public class ValoracionDAOImpl extends GenericPopDAOJPAImpl<Valoracion, Key> implements ValoracionDAO {
	
}
