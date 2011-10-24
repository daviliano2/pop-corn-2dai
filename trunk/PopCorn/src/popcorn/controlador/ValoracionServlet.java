package popcorn.controlador;

import java.io.IOException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import popcorn.persistence.Valoracion;

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
    //String rating = req.getParameter("rate");
    String valoracion = req.getParameter("valoracion");
    //Pelicula pelicula = req.;
	//Pelicula pelicula = req.getParameterValues(pelicula);
	//Valoracion val = new Valoracion(rating,user,voto);
	Valoracion val = new Valoracion(user,valoracion,pelicula*/);
	
	
	
	EntityManager em = EMF.get().createEntityManager();
	try {
		em.persist(val);
	}finally {
		em.close();
	}
	resp.sendRedirect("popcorn.jsp");
	}
    
}
