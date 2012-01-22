<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>


<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<div style="position:absolute;width:650px;height:500px;left: 30px;">
    <form action="<%=blobstoreService.createUploadUrl("/crear")%>" method="post" enctype="multipart/form-data">
        <p> Nombre de la pel&iacute;cula: <input name="titulo" type="text" placeholder="Titulo pelicula"></input><br/> 
            Sinopsis: <br/><textarea name="sinopsis" rows="5" cols="70" placeholder="Introdude la sinopsis"></textarea><br/>
            Duraci&oacute;n: <input type="text" name="duracion" placeholder="Duracion en minutos"></input><br/>
            Actores: <br/><textarea name="actores" rows="5" cols="70" placeholder="Actores separados por comas"></textarea><br/>
            Director: <input type="text" name="director" placeholder="Director"></input><br/>
            Fecha de estreno: <input type="date" name="fecha"/><br/>
            Categor&iacute;a: 
            <select name="categoria">
                <option value="default" selected="selected">Elige una categoria</option>
                <c:forEach var="categoriab" items="${categorias}" varStatus="status">
                    <option value="${categoriab.nombre}"><c:out value="${categoriab.nombre}" /></option>
                </c:forEach>
            </select><br/>
            Imagen: <input type="file" name="imagen" id="imagen"></input>
        </p>
        <p align="center">
            <input type="reset" value="Limpiar" />
            <input type="submit" value="Crear" /> 
        </p>
    </form>
</div>
