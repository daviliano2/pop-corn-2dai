/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import popcorn.dao.*;
import popcorn.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


import org.springframework.stereotype.Service;
import popcorn.persistence.Rol;

@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDAO usuarioDao;
    private RolDAO rolDao;

    @Autowired
    @Required
    public void setUsuarioDao(final UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Autowired
    @Required
    public void setRolDao(final RolDAO rolDao) {
        this.rolDao = rolDao;
    }

    /*@PostConstruct
    @Override
    public void preload_usuarios() {
        System.out.println("AQUI UsuarioServiceImpl preload_usuarios");
        usuarioDao.removeAll(Usuario.class);
        rolDao.removeAll(Rol.class);

        Usuario u1 = new Usuario();
        u1.setUsername("pepe");
        u1.setPassword("pepe");

        Usuario u2 = new Usuario();
        u2.setUsername("juan");
        u2.setPassword("juan");

        Usuario u3 = new Usuario();
        u3.setUsername("luis");
        u3.setPassword("luis");

        Rol r1 = new Rol();
        r1.setNombre("ROLE_ADMIN");

        Rol r2 = new Rol();
        r2.setNombre("ROLE_USER");

        rolDao.insert(r1);
        rolDao.insert(r2);

        addRol(u1, r1);
        addRol(u1, r2);
        addRol(u2, r2);
        addRol(u3, r2);

        create(u1);
        create(u2);
        create(u3);

        System.out.println("AQUI UsuarioServiceImpl preload_usuarios usuarios=" + usuarioDao.countAll(Usuario.class));
    }*/    
    
    @PostConstruct
    @Override
    public void crearRol() {
        Rol r1 = new Rol();
        Rol r2 = new Rol();
        r1.setNombre("ROLE_ADMIN");        
        r2.setNombre("ROLE_USER");
        rolDao.insert(r1);
        rolDao.insert(r2);        
    }
    
    @Override
    public void create(Usuario usuario) {
        usuarioDao.insert(usuario);
    }
      
    @Override
    public Usuario getUsuario(String idUsuario) {
        return usuarioDao.findByPK(Usuario.class, idUsuario);
    }
    
    @Override
    public Rol getRol(Usuario usuario) {
        return rolDao.findByString(Rol.class, "ROLE_USER");
    }
    
    @Override
    public void setRol(Usuario usuario, String nombreRol) {
        Rol rol = rolDao.findByString(Rol.class, nombreRol);
        usuario.setRoles(rol);
    }
    
    public Rol getRoles(Usuario usuario) {
        Collection<Rol> roles = new ArrayList<Rol>();
        Rol rol = rolDao.findByPK(Rol.class, usuario.getRoles().getId());
        roles.add(rol);        
        return (Rol) roles;
    }
    
    @Override
    public void addRol(Usuario usuario, Rol rol) {
        usuario.getRoles().add(rol.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException, DataAccessException {
        System.out.println("AQUI UsuarioServiceImpl loadUserByUsername usuarioDao=" + usuarioDao);
        Usuario usuario = usuarioDao.findByPK(Usuario.class, nombreUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + nombreUsuario);
        } else {
            return makeUser(usuario);
        }
    }

    @Override
    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            return getUsuario(username);
        } else {
            return null;
        }
    }

    private org.springframework.security.core.userdetails.User makeUser(Usuario usuario) {
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
                true, true, true, true, makeGrantedAuthorities(usuario));
    }

    private Collection<GrantedAuthority> makeGrantedAuthorities(Usuario usuario) {
        Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        //int i = 0;
        //for (Rol rol : getRol(usuario)) {
            result.add(new GrantedAuthorityImpl(usuario.getRoles().getNombre()));
        //}
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    public boolean isAdmin() {
        final String ROLE_ADMIN = "ROLE_ADMIN";
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            User user = ((User) auth.getPrincipal());
            Collection<GrantedAuthority> roles = user.getAuthorities();
            if (roles != null) {
                Iterator<GrantedAuthority> it = roles.iterator();
                while (!isAdmin && it.hasNext()) {
                    GrantedAuthority rol = it.next();
                    if (rol.getAuthority().equals(ROLE_ADMIN)) {
                        isAdmin = true;
                    }
                }
            }
        }
        return isAdmin;
    }
}
