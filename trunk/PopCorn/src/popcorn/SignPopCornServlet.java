package popcorn;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.persistence.EntityManager;

import popcorn.Comentario;
import popcorn.EMF;

@SuppressWarnings("serial")
public class SignPopCornServlet extends HttpServlet {
    @SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SignPopCornServlet.class.getName());

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if(user == null) {
        	resp.sendRedirect("popcorn.jsp");
        }
	    String content = req.getParameter("content");
	    Date date = new Date();
	    Comentario comentario = new Comentario(user, content, date);
	    
	    EntityManager em = EMF.get().createEntityManager();
	    try {
	    	em.persist(comentario);
	    } finally {	
	    	em.close();
	    }
	    resp.sendRedirect("popcorn.jsp");
    }
}