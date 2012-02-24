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
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;
    private RolService rolService;

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

    @Autowired
    @Required
    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    @Override
    public void create(final Usuario usuario) {
        final Collection<Rol> roles = rolService.getAllRoles();
        for (Rol rol : roles) {
            if (rol.getNombre().compareTo("ROLE_USER") == 0) {
                Rol rol1 = rolDAO.findByPK(Rol.class, rol.getId());
                usuario.setTipoRol(rol.getDescripcion());
                rol1.getUsuarios().add(usuario);
            }
        }
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
    public void contaComent(Key idUser, final Usuario user, int contador) {
        //System.out.println("AQUI temaService editar 1 tema: " + user);
        Usuario usuario = usuarioDAO.findByPK(Usuario.class, idUser); 
        usuario.setContadorCom(contador);
       // System.out.println("AQUI temaService editar 1 tema2: " + usuario);
    }

    @Override
    public Collection<Usuario> getAllUsuarios(Key idRol) {
        Rol rol = rolDAO.findByPK(Rol.class, idRol);
        return rol.getUsuarios();
    }
    
    @Override
    public Collection<Usuario> getAll() {
        return usuarioDAO.getAll(Usuario.class);
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
            throw new UsernameNotFoundException("User not found." + username);
        } else {
            return makeUser(usuario);
        }
    }

    @Override
    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            return usuarioDAO.findByString(authentication.getName());
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
        result.add(new GrantedAuthorityImpl(usuario.getRol().getNombre()));
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
