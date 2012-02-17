/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.view;

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
import org.primefaces.model.UploadedFile;  
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
  
@Controller("fileUploadController")
@Scope("request")
public class FileUploadController {  
  
    private UploadedFile file;  
  
    public UploadedFile getFile() {  
        System.out.println("AQUI fileUploadController 1 getFile");
        return file;  
    }  
  
    public void setFile(UploadedFile file) { 
        System.out.println("AQUI fileUploadController 2 setFile");
        this.file = file;  
    }  
  
    public void upload() {  
        System.out.println("AQUI fileUploadController 3 upload");
        FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}  