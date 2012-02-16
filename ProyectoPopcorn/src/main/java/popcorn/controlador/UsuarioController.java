package popcorn.controlador;

import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import popcorn.persistence.Usuario;
import popcorn.service.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import popcorn.dao.UsuarioDAO;
import popcorn.persistence.Categoria;
import popcorn.persistence.Rol;
import popcorn.service.CategoriaService;
import popcorn.service.PeliculaService;
import popcorn.service.RolService;

@Controller
@SessionAttributes({"usuario"})
public class UsuarioController {
    private UsuarioService usuarioService;
    private RolService rolService;
    private AuthenticationManager authenticationManager;
    private PeliculaService peliculaService;
    private UsuarioDAO usuarioDAO;
    private CategoriaService categoriaService;

    @Autowired
    @Required
    public void setUsuarioDAO(final UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Autowired
    @Required
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    @Required
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    @Required
    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }
    
    @Autowired
    @Required
    public void setCategoriaService (CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsuarioConectado", headers = "Accept=application/json")
    public @ResponseBody
    Usuario getUser() {
        return usuarioService.getCurrentUser();
    }

    @RequestMapping(value = "/ir_ver_login", method = RequestMethod.GET)
    public String irLogin(Model model) {
        final Collection<Rol> roles = rolService.getAllRoles();
        for (Rol rol : roles) {
            if (rol.getNombre().compareTo("ROLE_USER") == 0) {
                model.addAttribute("roles", rol);
            }
            if (rol.getNombre().compareTo("ROLE_ADMIN") == 0) {
                model.addAttribute("roles1", rol);
            }
        }
        return "/login";
    }

    @RequestMapping(value = "/ir_registrar_usuario", method = RequestMethod.GET)
    public String verRegistrarUsuario(Model model) {
        final Collection<Rol> roles = rolService.getAllRoles();
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        final int numCat = categoriaService.getNumCategorias();
        model.addAttribute("categorias",categorias);
        model.addAttribute("numero",numCat);
        for (Rol rol : roles) {
            if (rol.getNombre().compareTo("ROLE_USER") == 0) {
                model.addAttribute("roles", rol);
            }
        }
        return "/registrar_usuario";
    }
    
    @RequestMapping(value = "/ir_comprobar_usuario", method = RequestMethod.GET)
    public @ResponseBody String comprobarUsuario(@RequestParam("user") String nombreUsuario, @RequestParam("pass") String password,
                                                 @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
                                                 @RequestParam("idRol") String idRol, @RequestParam("categ") String favoritas, 
                                                 @RequestParam("rol") String tipoRol) {
        String valido = "ok";
        if (usuarioDAO.findByString(nombreUsuario) != null) {
            valido = null;
        }
        if (valido != null) {
            return crearUsuario(nombreUsuario, password, nombre, apellido, idRol, favoritas, tipoRol);
        } else {
            return valido;
        }
    }

    @RequestMapping(value = "/crear_usuario", method = RequestMethod.GET)
    public String crearUsuario(@RequestParam("user") String nombreUsuario, @RequestParam("pass") String password,
                               @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
                               @RequestParam("idRol") String idRol, @RequestParam("categ") String favoritas,
                               @RequestParam("rol") String tipoRol) {
        final List<String> fav = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(favoritas,",");
	while(tokens.hasMoreTokens()) {            
            String cat = tokens.nextToken();
            cat.trim();
            fav.add(cat);
        }
        
        usuarioService.create(nombreUsuario, password, nombre, apellido, KeyFactory.stringToKey(idRol),fav, tipoRol);
        return "redirect:/inicio";
    }

    @RequestMapping(value = "/ir_conectar_usuario", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody String conectar(@RequestParam String username,
                                         @RequestParam String password, Model model) {
        String rdo = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            authToken.setDetails(authentication.getDetails());
            Authentication newAuth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            if (newAuth.isAuthenticated()) {
                model.addAttribute("usuario", usuarioService.getCurrentUser());
            } else {
                rdo = "Error al conectar. Comprueba usuario y contraseña";
            }
        } catch (Exception unfe) {
            rdo = " ERROR " + unfe.getMessage();
        }
        return rdo;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession sesion, SessionStatus sessionStatus, @ModelAttribute("usuario") Usuario usuario) throws java.io.IOException {
        System.out.println("UsuarioController logout se está desconectando " + usuario.getUsername());
        SecurityContextHolder.getContext().setAuthentication(null);
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        sessionStatus.setComplete();

        return "redirect:index.html";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/accesoDenegado")
    public String IrAAccesodenegado() {
        return "redirect:/accessDenied.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/falloAutenticacion")
    public String IrAFalloAutenticacion() {
        return "redirect:/falloaAtenticacion.html";
    }
}
