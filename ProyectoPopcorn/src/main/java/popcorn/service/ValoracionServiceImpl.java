package popcorn.service;

import com.google.appengine.api.datastore.Key;

import popcorn.dao.ValoracionDAO;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Valoracion;
import popcorn.persistence.Pelicula;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import popcorn.controlador.UsuarioController;

@Service //IMPORTANTE ESTA ANOTACION, PERTENECE A STEREOTYPE.SERVICE
public class ValoracionServiceImpl implements ValoracionService {
    
    private PeliculaDAO peliculaDAO;
    private ValoracionDAO valoracionDAO;
    private UsuarioController userController;
   
    @Autowired
    @Required
    public void setUsuarioController(UsuarioController userController) {
        this.userController = userController;
    }
    
    
    @Autowired
    @Required
    public void setPeliculaDAO(final PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }
    
    @Autowired
    @Required
    public void setValoracionDAO(final ValoracionDAO valoracionDAO) {
        this.valoracionDAO = valoracionDAO;
    }    
     
    @Override
    @PreAuthorize("isAuthenticated()")
    public void create(Valoracion valoracion) {
        valoracionDAO.insert(valoracion);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public void create(final Valoracion valoracion, Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);        
        boolean sw = false;
        if(!pelicula.getValoraciones().isEmpty()) {
            for(Valoracion val_nueva : pelicula.getValoraciones()) {
                if(val_nueva.getAutor().compareTo(valoracion.getAutor()) == 0) {
                    int val = valoracion.getValoracion();
                    val_nueva.setValoracion(val);
                    sw = true;
                }
            }
        } else {
            pelicula.getValoraciones().add(valoracion);  
        }
        if(sw == false) {
            pelicula.getValoraciones().add(valoracion);  
        }
    }

    @Override
    public Collection<Valoracion> getValoraciones(Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        return pelicula.getValoraciones();
    }

    @Override
    public int contarValoraciones(Key idPelicula) {
        return getValoraciones(idPelicula).size();
    }

    @Override
    public Double mediaValoracion(Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        Double suma = 0.0;
        for(Valoracion valoracion:pelicula.getValoraciones()) {
            suma += valoracion.getValoracion();
        }
        return suma/pelicula.getValoraciones().size();
    }
}
