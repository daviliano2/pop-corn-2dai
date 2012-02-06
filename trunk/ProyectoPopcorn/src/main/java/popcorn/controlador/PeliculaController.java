package popcorn.controlador;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import popcorn.persistence.Pelicula;
import popcorn.service.PeliculaService;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.dao.PeliculaDAO;
import popcorn.persistence.Categoria;
import popcorn.persistence.Noticia;
import popcorn.service.CategoriaService;
import popcorn.service.NoticiaService;

@Controller
@SessionAttributes({"usuario"})
public class PeliculaController {
    
    private PeliculaService peliculaService;
    private CategoriaService categoriaService;
    private NoticiaService noticiaService;
    private PeliculaDAO peliculaDAO;
            
    @Autowired
    BlobstoreService blobstoreService;
    
    @Autowired 
    HttpServletRequest req;
    
    @Autowired
    @Required
    public void setPeliculaService(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }
    
    @Autowired
    @Required
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    @Autowired
    @Required
    public void setNoticiaService(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }
    
    @Autowired
    @Required
    public void setPeliculaDAO(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }
    
    @RequestMapping(value = "/ir_crear_pelicula", method = RequestMethod.GET)
    public String doShowCrearPelicula(Model model) {
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        //System.out.println(categorias.toString());
        model.addAttribute("categorias",categorias);
        return "/crear_pelicula";
    }    
     
    @RequestMapping(value = "/ir_listar_peliculas", method = RequestMethod.GET)
    public String doIrPeliculas(Model model) {
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        model.addAttribute("categorias",categorias);
        model.addAttribute("peliculas",peliculas);
        return "/listar_peliculas";
    }
        
    @RequestMapping(value = "/ir_ver_pelicula", method = RequestMethod.GET)
    public String doVerPelicula(@RequestParam("idPelicula") String idPelicula, Model model) { 
        final Pelicula pelicula = peliculaService.getPelicula(KeyFactory.stringToKey(idPelicula));
        model.addAttribute("pelicula", pelicula);  
        return "/ver_pelicula";
    }
    
    @RequestMapping(value = "/ir_ver_inicio", method = RequestMethod.GET)
    public String doVerInicioPelicula(Model model) {
        List<Pelicula> pelis = peliculaDAO.getPeliculas();
        model.addAttribute("pelis", pelis);
        //final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        final Collection<Noticia> noticias = noticiaService.getOrderNoticias();
        //model.addAttribute("peliculas",peliculas); 
        model.addAttribute("noticias", noticias);
        return "/novedades_inicio";
    }
        
    @RequestMapping(value = "/crear_peli_nueva", method = RequestMethod.POST)
    public String doCrearPelicula(@RequestParam("titulo") String titulo,@RequestParam("sinopsis")String sinopsis,
    @RequestParam("duracion") int duracion,@RequestParam("categoria") String categoria,@RequestParam("actores") String actores,
    @RequestParam("director") String director, @RequestParam("fecha") String fecha, @RequestParam("trailer") String trailer ) {
        
        final Pelicula pelicula = new Pelicula(titulo, sinopsis, duracion, categoria, director,fecha,trailer);
        
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKeyOutside = blobs.get("imagen");
        
        final List<String> actor = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(actores,",");
	while(tokens.hasMoreTokens()) {            
            String actrs = tokens.nextToken();
            actrs.trim();
            actor.add(actrs);
        }
        pelicula.setActores(actor);
        if (blobKeyOutside != null) {
            pelicula.setImagen(blobKeyOutside.getKeyString());
            peliculaService.create(pelicula);
            System.out.println("Pelicula creada");
        } else {
            System.out.println("Imagen nula");
        }
        
        return "redirect:/inicio";
    }
    
    @RequestMapping(value = "/editar_pelicula", method = RequestMethod.GET)
    public String doShowEditarPelicula(@RequestParam("idPelicula") String idPelicula, Model model) {
        final Pelicula pelicula = peliculaService.getPelicula(KeyFactory.stringToKey(idPelicula));
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        model.addAttribute("categorias",categorias);
        model.addAttribute("pelicula", pelicula);
        return "/edicion_pelicula";
    }
    
    @RequestMapping(value = "/editar_peli", method = RequestMethod.GET)
    public String doEditarPelicula(@RequestParam("idPelicula") String idPelicula,
        @RequestParam("titulo") String titulo,@RequestParam("sinopsis")String sinopsis,
        @RequestParam("duracion") int duracion,@RequestParam("categoria") String categoria,@RequestParam("actores") String actores,
        @RequestParam("director") String director, @RequestParam("fecha") String fecha, @RequestParam("trailer") String trailer) {
        //System.out.println("AKI peliControler editarPeli 1");
        final Pelicula pelicula = new Pelicula(titulo, sinopsis, duracion, categoria, director,fecha,trailer);
        final List<String> actor = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(actores,",");
	while(tokens.hasMoreTokens()) {            
            String actrs = tokens.nextToken();
            actrs.trim();
            actor.add(actrs);
        }
        pelicula.setActores(actor);
        peliculaService.editarPeli(KeyFactory.stringToKey(idPelicula), pelicula);
        return "redirect:/inicio";
    }
    
    @RequestMapping(value = "/borrar_pelicula", method = RequestMethod.POST)
    public String doBorrarPelicula(@RequestParam("idPelicula") String idPelicula) {
        peliculaService.borrarPeli(KeyFactory.stringToKey(idPelicula));
        return "redirect:/inicio";
    }
    
    @RequestMapping(value = "/crear_categoria", method = RequestMethod.POST)
    public String doCrearCategoria(@RequestParam("categoria") String categoria) {
            Categoria cat = new Categoria();
            cat.setNombre(categoria);
            categoriaService.create(cat);
        return "redirect:/inicio";
    }
}