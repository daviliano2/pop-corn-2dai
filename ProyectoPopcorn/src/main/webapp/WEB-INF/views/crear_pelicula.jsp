<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=ISO-8859-5" language="java" %>
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

<script type="text/javascript">
    $(document).ready(
    function() {
        $("#botonCrearCat").click(
        function() {                               
            $.getJSON(
            "/crear_categoria",
            {
                "categoriaNueva":$("#categoriaNueva").val()
            },
            function(str) {
                if(str) {
                    //alert(str);
                    var categorias = $.parseJSON(str); //ESTO SERIA TOTALMENTE ASINCRONO PERO NO GUARDA EL ULTIMO VOTO
                    $("#categoria").html(categorias.categoriasNuevas); //SI VAS A INICIO Y VUELVES, HABRIA QUE DARLE UN PAR DE VUELTAS..  
                    //window.location = "/ir_ver_pelicula?idPelicula="+$("#idPelicula").val();                                
                    //window.location = "/inicio";
                    crearPelis(); //CON ESTO LLAMA A LA FUNCION QUE HAY EN INICIO JSP Y SE RECARGA SOLO                    
                } else {
                    alert("else");
                }
            }

        );                                
        }        
    );
    }      
);
    
</script>
<c:choose>
    <c:when test="${!empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal and usuario.tipoRol eq 'Administrador'}">

        <script type="text/javascript" src="jQuery/js/calendar.js"></script>
        <link rel="stylesheet" type="text/css" href="stylesheets/calendar.css" ></link>
        <div style="width:650px;height: auto;margin-left: 50px;">
            <form action="<%=blobstoreService.createUploadUrl("/crear_peli_nueva")%>" method="post" enctype="multipart/form-data">
                <p> Nombre de la pel&iacute;cula: <input name="titulo" type="text" placeholder="Titulo pelicula"></input><br/> 
                    Sinopsis: <br/><textarea name="sinopsis" rows="5" cols="70" placeholder="Introdude la sinopsis"></textarea><br/>
                    Duraci&oacute;n: <input type="text" name="duracion" placeholder="Duracion en minutos"></input><br/>
                    Actores: <br/><textarea name="actores" rows="5" cols="70" placeholder="Actores separados por comas"></textarea><br/>
                    Director: <input type="text" name="director" placeholder="Director"></input><br/>
                    Fecha de estreno: <input size="10" id="fc_peli" type="text" name="fecha" title="MM/DD/YYYY" onClick="displayCalendar(this);"></input><br/>
                    Categor&iacute;a: 
                    <select name="categoria">
                        <option value="default" selected="selected">Elige una categoria</option>
                        <c:forEach var="categoriab" items="${categorias}" varStatus="status">
                            <option value="${categoriab.nombre}"><c:out value="${categoriab.nombre}" /></option>
                        </c:forEach>
                    </select><br/>
                    Trailer: <input type="text" name="trailer" placeholder="Enlace de Youtube"></input><br/>
                    Imagen: <input type="file" name="imagen" id="imagen"></input>
                </p>
                <p align="center">
                    <input type="reset" value="Limpiar" />
                    <input type="submit" value="Crear" /> 
                </p>
            </form>
        </div>
        <div style="margin-left: 300px; margin-top: -132px;">
            <form id="crearCatForm">
                <input type="text" placeholder="Nueva Categoria" name="categoriaNueva" id="categoriaNueva"/>
                <input type="button" value="Crear Categoria" id="botonCrearCat" style="cursor:pointer"/>
            </form>
        </div>
        <div style="margin-left: 50px;height: 80px;"></div>
        <br/>

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