/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import popcorn.persistence.*;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UsuarioService extends UserDetailsService {
    //void preload_usuarios();    
    void create(final Usuario Usuario);
    void create(String username, String password, Key idRol);
    Collection<Usuario> getAllUsuarios(Key idRol);    
    /*Rol getRol(Usuario usuario);
    void crearRol();
    void setRol(Usuario usuario, String rol);
    void addRol(Usuario usuario, Rol rol);*/
    Usuario getUsuario(String idUsuario);    
    Usuario getCurrentUser();
    void setRol(Usuario usuario, String rol);
    boolean isAdmin();
    
}
