<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
                                        var valora = $.parseJSON(str); //ESTO SERIA TOTALMENTE ASINCRONO PERO NO GUARDA EL ULTIMO VOTO
                                        $("#media").html(valora.media); //SI VAS A INICIO Y VUELVES, HABRIA QUE DARLE UN PAR DE VUELTAS..  
                                        //window.location = "/ir_ver_pelicula?idPelicula="+$("#idPelicula").val();
                                        ver_pelicula('${pelicula.idString}');
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
                    $("#botonComentar").click(
                        function() {                               
                            $.getJSON(
                                "/comentar",
                                {
                                    "idPelicula":$("#idPeli").val(),
                                    "comenta":$("#comenta").val()
                                },
                                function(str) {
                                    if(str) {
                                        //alert(str);
                                        var comentar = $.parseJSON(str); //ESTO SERIA TOTALMENTE ASINCRONO PERO NO GUARDA EL ULTIMO VOTO
                                        $("#comentarios").html(comentar.comentarios); //SI VAS A INICIO Y VUELVES, HABRIA QUE DARLE UN PAR DE VUELTAS..  
                                        //window.location = "/ir_ver_pelicula?idPelicula="+$("#idPelicula").val();                                        
                                        //window.location = "/inicio";
                                        ver_pelicula('${pelicula.idString}'); //CON ESTO LLAMA A LA FUNCION QUE HAY EN INICIO JSP Y SE RECARGA SOLO
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

        <div id="apDivContenedorPelicula"> <!-- Aqui se va a introducir los datos de la pelicula -->
            <div id="apDivImagenPelicula"> <!-- Aqui va la imagen -->
                <img height="300" width="290" src='/serve?blob-key=${pelicula.imagen}'></img><br/>
            </div>
            <div id="apDivTituloPelicula">
                <center><p><b><h2> <c:out value="${pelicula.titulo}"/></h2></b></p></center>
            </div>
            <div id="apDivInformacionPelicula"> <!-- Aqui va la informacion de la pelicula -->               
                <p>
                    <b>Duraci&oacute;n:</b> <c:out value="${pelicula.duracion}"/> min.<br/><br/>
                    <b>Director:</b> <c:out value="${pelicula.director}"/><br/><br/>
                    <b>Categoria:</b> <c:out value="${pelicula.categoria}"/><br/><br/>
                    <b>Actores: </b>
                    <c:forEach var="actor" items="${pelicula.actores}" varStatus="status">                        
                        <c:out value="${actor}" />, 
                    </c:forEach>
                    <br/><br/><br/>
                    <b>Sinopsis:</b><br/> <c:out value="- ${pelicula.sinopsis}"/><br/>
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
                    <input type="button" value="vota" id="botonValorar" style="cursor:pointer" />                         
                </form>   
            </div>
            <div id="apDivMediaValoracion">
                <jsp:include page="/ir_ver_valoraciones"/><br/>
            </div>
        </div>
       <!-- <div id="apDivSinopsis">
            
        </div> -->         
        <div id="apDivContenedorComentario">
            <div id="apDivComenta">
                <p>Introduce tu comentario sobre la pelicula:</p>
                <form id="comentaForm">
                    <textarea name="comenta" id="comenta" rows="5" cols="70"></textarea>
                    <input type="hidden" value="${pelicula.idString}" id="idPeli"/><br/>
                    <input type="button" value="Comentar" id="botonComentar" style="cursor:pointer"/>
                    <input type="reset" value="Limpiar" style="cursor:pointer"/>
                </form>
            </div>        
        </div>
        <div id="apDivVerComentarios">
            <div id="apDivComenColocacion">
                <jsp:include page="/ir_ver_comentario"/>
            </div>
        </div>
