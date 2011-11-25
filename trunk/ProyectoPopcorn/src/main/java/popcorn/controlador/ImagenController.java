package popcorn.controlador;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagenController {
    
    @Autowired
    BlobstoreService blobstoreService;
        
    @Autowired 
    HttpServletRequest req;
    
    @ResponseBody
    @RequestMapping(value = "/serve", method = RequestMethod.GET)
    public void doVerImagen(HttpServletResponse res) throws IOException {
        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        blobstoreService.serve(blobKey, res);
    }    

}