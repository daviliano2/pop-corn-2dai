package popcorn.service;

import com.google.appengine.api.datastore.Key;
import popcorn.persistence.Valoracion;
import java.util.Collection;

public interface ValoracionService {
    
    void create(final Valoracion valoracion);
    
    void create(int valor, Key idPelicula);
    
    Collection<Valoracion> getValoraciones(Key idPelicula);
    
    int contarValoraciones(Key idPelicula);
    
    Double avgValoracion(Key idPelicula);
}
