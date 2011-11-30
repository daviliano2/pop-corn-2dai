package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import popcorn.dao.RolDAO;
import popcorn.persistence.Rol;

/**
 *
 * @author david
 */
@Service
public class RolServiceImpl implements RolService {

    private RolDAO rolDAO;

    @Autowired
    @Required
    public void setRolDAO(final RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    @PostConstruct
    @Override
    public void createRol() {        
        if(rolDAO.countAll(Rol.class) == 0) { 
            rolDAO.removeAll(Rol.class);
            Rol r1 = new Rol();
            Rol r2 = new Rol();
            r1.setNombre("ROLE_ADMIN");
            r1.setDescripcion("Administrador");
            r2.setNombre("ROLE_USER");
            r2.setDescripcion("Usuario");
            rolDAO.insert(r1);
            rolDAO.insert(r2);
        }

    }

    @Override
    public Rol getRol(Key idRol) {
        return rolDAO.findByPK(Rol.class, idRol);
    }

    @Override
    public Collection<Rol> getAllRoles() {
        return rolDAO.getAll(Rol.class);
    }
}
