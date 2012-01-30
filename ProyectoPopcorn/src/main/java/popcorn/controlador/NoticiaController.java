package popcorn.controlador;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.persistence.Noticia;
import popcorn.service.NoticiaService;

@Controller
@SessionAttributes({"usuario"})
public class NoticiaController {
    
    private NoticiaService noticiaService;
            
    @Autowired
    BlobstoreService blobstoreService;
    
    @Autowired 
    HttpServletRequest req;
    
    @Autowired
    @Required
    public void setNoticiaService(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }
        
    @RequestMapping(value = "/ir_crear_noticia", method = RequestMethod.GET)
    public String doShowCrearNoticia() {
        return "/crear_noticia";
    }    
     
    @RequestMapping(value = "/ir_listar_noticias", method = RequestMethod.GET)
    public String doIrNoticias(Model model) {
        final Collection<Noticia> noticias = noticiaService.getAllNoticias();
        model.addAttribute("noticias",noticias);
        return "/listar_noticias";
    }
        
    @RequestMapping(value = "/ir_ver_noticia", method = RequestMethod.GET)
    public String doVerNoticia(@RequestParam("idNoticia") String idNoticia, Model model) { 
        final Noticia noticia = noticiaService.getNoticia(KeyFactory.stringToKey(idNoticia));
        model.addAttribute("noticia", noticia);  
        return "/ver_noticia";
    }
        
    @RequestMapping(value = "/crear_noticia_nueva", method = RequestMethod.POST)
    public String doCrearNoticia(@RequestParam("titulo") String titulo,@RequestParam("contenido")String contenido) {
        final Date fecha = new Date();
        final Noticia noticia = new Noticia(titulo, contenido, fecha);
        
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKeyOutside = blobs.get("imagen1");        
        if (blobKeyOutside != null) {
            noticia.setImagen(blobKeyOutside.getKeyString());
            noticiaService.create(noticia);
        System.out.println("Noticia creada");
        } else {
            System.out.println("Imagen nula");
        }
        
        return "redirect:/inicio";
    }
}