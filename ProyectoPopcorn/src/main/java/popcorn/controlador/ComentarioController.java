/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.controlador;

//import com.google.appengine.api.users.User;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import java.util.Collection;
import java.util.Date;
import popcorn.persistence.Comentario;
import popcorn.service.ComentarioService;
/*import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;*/
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComentarioController {
    
    private ComentarioService comentarioService;
    private UserService userService;

    @Autowired
    @Required
    public void setComentarioService(
            ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    @Required
    public void getComentarioService(
            ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }
     
    @RequestMapping(value = "/ir_ver_comentario", method = RequestMethod.GET)
    public String doVerComentario(Model model) {
        
        final Collection<Comentario> comentarios = comentarioService.getAllComentarios();
        
        model.addAttribute("comentarios", comentarios);
               
        return "/ver_comentarios";

    }
    
    @RequestMapping(value = "/comentar", method = RequestMethod.POST)
    public String doCrearComentario(@RequestParam("content")String content, Model model) {
        
        final User author = userService.getCurrentUser();
        final Date fecha = new Date();
        final Comentario comentario = new Comentario(author,content,fecha);
        comentarioService.create(comentario);
        return "redirect:ir_ver_pelicula?";

    }
}
