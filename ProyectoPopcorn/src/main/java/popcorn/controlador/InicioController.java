package popcorn.controlador;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Categoria;
import popcorn.persistence.Noticia;
import popcorn.persistence.Pelicula;
import popcorn.persistence.Usuario;
import popcorn.service.CategoriaService;
import popcorn.service.NoticiaService;
import popcorn.service.PeliculaService;

@Controller
@SessionAttributes({"usuario"})
public class InicioController {

    private PeliculaService peliculaService;
    private NoticiaService noticiaService;
    private UsuarioController userController;
    private CategoriaService categoriaService;
    private PeliculaDAO peliculaDAO;

    @Autowired
    @Required
    public void setPeliculaService(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @Autowired
    @Required
    public void setNoticiaService(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @Autowired
    @Required
    public void setUsuarioController(UsuarioController userController) {
        this.userController = userController;
    }

    @Autowired
    @Required
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    @Autowired
    @Required
    public void setPeliculaDAO(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    @RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String doIrInicio(Model model) {
        final Usuario usuario = userController.getUser();
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        //model.addAttribute("categorias", categorias);
        //final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        final List<Pelicula> peliculas = peliculaDAO.getPeliculas();
        //model.addAttribute("peliculas", peliculas);
        Collection<Pelicula> pelisUser = new ArrayList<Pelicula>();
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            for (Pelicula peli : peliculas) {
                for (Categoria cate : categorias) {
                    for (Key cateUser : usuario.getCategoria()) {
                        if (cateUser.compareTo(cate.getId()) == 0) {
                            if (cate.getNombre().compareTo(peli.getCategoria()) == 0) {
                                pelisUser.add(peli);
                            }
                        }
                    }
                }
            }
            model.addAttribute("peliculas", pelisUser);
        }
        final Collection<Noticia> noticias = noticiaService.getAllNoticias();
        model.addAttribute("noticias", noticias);
        return "/inicio";
    }
}
