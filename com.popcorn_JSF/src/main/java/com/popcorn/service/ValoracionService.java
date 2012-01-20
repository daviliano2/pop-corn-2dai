package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import com.popcorn.persistence.Valoracion;
import java.util.Collection;

public interface ValoracionService {
    
    void create(final Valoracion valoracion);
    
    void create(final Valoracion valoracion, Key idPelicula);
    
    Collection<Valoracion> getValoraciones(Key idPelicula);
    
    int contarValoraciones(Key idPelicula);
    
    Double mediaValoracion(Key idPelicula);
}
