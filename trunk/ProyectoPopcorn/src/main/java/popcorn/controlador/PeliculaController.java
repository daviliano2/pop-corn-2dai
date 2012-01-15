
package popcorn.controlador;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import popcorn.persistence.Pelicula;
import popcorn.service.PeliculaService;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.persistence.Categoria;
import popcorn.service.CategoriaService;

@Controller
@SessionAttributes({"usuario"})
public class PeliculaController {
    
    private PeliculaService peliculaService;
    private CategoriaService categoriaService;
            
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
    
    @RequestMapping(value = "/ir_crear_pelicula", method = RequestMethod.GET)
    public String doShowCrearPelicula(Model model) {
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        //System.out.println(categorias.toString());
        model.addAttribute("categorias",categorias);
        return "/crear_pelicula";
    }    
     
    @RequestMapping(value = "/ir_listar_peliculas", method = RequestMethod.GET)
    public String doIrPeliculas(Model model) {
        /*String html ="<div id=\"apDivGaleria\">";
        html += */
        final Collection<Categoria> categorias = categoriaService.getAllCategorias();
        //System.out.println(categorias.toString());
        final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        model.addAttribute("categorias",categorias);
        model.addAttribute("peliculas",peliculas);
        //System.out.println("AQUI IrPeliculas 1: " + peliculas);
        //return verPelisJson(categorias, peliculas).toString();
        return "/listar_peliculas";
    }
        
    @RequestMapping(value = "/ir_ver_pelicula", method = RequestMethod.GET)
    public String doVerPelicula(@RequestParam("idPelicula") String idPelicula, Model model) { 
        final Pelicula pelicula = peliculaService.getPelicula(KeyFactory.stringToKey(idPelicula));
        model.addAttribute("pelicula", pelicula);  
        return "/ver_pelicula";
    }
        
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String doCrearPelicula(@RequestParam("titulo") String titulo,@RequestParam("sinopsis")String sinopsis,
    @RequestParam("duracion") int duracion,@RequestParam("categoria") String categoria,@RequestParam("actores") String actores,
    @RequestParam("director") String director) {
        
        final Pelicula pelicula = new Pelicula(titulo, sinopsis, duracion, categoria, director);
        
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
}