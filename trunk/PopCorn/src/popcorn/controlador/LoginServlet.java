package popcorn.controlador;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popcorn.dao.UsuarioDAO;
import popcorn.dao.UsuarioDAOImpl;
import popcorn.persistence.Usuario;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
    @SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(LoginServlet.class.getName());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
    	String nombreUsuario = req.getParameter("usuario");
    	String password = req.getParameter("password");
    	Usuario usuario2 = new Usuario(nombreUsuario, password);
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    	usuarioDAO.insert(usuario2);
    	usuarioDAO.closeEm();
    	
    	
    } 
    
}