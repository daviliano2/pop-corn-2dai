package popcorn.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popcorn.dao.PeliculaDAO;
import popcorn.dao.PeliculaDAOImpl;
import popcorn.persistence.Pelicula;

public class PeliculaServlet extends HttpServlet {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(PeliculaServlet.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		String titulo = req.getParameter("titulo");
		String sinopsis = req.getParameter("sinopsis");
		Integer duracion = (Integer.parseInt(req.getParameter("duracion")));
		String director = req.getParameter("director");
		String categoria = req.getParameter("categoria");
		List<String> actores = new ArrayList<String>();
		actores.add(req.getParameter(""));
		Pelicula pelicula = new Pelicula(titulo,sinopsis,duracion,categoria,actores,director);
		
		PeliculaDAO peliculaDAO = new PeliculaDAOImpl();
		peliculaDAO.insert(pelicula);
		peliculaDAO.closeEm();
		resp.sendRedirect("inicio.jsp");
	}

}
