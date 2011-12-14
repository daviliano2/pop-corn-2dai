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
        <script type="text/javascript" src="jQuery/js/jquery-1.4.3.min.js"></script>
        <script type="text/javascript">
            $(document).ready(
            function() {
                $("#botonValorar").click(
            
                function() {   
                    <c:choose>
                        <c:when test="${!empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                    $.getJSON(
                    "/valorar",
                    {
                        "idPelicula":$("#idPelicula").val(),
                        "valoracion":$("#valoracion").val()
                    },
                    function(str) {
                        if(str) {
                            //alert($("#idPelicula").val());
                            window.location = "/ir_ver_pelicula?idPelicula="+$("#idPelicula").val();
                                        
                        } else {
                            alert("else");
                        }
                    }
                );
                            </c:when>
                        <c:otherwise>
                            alert("Conectate para votar");
                        </c:otherwise>
                    </c:choose>
                }                
            );
            }
        );
        </script>
    </head>

    <body>

        <div id="apDivFondo" style="background: url('Image/popcorn.jpg');"> <!-- Div principal, contiene la imagen del fondo -->
            <div id="apDivMenu" > <!-- Aqui se encuentra el logo y el menu -->
                <script language="JavaScript" type="text/javascript" >
                    document.write('Esta p&aacute;gina est&aacute; optimizada para una resoluci&oacute;n de 1366 x 768. Tu pantalla tiene una resoluci&oacute;n de ' + screen.width + ' x ' + screen.height + '.')
                </script>
                <form action="/inicio">
                    <input type="submit" value="INICIO"></input>
                </form>

            </div>

            <div id="apDivContenedorPelicula"> <!-- Aqui se va a introducir los datos de la pelicula -->
                <div id="apDivImagenPelicula"> <!-- Aqui va la imagen -->
                    <img height="290" width="300" src='http://localhost:8888/serve?blob-key=${pelicula.imagen}'></img><br/>
                </div>
                <div id="apDivTituloPelicula">
                    <center><p><b><h2> <c:out value="${pelicula.titulo}"/></h2></b></p></center>
                </div>
                <div id="apDivInformacionPelicula"> <!-- Aqui va la informacion de la pelicula -->               
                    <p>
                        <b>Duraci&oacute;n:</b> <c:out value="${pelicula.duracion}"/> min.<br/>
                        <b>Director:</b> <c:out value="${pelicula.director}"/><br/>
                        <b>Categoria:</b> <c:out value="${pelicula.categoria}"/><br/>
                        <b>Actores:</b><br/>
                        <c:forEach var="actor" items="${pelicula.actores}" varStatus="status">    
                            &nbsp;&nbsp;&nbsp;&nbsp;&bull; <c:out value="${actor}" /><br/>
                        </c:forEach>
                    </p>
                </div>

                <div id="apDivValoracion">                   
                    <strong>Tu valoracion:</strong>                    
                    <form id="formValorar">
                        <select name="valoracion" id="valoracion">
                            <option value="Puntuacion" selected="selected">Puntua</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>                          
                        <input type="hidden" value="${pelicula.idString}" id="idPelicula"/>
                        <input type="button" value="vota" id="botonValorar" />                         
                    </form>                        
                </div>
            </div>

            <div id="apDivSinopsis">
                <jsp:include page="/ir_ver_valoraciones"/><br/>
                <p><b>&nbsp;&nbsp;&nbsp;Sinopsis</b></p>
                <div id="apDivTextoSinopsis">
                    <p style="text-align: justify;">
                        <c:out value="${pelicula.sinopsis}"/><br/>
                    </p>
                </div>
            </div>                   

            <div id="apDivContenedorComentario">
                <div id="apDivComenta">
                    <c:if test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                        <p>Introduce tu comentario sobre la pelicula, 
                            ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
                            <a  href="/logout" > Desconectar</a>
                        </p>
                    </c:if>
                    <c:if test="${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                        <p>Introduce tu comentario sobre la pelicula:</p>
                    </c:if>
                    <form action="/comentar" method="post">
                        <textarea name="content" rows="5" cols="70"></textarea>
                        <input type="hidden" value="${pelicula.idString}" name="idPelicula"/>
                        <input type="submit" value="Comentar" />
                        <input type="reset" value="Limpiar" />
                    </form>
                </div>
            </div>
            <div id="apDivVerComentarios">                    
                <jsp:include page="/ir_ver_comentario"/>
            </div>

        </div>
    </body>
</html>