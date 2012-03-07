<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                            ver_pelicula('${pelicula.idString}');
                            pan_usuario();
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
                        pan_usuario();
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
<table>
    <tr>
        <td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <form action="/borrar_pelicula" method="post">
                    <input type="submit" value="Borrar" style="cursor: pointer"/>
                    <input type="hidden" name="idPelicula" value="${pelicula.idString}" />
                </form>
            </td>
            <td>
                <form>
                    <input type="button" value="Editar" onclick="editarPelis('${pelicula.idString}')" style="cursor: pointer"/>
                </form>
            </sec:authorize>
        </td>
    </tr>    
</table>                                      
<div id="apDivContenedorPelicula"> <!-- Aqui se va a introducir los datos de la pelicula -->
    <div id="apDivImagenPelicula"> <!-- Aqui va la imagen -->
        <img height="300" width="290" src='/serve?blob-key=${pelicula.imagen}'/><br/>
    </div>
    <div id="apDivTituloPelicula">
        <center><p><b><h2> <c:out value="${pelicula.titulo}"/></h2></b></p></center>
    </div>
    <div id="apDivInformacionPelicula"> <!-- Aqui va la informacion de la pelicula -->               
        <p>
            <b>Duraci&oacute;n:</b> <c:out value="${pelicula.duracion}"/> min.<br/>
            <b>Director:</b> <c:out value="${pelicula.director}"/><br/>
            <b>Categoria:</b> <c:out value="${pelicula.categoria}"/><br/>
            <b>Actores: </b>
            <c:forEach var="actor" items="${pelicula.actores}" varStatus="status">                        
                <a href="http://es.wikipedia.org/wiki/${actor}" TARGET="_blank"><c:out value="${actor}" /></a>,
            </c:forEach><br/>
            <b>Fecha estreno: </b><c:out value="${pelicula.fechEstreno}"/><br/>
            <b>Sinopsis:</b><br/> <c:out value="- ${pelicula.sinopsis}"/><br/>
        </p>
        <c:if test="${not empty pelicula.trailer}">
            <a href="http://www.youtube.com/embed/${pelicula.trailer}" target="_blank">Ver trailer</a><br/>
            <!--<iframe width="560" height="315" src="http://www.youtube.com/embed/${pelicula.trailer}" frameborder="0" allowfullscreen></iframe>-->
        </c:if>
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