<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <title>Pop Corn 2DAI</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <link rel="stylesheet" type="text/css" href="stylesheets/Estiloweb2.css" ></link>
        <script type="text/javascript" src="jQuery/js/jquery-1.4.3.min.js"></script>     
        <script type="text/javascript" src="jQuery/js/plusone.js">
            {lang: 'es'}
        </script>
        <script language="JavaScript" type="text/javascript" >
            function irface() {
                window.open("http://www.facebook.com/ProyectoPopcorn", "", "")
            }
            function irgoog() {
                window.open("https://plus.google.com/111800963186367573914","","")
            }
            function irtweet() {
                window.open("http://twitter.com/PopcornProyecto","","")
            } 
            function ver_pelicula(id_pelicula) {               
                $.get(
                "/ir_ver_pelicula",
                {
                    idPelicula: id_pelicula
                },
                function(html) {                        
                    $("#apDivGeneral").html(html);
                },
                "ajax"    
            );
            }            
            function ver_noticia(id_noticia) {               
                $.get(
                "/ir_ver_noticia",
                {
                    idNoticia: id_noticia
                },
                function(html) {                        
                    $("#apDivGeneral").html(html);
                },
                "ajax"    
            );
            }
            function verInicio() {
                $.get(
                "/ir_ver_inicio",
                {
                },
                function(html){
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function verTodasPelis() {
                $.get(
                "/ir_listar_peliculas",
                {
                },
                function(html) {
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function verTodasNoticias() {
                $.get(
                "/ir_listar_noticias",
                {
                },
                function(html) {
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function crearPelis() {
                $.get(
                "/ir_crear_pelicula",
                {                        
                },
                function(html) {
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function editarPelis(id_pelicula) {
                $.get(
                "/editar_pelicula",
                {           
                    idPelicula: id_pelicula
                },
                function(html) {
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function crearNoticias() {
                $.get(
                "/ir_crear_noticia",
                {                        
                },
                function(html) {
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function registrarUsuario() {
                $.get(
                "/ir_registrar_usuario",
                {
                },
                function(html) {
                    $("#apDivGeneral").html(html);
                },
                "ajax"
            );
            }
            function pan_usuario() {
            <c:if test="${!empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                    $.getJSON(
                    "/ir_num_comentarios",
                    {          
                    },
                    function(str) {
                        if(str) {                           
                            var com = $.parseJSON(str);
                            if(com.comentarios) {
                                $("#comen").html(com.comentarios);
                            } else {
                                $("#comen").html(com.numComen);  
                                $("#comentario").html(com.lastComen);
                                $("#peli").html(com.lastMovie);                                   
                            }
                            if(com.valoraciones) {
                                $("#val").html(com.valoraciones);
                            } else {
                                $("#val").html(com.numVal);
                            }
                            $("#user").html(com.nomUser);
                        } else {
                            alert("vamos mal");
                        }
                    }
                );
            </c:if>
                }
                $(document).ready(
            
                function() {    
                    verInicio();
                    pan_usuario();
                
                }
            );  
        </script>
    </head>
    <body style="background-color: #DDDDDD">
        <div id="apDivFondo">
            <div id="apDivMenu">
                <div id="apDivLogo">

                </div>
                <div id="apDivBotones">
                    <table>
                        <tr>
                            <div class="invertedshiftdown">
                                <ul>                             
                                    <li><a title="Pagina de inicio" onclick="verInicio()" style="cursor:pointer">Inicio</a></li>
                                    <li><a title="Ir a ver peliculas" onclick="verTodasPelis()" style="cursor:pointer">Listar Peliculas</a></li>                                    
                                    <li><a title="Ir a ver noticias" onclick="verTodasNoticias()" style="cursor:pointer">Listar Noticias</a></li>                                    
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <li><a title="Ir a crear peliculas" onclick="crearPelis()" style="cursor:pointer">Crear Peliculas</a></li>
                                        <li><a title="Ir a crear noticias" onclick="crearNoticias()" style="cursor:pointer">Crear Noticias</a></li>
                                    </sec:authorize>
                                    <c:if test="${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                                        <li><a title="Registro de usuarios" onclick="registrarUsuario()" style="cursor:pointer">Registrar Usuario</a></li>
                                    </c:if> 
                                    <li><a href="http://foropopcorn2dai.appspot.com/spring/index" style="cursor:pointer">Foro</a></li>
                                </ul>       
                            </div>
                        </tr>
                    </table>
                </div>
                <div id="apDivMarcoFin"></div>
            </div>            
            <div id="apDivSocial">

                <div id="apDivBotonGoogle">
                    <img src="Image/gplus.png" onclick="irgoog()" style="cursor:pointer"></img>                  
                </div>
                <div id="apDivBotonFace">
                    <img src="Image/facebook.png" onclick="irface()" style="cursor:pointer"></img>                  
                </div>
                <div id="apDivBotonTwitt">
                    <img src="Image/twitter.png" onclick="irtweet()" style="cursor:pointer"></img>                  
                </div>
                <div id="apDivGog1">
                    <g:plusone href="http://popcorn2dai.appspot.com/"></g:plusone>
                </div>
                <div id="apDivLogoAppEngine">
                    <img src="Image/appengine-noborder-120x30.gif" style="top: 10px;"></img>
                </div>
                <div id="cse-search-form" style="width: 400px;">Cargando</div>

                <script src="jQuery/js/jsapi.js" type="text/javascript"></script>
                <script type="text/javascript"> 
                    google.load('search', '1', {language : 'es', style : "stylesheets/minimalist.css"  });
                    google.setOnLoadCallback(function() {
                        var customSearchControl = new google.search.CustomSearchControl('000154107945480710813:ylhizvrhsyc');
                        customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);
                        var options = new google.search.DrawOptions();
                        options.enableSearchboxOnly("http://www.google.es/cse?cx=000154107945480710813:ylhizvrhsyc", null, true);
                        customSearchControl.draw('cse-search-form', options);
                    }, true);
                </script>

            </div>
            <div id="apDivLogin">
                <div id="apDivInicioSesion">
                    <jsp:include page="/ir_ver_login"/>
                </div>
                <div id="apDivFecha">
                    <script>
                        var meses = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
                        var diasSemana = new Array("Domingo","Lunes","Martes","Mi&eacute;coles","Jueves","Viernes","S&aacute;bado");
                        var f = new Date();
                        document.write(diasSemana[f.getDay()] + ", " + f.getDate() + " de "+ meses[f.getMonth()] + " de " + f.getFullYear());
                    </script>
                </div>
            </div>
            <div id="apDivGeneral">
            </div>
            <div id="apDivPanel">
                <div id="apDivPanUsuario">
                    <div id="apDivNombrePanUsuario">Panel de usuario</div>
                    <c:choose>
                        <c:when test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <strong>
                                    Usuario Administrador.
                                </strong>
                            </sec:authorize>
                            <strong>Usuario:</strong> <span id="user"></span><br/>
                            <strong>Comentarios:</strong> <span id="comen"></span><br/>
                            <strong>Valoraciones:</strong> <span id="val"></span><br/>
                            <strong>Ultimo comentario:</strong> <span id="comentario"></span><br/>
                            <strong>Ultima pelicula comentada:</strong><br/> <span id="peli"></span><br/>
                        </c:when>
                        <c:otherwise>
                            <br/><strong>Registrate o logueate para ver tus estadisticas.<br/>&nbsp;</strong>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${!empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal and usuario.tipoRol eq 'Usuario'}">
                    <div id="apDivNovedades">
                        <div id="apDivNombreNovedades">Categorias Favoritas</div>

                        <ul id="galeria">
                            <style>
                                #slider {width: 216px; height: 256px;left: -40px; padding:0; top: -16px; border-radius: 5px;}
                                #slider img {width: 216px; height: 256px; padding: 0; margin:0; border:0; border-radius: 5px;}
                                #slider .clicker a {width: 11px; height: 11px; background: #fff; margin-right: 2px; border-radius: 5px; -moz-border-radius: 5px;}
                                #slider .clicker a.active {background: #ff0;}
                            </style>
                            <script src="jQuery/js/jquery.hslide.min.js"></script> 
                            <script>var $s = jQuery.noConflict();</script>
                            <script>
                                $s(function(){
                                    $s('#slider').hslide();
                                });
                            </script>
                            <div id="slider">
                                <c:forEach var="pelicula" items="${peliculas}" varStatus="status">
                                    <div>
                                        <a onclick="ver_pelicula('${pelicula.idString}')" style="cursor:pointer">
                                            <img src='/serve?blob-key=${pelicula.imagen}' alt="#" title="${pelicula.titulo}"></img> </a>
                                        <!-- TAMBIEN SE PUEDEN COLOCAR VIDEO DE YOUTUBE
                                    <div style="background-color: #000;">
                                    <iframe width="294" height="220" src="http://www.youtube.com/embed/rdNdmc83xe4" frameborder="0" allowfullscreen style="margin: 10px 25px;">
                                    </iframe></div>
                                        -->
                                    </div>
                                </c:forEach>
                            </div>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
