/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import java.util.List;
import popcorn.persistence.*;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UsuarioService extends UserDetailsService {
    //void preload_usuarios();    
    void create(final Usuario Usuario);
    void create(String username, String password,
                String nombre, String apellido, Key idRol,
                List<String> categorias, String tipoRol);
    Collection<Usuario> getAllUsuarios(Key idRol);    
    Usuario getUsuario(String idUsuario);    
    Usuario getCurrentUser();
    void setRol(Usuario usuario, String rol);
    boolean isAdmin();
    
}
