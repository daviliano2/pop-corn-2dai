package popcorn.controlador;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popcorn.dao.PeliculaDAO;
import popcorn.dao.PeliculaDAOImpl;
import popcorn.persistence.Pelicula;
import popcorn.persistence.Valoracion;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ValoracionServlet extends HttpServlet {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ComentarioServlet.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    
    Key id = KeyFactory.stringToKey(req.getParameter("id"));
    PeliculaDAO peliculaDAO = new PeliculaDAOImpl();
	
    Pelicula pelicula = peliculaDAO.findByPK(popcorn.persistence.Pelicula.class, id);
	if (req.getParameter("valoracion").equals("si")) {
		
		String val = req.getParameter("valoracion");
		Valoracion valoracion = new Valoracion(user,val,pelicula);
		pelicula.getValoraciones().add(valoracion);
	}
	
	resp.sendRedirect("ver_pelicula.jsp");
	}
    
}
