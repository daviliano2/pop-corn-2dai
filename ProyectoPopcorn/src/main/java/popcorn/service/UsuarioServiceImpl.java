/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
/*import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;*/
import popcorn.dao.UsuarioDAO;
import popcorn.persistence.Usuario;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
/**
 *
 * @author david
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private UsuarioDAO usuarioDAO;
    
    @Autowired
    @Required
    public void setUsuarioDAO(final UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    @Override
    public void create(final Usuario usuario) {
        usuarioDAO.insert(usuario);
    }
    
    @Override
    public void borrar(Key idUsuario) {
        Usuario usuario = usuarioDAO.findByPK(Usuario.class, idUsuario);
        if(usuario != null) {
            usuarioDAO.remove(usuario);
        }
    }
    
    @Override
    public Collection<Usuario> getAllUsuarios() {
        return usuarioDAO.getAll(Usuario.class);
    }
}
