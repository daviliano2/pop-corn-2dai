/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import com.popcorn.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;


public interface UsuarioService extends UserDetailsService {
    //void preload_usuarios();    
    void create(final Usuario Usuario);
    void create(String username, String password,
                String nombre, String apellido, Key idRol);
    Collection<Usuario> getAllUsuarios(Key idRol); 
    Collection<Usuario> getAll();
    /*Rol getRol(Usuario usuario);
    void crearRol();
    void setRol(Usuario usuario, String rol);
    void addRol(Usuario usuario, Rol rol);*/
    Usuario getUsuario(String idUsuario);    
    void contaComent(Key idUser, final Usuario user, int contador);
    Usuario getCurrentUser();
    void setRol(Usuario usuario, String rol);
    boolean isAdmin();
    public UserDetails loadUserByUsername(String username); 
}
