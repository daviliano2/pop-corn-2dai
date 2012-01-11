package popcorn.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import popcorn.persistence.Valoracion;

public interface ValoracionDAO extends GenericPopDAO<Valoracion, Key> {
	
    Valoracion getValoracion(String username);
    List<Valoracion> getValoraciones(String username);
    
}
