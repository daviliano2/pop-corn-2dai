<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>


<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<c:choose>
    <c:when test="${!empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal and usuario.tipoRol eq 'Administrador'}">
    <script type="text/javascript" src="jQuery/js/calendar.js"></script>
    <link rel="stylesheet" type="text/css" href="stylesheets/calendar.css" ></link>
    <div style="width:650px;height: auto;margin-left: 50px;">
        <form action="<%=blobstoreService.createUploadUrl("/crear_noticia_nueva")%>" method="post" enctype="multipart/form-data">
            <p> Titulo noticia: <input name="titulo" type="text" placeholder="Titulo noticia"></input><br/> 
                Contenido: <br/><textarea name="contenido" rows="5" cols="70" placeholder="Introdude la noticia"></textarea><br/>
                Fuente noticia: <br/><input type="text" name="fuente" placeholder="Direccion de la fuente"></input><br/>
                Trailer: <input type="text" name="trailer" placeholder="Enlace de Youtube"></input><br/>
                Imagen: <input type="file" name="imagen1" id="imagen1"></input>
            </p>
            <p align="center">
                <input type="reset" value="Limpiar" />
                <input type="submit" value="Crear" /> 
            </p>
        </form>
    </div>
</c:when>
    <c:otherwise>
        <img src="http://fc04.deviantart.net/fs70/f/2011/172/2/f/no__meme_by_dylrocks95-d3jlcc7.png"/>
        <br/>
        <strong>NO TIENES AUTORIZACION PARA ACCEDER A ESTE SITIO</strong>        
        <form action="/inicio">
            <input type="submit" value="INICIO"/>
        </form>
    </c:otherwise>
</c:choose>