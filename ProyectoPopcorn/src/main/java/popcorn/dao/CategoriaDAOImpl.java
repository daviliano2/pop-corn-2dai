package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import popcorn.persistence.Categoria;

@Repository
public class CategoriaDAOImpl extends GenericPopDAOImpl<Categoria, Key> implements CategoriaDAO {
	
}