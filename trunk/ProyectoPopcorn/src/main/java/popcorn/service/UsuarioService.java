/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import popcorn.persistence.Usuario;
import java.util.Collection;

/**
 *
 * @author david
 */
public interface UsuarioService {
    
    void create(final Usuario usuario);
    void borrar(final Key idUsuario);
    
    Collection<Usuario> getAllUsuarios();
}
