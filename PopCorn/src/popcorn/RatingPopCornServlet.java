package popcorn;

import java.io.IOException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class RatingPopCornServlet extends HttpServlet {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SignPopCornServlet.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    String rating = req.getParameter("rate");
	Valoracion val = new Valoracion(rating,user);
	
	EntityManager em = EMF.get().createEntityManager();
	try {
		em.persist(val);
	}finally {
		em.close();
	}
	//resp.sendRedirect("popcorn.jsp");
	}
    
}
