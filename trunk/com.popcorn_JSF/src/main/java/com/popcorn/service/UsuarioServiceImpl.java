/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;

import com.popcorn.dao.*;
import com.popcorn.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public void create(String username, String password,
                        String nombre, String apellido, Key idRol) {
        Rol rol = rolDAO.findByPK(Rol.class, idRol);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setUsername(username);
        usuario.setPassword(password);
        rol.getUsuarios().add(usuario);
    }
    
    @Override
    public Usuario getUsuario(String idUsuario) {
        return usuarioDAO.findByString(idUsuario);
    }
    
    @Override
    public Collection<Usuario> getAllUsuarios(Key idRol) {
        Rol rol = rolDAO.findByPK(Rol.class, idRol);
        return rol.getUsuarios();
    }

    @Override
    public void setRol(Usuario usuario, String rol) {
        //usuario.setRoles(rolDAO.findByString(Rol.class, rol));
        //NO FUNCIONA
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {        
        Usuario usuario = new Usuario();
        usuario = usuarioDAO.findByString(username);        
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + usuario.getUsername());
        } else {
           // System.out.println("AQUI UsuarioServiceImpl3 loadUserByUsername3 antes de makeUser usuario=" + usuario);
            return makeUser(usuario);
        }
    }

    @Override
    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("AQUI getCurrentUser1 authentication: " + authentication);
        if (authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            //System.out.println("AQUI getCurrentUser2 authentication.getName(): " + authentication.getName()); 
            
            return usuarioDAO.findByString(authentication.getName());
        } else {
            //System.out.println("AQUI getCurrentUser3, si ves esto retorna null y da error");
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
        result.add(new GrantedAuthorityImpl(usuario.getRol().getNombre()));
        System.out.println("-- AKI  RESULT :"+result);
        System.out.println("-- AKI USUARIO :"+usuario);
        System.out.println("-- AKI USUARIO.GETROL()"+usuario.getRol());
        System.out.println("-- AKI USUARIO.GETROL().GETNOMBRE()"+usuario.getRol().getNombre());
        //}
        return result;
    }

   @Override
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
