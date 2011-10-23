package popcorn.controlador;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popcorn.dao.ComentarioDAO;
import popcorn.dao.ComentarioDAOImpl;
import popcorn.persistence.Comentario;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ComentarioServlet extends HttpServlet {
    @SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ComentarioServlet.class.getName());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if(user == null) {
        	resp.sendRedirect("generica.jsp");
        }
        
        String content = req.getParameter("content");
	    Date date = new Date();
	    Comentario comentario = new Comentario(user, content, date);
	    
	    ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
	    comentarioDAO.insert(comentario);
	    comentarioDAO.closeEm();

	    resp.sendRedirect("generica.jsp");
    } 
    
}