package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import com.popcorn.dao.RolDAO;
import com.popcorn.persistence.Rol;
import com.popcorn.persistence.Usuario;

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
    @Transactional
    public void createRol() {        
        if(rolDAO.countAll(Rol.class) == 0) { 
            rolDAO.removeAll(Rol.class);
            Rol r1 = new Rol();
            Rol r2 = new Rol();
            r1.setNombre("ROLE_ADMIN");
            r1.setDescripcion("Administrador");
            r2.setNombre("ROLE_USER");
            r2.setDescripcion("Usuario");            
            Usuario admin = new Usuario();
            admin.setUsername("daviliano");
            admin.setPassword("1daviliano23");
            admin.setNombre("David");
            admin.setTipoRol(r1.getDescripcion());
            Usuario admin2 = new Usuario();
            admin2.setUsername("sevengear");
            admin2.setPassword("1sevengear23");
            admin2.setNombre("Miguel");
            admin2.setTipoRol(r1.getDescripcion());
            Usuario admin3 = new Usuario();
            admin3.setUsername("lucasino");
            admin3.setPassword("1lucasino23");
            admin3.setNombre("Lucas");
            admin3.setTipoRol(r1.getDescripcion());
            Usuario admin4 = new Usuario();
            admin4.setUsername("admin");
            admin4.setPassword("admin");
            admin4.setNombre("Administrador");
            admin4.setTipoRol(r1.getDescripcion());
            r1.getUsuarios().add(admin);
            r1.getUsuarios().add(admin2);
            r1.getUsuarios().add(admin3);
            r1.getUsuarios().add(admin4);
            admin.setRol(r1);
            admin2.setRol(r1);
            admin3.setRol(r1);
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
