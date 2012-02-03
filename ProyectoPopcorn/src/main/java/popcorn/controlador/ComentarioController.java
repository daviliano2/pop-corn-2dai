package popcorn.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.ArrayList;
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
import popcorn.dao.PeliculaDAO;
import popcorn.dao.ValoracionDAO;
import popcorn.persistence.Pelicula;
import popcorn.persistence.Usuario;
import popcorn.persistence.Valoracion;
import popcorn.service.PeliculaService;

@Controller
@SessionAttributes({"usuario"})
public class ComentarioController {

    private ComentarioService comentarioService;
    private PeliculaService peliculaService;
    private UsuarioController userController;
    private ComentarioDAO comentarioDAO;
    private ValoracionDAO valoracionDAO;
    private PeliculaDAO peliculaDAO;
    
    @Autowired
    @Required
    public void setPeliculaDAO(final PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

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
    
    @Autowired
    @Required
    public void setPeliculaService(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @RequestMapping(value = "/comentar", method = RequestMethod.GET)
    public @ResponseBody String doCrearComentario(@RequestParam("comenta") String content,
            @RequestParam("idPelicula") String idStringPelicula) {
        //System.out.println("AKI comentarioController, doCrearComentario 1");
        final Usuario usuario = userController.getUser();
        final Date fecha = new Date();
        final Comentario comentario;
        if (usuario == null) {
            comentario = new Comentario(content, fecha);
        } else {
            comentario = new Comentario(usuario, content, fecha);
        }
        comentarioService.create(comentario, KeyFactory.stringToKey(idStringPelicula));
        return datosComentario(KeyFactory.stringToKey(idStringPelicula)).toString();
    }

    private JSONObject datosComentario(Key idPelicula) {
        //System.out.println("AKI comentarioController, datosComentario 0 ");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idPeli", idPelicula);
            jsonObject.put("comentarios", comentarioService.getAllComentarios(idPelicula));
            //System.out.println("AKI comentarioController, datosComentario jsonObject = " + jsonObject.get("comentarios"));
        } catch (JSONException ex) {
            Logger.getLogger(ComentarioController.class.getName());
        }
        return jsonObject;
    }

    @RequestMapping(value = "/ir_ver_comentario", method = RequestMethod.GET)
    public String doVerComentario(@RequestParam("idPelicula") String idPelicula, Model model) {
        Pelicula peli = peliculaService.getPelicula(KeyFactory.stringToKey(idPelicula));
        final Collection<Comentario> comentarios = comentarioDAO.getComentariosPeli(peli.getTitulo());
        model.addAttribute("numTotal", comentarios.size());
        model.addAttribute("comentarios", comentarios);
        return "/ver_comentarios";
    }    
    
    @RequestMapping(value = "/ir_num_comentarios", method = RequestMethod.GET, headers = "Accept=application/json")
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

            if (comentarios.isEmpty()) {
                jsonObject.put("comentarios", "No has comentado");
            } else {
                jsonObject.put("numComen", comentarios.size());
                jsonObject.put("lastComen", comentarios.get(comentarios.size() - 1).getContent());
                jsonObject.put("lastMovie", comentarios.get(comentarios.size() - 1).getPelicula().getTitulo());
            }
            if (valoraciones.isEmpty()) {
                jsonObject.put("valoraciones", "No has votado ninguna pelicula");
            } else {
                jsonObject.put("numVal", valoraciones.size());
            }

        } catch (JSONException ex) {
            Logger.getLogger(ComentarioController.class.getName());
        }
        return jsonObject;
    }

    @RequestMapping(value = "/comprobar_autor_comentario", method = RequestMethod.GET)
    public @ResponseBody
    String doComprobarAutorComentario(@RequestParam("idComentario") String idComentario,
            @RequestParam("autor") String autor) {
        final Usuario usuario = userController.getUser();
        if (usuario.getUsername().compareTo(autor) == 0) {
            return "ok";
        } else {
            return "notOk";
        }
    }

    @RequestMapping(value = "/borrar_comentario", method = RequestMethod.POST)
    public String doBorrarComentario(@RequestParam("idComentario") String idComentario) {
        comentarioService.borrarComentario(KeyFactory.stringToKey(idComentario));
        return "redirect:/inicio";
    }
    
    @RequestMapping(value = "/borrar_comentario2", method = RequestMethod.POST)
    public String doBorrarComentario2(@RequestParam("idComentario") String idComentario) {
        comentarioService.borrarComentario2(KeyFactory.stringToKey(idComentario));
        return "redirect:/inicio";
    }
}