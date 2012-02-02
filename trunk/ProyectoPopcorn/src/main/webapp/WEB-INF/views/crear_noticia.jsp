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
<script type="text/javascript" src="jQuery/js/calendar.js"></script>
<link rel="stylesheet" type="text/css" href="stylesheets/calendar.css" ></link>
<div style="width:650px;height: auto;margin-left: 50px;">
    <form action="<%=blobstoreService.createUploadUrl("/crear_noticia_nueva")%>" method="post" enctype="multipart/form-data">
        <p> Titulo noticia: <input name="titulo" type="text" placeholder="Titulo noticia"></input><br/> 
            Contenido: <br/><textarea name="contenido" rows="5" cols="70" placeholder="Introdude la noticia"></textarea><br/>
            
            Imagen: <input type="file" name="imagen1" id="imagen1"></input>
        </p>
        <p align="center">
            <input type="reset" value="Limpiar" />
            <input type="submit" value="Crear" /> 
        </p>
    </form>
</div>
