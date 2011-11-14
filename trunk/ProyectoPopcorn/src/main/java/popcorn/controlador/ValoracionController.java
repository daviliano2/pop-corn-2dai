package popcorn.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import java.util.Collection;
import popcorn.service.PeliculaService;
import popcorn.service.ValoracionService;
import popcorn.persistence.Pelicula;
import popcorn.persistence.Valoracion;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ValoracionController {
    
    private ValoracionService valoracionService;
    private UserService userService;
    private PeliculaService peliculaService;

    @Autowired
    @Required
    public void setValoracionService(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Required
    public void setPeliculaService(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }
       
    @RequestMapping(value = "/valorar", method = RequestMethod.GET)
    public String doCrearValoracion(@RequestParam ("valoracion") int val, 
                            @RequestParam ("idPelicula") String idStringPelicula) {
        
        //final Pelicula pelicula = peliculaService.getPelicula(idPelicula);
        //final Valoracion valoracion = new Valoracion(val, idPelicula);
        valoracionService.create(val,KeyFactory.stringToKey(idStringPelicula));
        return "redirect:ir_ver_pelicula";
    }
    
    @RequestMapping(value = "/ir_ver_valoraciones", method = RequestMethod.GET)   
    public String doVerValoracion(@RequestParam Key idPelicula, Model model) {        
        
        final Collection<Valoracion> valoraciones = valoracionService.getValoraciones(idPelicula);
        
        model.addAttribute("valoraciones", valoraciones);
               
        return "/ver_valoraciones";

    }
}
