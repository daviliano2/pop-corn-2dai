package popcorn.service;

//import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import popcorn.dao.ComentarioDAO;
import popcorn.persistence.Comentario;
/**
 *
 * @author miguel
 */

@Service
public class ComentarioServiceImpl implements ComentarioService{
    
    private ComentarioDAO comentarioDAO;
    private UserService userService;
    
    @Autowired
    @Required
    public void setComentarioDAO(final ComentarioDAO comentarioDao) {
        this.comentarioDAO = comentarioDao;
    }

    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(final Comentario greeting) {
        comentarioDAO.insert(greeting);
    }

    @Override
    public Collection<Comentario> getAllComentarios() {
        return comentarioDAO.getAll(Comentario.class);
    }

    @Override
    public Collection<Comentario> getPaginaComentarios(int startPosition, int maxResult) {
        return comentarioDAO.getOrderedPaginated(Comentario.class, startPosition, maxResult, "fecha", 2);
    }

    @Override
    public int countAllComentarios() {
        return comentarioDAO.countAll(Comentario.class);
    }
}
