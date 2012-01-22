package popcorn.controlador;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import popcorn.persistence.Comentario;
import popcorn.service.ComentarioService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.dao.ComentarioDAO;
import popcorn.dao.ValoracionDAO;
import popcorn.persistence.Usuario;
import popcorn.persistence.Valoracion;

@Controller
@SessionAttributes({"usuario"})
public class ComentarioController {

    private ComentarioService comentarioService;
    private UsuarioController userController;
    private ComentarioDAO comentarioDAO;
    private ValoracionDAO valoracionDAO;

    @Autowired
    @Required
    public void setValoracionDAO(final ValoracionDAO valoracionDAO) {
        this.valoracionDAO = valoracionDAO;
    }
   
    @Autowired
    @Required
    public void setComentarioDAO(final ComentarioDAO comentarioDao) {
        this.comentarioDAO = comentarioDao;
    }

    @Autowired
    @Required
    public void setUsuarioController(UsuarioController userController) {
        this.userController = userController;
    }

    @Autowired
    @Required
    public void setComentarioService(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @RequestMapping(value = "/comentar", method = RequestMethod.POST)
    public String doCrearComentario(@RequestParam("content") String content,
            @RequestParam("idPelicula") String idStringPelicula) {
        final Usuario usuario = userController.getUser();
        final Date fecha = new Date();
        final Comentario comentario;
        if (usuario == null) {
            comentario = new Comentario(content, fecha);
        } else {
            comentario = new Comentario(usuario, content, fecha);
        }
        comentarioService.create(comentario, KeyFactory.stringToKey(idStringPelicula));
        return "redirect:inicio";
    }

    @RequestMapping(value = "/ir_ver_comentario", method = RequestMethod.GET)
    public String doVerComentario(@RequestParam("idPelicula") String idPelicula, Model model) {
        final Collection<Comentario> comentarios = comentarioService.getAllComentarios(KeyFactory.stringToKey(idPelicula));
        model.addAttribute("comentarios", comentarios);
        return "/ver_comentarios";
    }

    @RequestMapping(value = "/ir_num_comentarios", method = RequestMethod.GET, headers="Accept=application/json")
    public @ResponseBody String verNumComentarios(Model model) {
        final Usuario usuario = userController.getUser();
        List<Valoracion> valoraciones = valoracionDAO.getValoraciones(usuario.getUsername());
        List<Comentario> comentarios = comentarioDAO.getComentarios(usuario.getUsername());        
        return numComentJson(comentarios, valoraciones, usuario).toString();
    }

    private JSONObject numComentJson(List<Comentario> comentarios, List<Valoracion> valoraciones, Usuario usuario) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nomUser", usuario.getNombre());           
            
            if(comentarios.isEmpty()) {
                jsonObject.put("comentarios", "No has comentado");
            } else {
                jsonObject.put("numComen", comentarios.size());
                jsonObject.put("lastComen", comentarios.get(comentarios.size()-1).getContent());
                jsonObject.put("lastMovie", comentarios.get(comentarios.size()-1).getPelicula().getTitulo());
            }
            if(valoraciones.isEmpty()) {
                jsonObject.put("valoraciones", "No has votado ninguna pelicula");
            } else {
                jsonObject.put("numVal", valoraciones.size());
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(ComentarioController.class.getName());
        }
        return jsonObject;
    }
}