package popcorn.controlador;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.persistence.Pelicula;
import popcorn.persistence.Usuario;
import popcorn.service.PeliculaService;
import popcorn.service.UsuarioService;

@Controller
@SessionAttributes({"usuario"})
public class InicioController {
    
    private PeliculaService peliculaService;

    @Autowired
    @Required
    public void setPeliculaService(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }
    
    @RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String doIrInicio(Model model) {
        final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas",peliculas);
        return "/inicio";
    }    
    
}
