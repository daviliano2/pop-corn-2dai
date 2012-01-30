package popcorn.controlador;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.persistence.Noticia;
import popcorn.persistence.Pelicula;
import popcorn.service.NoticiaService;
import popcorn.service.PeliculaService;

@Controller
@SessionAttributes({"usuario"})
public class InicioController {

    private PeliculaService peliculaService;
    private NoticiaService noticiaService;

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

    @RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String doIrInicio(Model model) {
        final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas", peliculas);
        final Collection<Noticia> noticias = noticiaService.getAllNoticias();
        model.addAttribute("noticias", noticias);
        return "/inicio";
    }
}
