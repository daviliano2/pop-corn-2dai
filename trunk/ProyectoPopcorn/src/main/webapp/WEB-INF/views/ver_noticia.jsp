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
                    $("#botonComentar2").click(
                        function() {                               
                            $.getJSON(
                                "/comentar_noticia",
                                {
                                    "idNoticia":$("#idNoticia").val(),
                                    "comenta2":$("#comenta2").val()
                                },
                                function(str) {
                                    if(str) {
                                        //alert(str);
                                        var comentar2 = $.parseJSON(str); //ESTO SERIA TOTALMENTE ASINCRONO PERO NO GUARDA EL ULTIMO VOTO
                                        $("#comentarios").html(comentar2.comentarios); //SI VAS A INICIO Y VUELVES, HABRIA QUE DARLE UN PAR DE VUELTAS..  
                                        //window.location = "/ir_ver_pelicula?idPelicula="+$("#idPelicula").val();                                        
                                        //window.location = "/inicio";
                                        ver_noticia('${noticia.idString}'); //CON ESTO LLAMA A LA FUNCION QUE HAY EN INICIO JSP Y SE RECARGA SOLO
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

        <div>
            <div>
                <img height="300" width="290" src='/serve?blob-key=${noticia.imagen}'></img><br/>
            </div>
            <div>
                <center><p><b><h2> <c:out value="${noticia.titulo}"/></h2></b></p></center>
            </div>
            <div>               
                <p>
                    <b>Contenido:</b> <c:out value="${noticia.contenido}"/><br/><br/>
                </p>
            </div>             
        </div>
                
        <div >
            <div >
                <p>Introduce tu comentario sobre la noticia:</p>
                <form id="comentaForm2">
                    <textarea name="comenta2" id="comenta2" rows="5" cols="70"></textarea>
                    <input type="hidden" value="${noticia.idString}" id="idNoticia"/><br/>
                    <input type="button" value="Comentar" id="botonComentar2" style="cursor:pointer"/>
                    <input type="reset" value="Limpiar" style="cursor:pointer"/>
                </form>
            </div>        
        </div>
        <div>
            <div>
                <jsp:include page="/ir_ver_comentario"/>
            </div>
        </div>
