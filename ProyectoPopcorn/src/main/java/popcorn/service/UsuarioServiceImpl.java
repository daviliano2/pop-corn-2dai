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

@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    @Autowired
    @Required
    public void setUsuarioDAO(final UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Autowired
    @Required
    public void setRolDAO(final RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    @Override
    public void create(final Usuario usuario) {
        usuarioDAO.insert(usuario);
        usuarioDAO.update(usuario);
    }

    @Override
    public void create(String username, String password, Key idRol) {
        Rol rol = rolDAO.findByPK(Rol.class, idRol);
        System.out.println("rol:  " + rol);
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        //usuarioDAO.insert(usuario);
        System.out.println("usuario:  " + usuario);               
        rol.getUsuarios().add(usuario);
        System.out.println("usuario-rol: " + usuario.getRoles().getNombre());
        //System.out.println("rol-usuario: " + rol.getUsuarios() + "\n");
    }

    /*@Override
    public void create(String contenido, Key idPelicula, Date fecha) {
    Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
    Comentario comentario = new Comentario();
    comentario.setContent(contenido);        
    comentario.setFecha(fecha);
    comentario.setPelicula(pelicula);
    pelicula.getComentarios().add(comentario);
    }*/
    
    @Override
    public Usuario getUsuario(String idUsuario) {
        return usuarioDAO.findByPK(Usuario.class, idUsuario);
    }
    
    @Override
    public Collection<Usuario> getAllUsuarios(Key idRol) {
        Rol rol = rolDAO.findByPK(Rol.class, idRol);
        return rol.getUsuarios();
    }    
       
    /*@Override
    public Collection<Comentario> getAllComentarios(Key idPelicula) {
    Pelicula pelicula = peliculaDAO.findByPK(Pelicula.class, idPelicula);
    return pelicula.getComentarios();                
    }*/

    @Override
    public void setRol(Usuario usuario, String rol) {
        usuario.setRoles(rolDAO.findByString(Rol.class, rol));
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException, DataAccessException {
        System.out.println("AQUI UsuarioServiceImpl loadUserByUsername usuarioDao=" + usuarioDAO);
        Usuario usuario = usuarioDAO.findByPK(Usuario.class, nombreUsuario);
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
    }
    
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
    }*/
}
