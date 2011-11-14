<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        </meta>
        <title>Project PopCorn - Peliculas</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/Estiloweb.css">
        </link>
    </head>
    <body>

        <div id="apDivFondo" style="background: url('Image/popcorn.jpg');"> <!-- Div principal, contiene la imagen del fondo -->
            <div id="apDivMenu"> <!-- Aqui se encuentra el logo y el menu -->
                <script language="JavaScript" type="text/javascript">
                    document.write('Esta p&aacute;gina est&aacute; optimizada para una resoluci&oacute;n de 1366 x 768. Tu pantalla tiene una resoluci&oacute;n de ' + screen.width + ' x ' + screen.height + '.')
                </script>
                <p style="text-align:center">logo y menu. Elige la pelicula</p>
            </div>
            <div id="apDivContenedorPelicula"> <!-- Aqui se va a introducir los daots de la pelicula -->
                <div id="apDivImagenPelicula"> <!-- Aqui va la imagen -->
                    <c:forEach var="pelicula" items="${pelicula}" varStatus="status">
                        <img src="./Image/<c:out value="${pelicula.imagen}"/>" height="290" width="300" style="text-align: center"></img><br/>
                    </div>
                    <div id="apDivInformacionPelicula"> <!-- Aqui va la informacion de la pelicula -->
                        <center><p><b><h2> <c:out value="${pelicula.titulo}"/></h2></b></p></center>                 
                        <p><b>Duraci&oacute;n:</b> <c:out value="${pelicula.duracion}"/> min.<br/>
                            <b>Director:</b> <c:out value="${pelicula.director}"/><br/>
                            <b>Actores:</b></p> 
                        <ul>
                            <c:forEach var="actor" items="${pelicula.actores}" varStatus="status">    
                                <li>${actor}</li>
                            </c:forEach>
                        </ul>

                    </div>  
                    <div id="apDivValoracion">
                        <strong>Tu valoracion:</strong><br/>
                        <form action="/valorar" method="get" >
                            <select name="valoracion">
                                <option value="Puntuacion" selected="selected"></option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <input type="hidden" value="${pelicula.idString}" name="idPelicula"/>
                            <input type="submit" value="vota" />
                        </form>
                    </div>
                </div>
                <div id="apDivSinopsis">
                    <jsp:include page="/ir_ver_valoraciones"></jsp:include>               
                    <br/>
                   
                    <p><b>Sinopsis</b></p>
                    <div id="apDiv8">
                        <p style="text-align: justify;">
                            <c:out value="${pelicula.sinopsis}"/><br/>
                        </p>
                    </div>
                </div>                   
            </c:forEach>

            <div id="apDiv4">
                <div id="apDiv5">

                    <p>Introduce tu comentario sobre la pelicula: </p>
                    <form action="/comentar" method="post">
                        <textarea name="content" rows="5" cols="70"></textarea>
                        <input type="submit" value="Comentar" />
                        <input type="reset" value="Limpiar" />
                    </form>
                </div>
            </div>
            <div id="apDiv6">
                <jsp:include page="/ir_ver_comentario"></jsp:include>               

            </div>

        </div>
    </body>
</html>