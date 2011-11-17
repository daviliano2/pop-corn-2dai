package popcorn.service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.UserService;
import popcorn.dao.ValoracionDAO;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Valoracion;
import popcorn.persistence.Pelicula;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service //IMPORTANTE ESTA ANOTACION, PERTENECE A STEREOTYPE.SERVICE
public class ValoracionServiceImpl implements ValoracionService {
    
    private PeliculaDAO peliculaDAO;
    private ValoracionDAO valoracionDAO;
    private UserService userService;
    
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
    
    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public void create(Valoracion valoracion) {
        valoracionDAO.insert(valoracion);
    }

    @Override
    public void create(int valorValoracion, Key idPelicula) {
        Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
        Valoracion valoracion = new Valoracion();
        valoracion.setValoracion(valorValoracion);
        valoracion.setPelicula(pelicula);
        pelicula.getValoraciones().add(valoracion);        
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
