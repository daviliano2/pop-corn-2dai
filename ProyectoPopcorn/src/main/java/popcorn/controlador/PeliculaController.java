
package popcorn.controlador;

import com.google.appengine.api.users.UserService;
import java.util.ArrayList;
import java.util.Collection;
import popcorn.persistence.Pelicula;
import popcorn.service.PeliculaService;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeliculaController {
    
    private PeliculaService peliculaService;
    private UserService userService;

    @Autowired
    @Required
    public void setPeliculaService(
            PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/ir_ver_pelicula", method = RequestMethod.GET)
    public String doVerPelicula(Model model) {        
        final Collection<Pelicula> pelicula = peliculaService.getAllPeliculas();        
        model.addAttribute("pelicula", pelicula);        
        return "/ver_pelicula";
    }
    
    @RequestMapping(value = "/ir_crear_pelicula", method = RequestMethod.GET)
    public String doShowCrearPelicula() {
        return "/crear_pelicula";
    }
    
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String doCrearPelicula(@RequestParam("titulo") String titulo,@RequestParam("sinopsis")String sinopsis,
    @RequestParam("duracion") int duracion,@RequestParam("categoria") String categoria,@RequestParam("actores") String actores,
    @RequestParam("director") String director,@RequestParam("imagen") String imagen, Model model) {
        final List<String> actor = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(actores,",");
	while(tokens.hasMoreTokens()) {            
            String actrs = tokens.nextToken();
            actrs.trim();
            actor.add(actrs);
            System.out.println(actor.toString());            
        }        
        final Pelicula pelicula = new Pelicula(titulo, sinopsis, duracion, categoria, actor, director,imagen);
        peliculaService.create(pelicula);
        return "redirect:inicio?pagina=1";
    }
}
