<%-- 
    Document   : nueva_vista
    Created on : 26-nov-2011, 20:48:43
    Author     : miguel
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <link rel="stylesheet" type="text/css" href="stylesheets/Estiloweb2.css" ></link>
        <link rel="stylesheet" type="text/css" href="stylesheets/EstiloCarrusel.css" />
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
        </script>
        <script type="text/javascript" src="https://apis.google.com/js/plusone.js">
            {lang: 'es'}
        </script>
    </head>
    <body>
        <div id="apDivFondo">
            <div id="apDivMenu">
                <div id="apDivLogo">
                    
                </div>
                <div id="apDivBotones">
                    <table>
                        <tr>
                            <td>
                                <form action="/inicio">
                                    <input type="submit" value="INICIO"/>
                                </form>
                            </td>
                            <td>
                                <form action="/ir_seleccionar_peliculas">
                                    <input type="submit" value="PELICULAS"></input>                    
                                </form>
                            </td>
                            <td>
                                <form action="/ir_crear_pelicula">
                                    <input type="submit" value="CREAR PELICULA"></input>                    
                                </form>
                            </td>
                            <td>
                                <c:if test="${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                                <form action="/ir_registrar_usuario">
                                    <input type="submit" value="REGISTRO USUARIO"></input>                    
                                </form> 
                            </c:if> 
                            </td>
                        </tr>
                    </table>
                </div>
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
                <div id="cse-search-form" style="width: 400px;">Loading</div>
                <script src="//www.google.es/jsapi" type="text/javascript"></script>
                <script type="text/javascript"> 
                    google.load('search', '1', {language : 'es', style : google.loader.themes.MINIMALIST});
                    google.setOnLoadCallback(function() {
                        var customSearchControl = new google.search.CustomSearchControl('000154107945480710813:ylhizvrhsyc');
                        customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);
                        var options = new google.search.DrawOptions();
                        options.enableSearchboxOnly("http://www.google.es/cse?cx=000154107945480710813:ylhizvrhsyc", null, true);
                        customSearchControl.draw('cse-search-form', options);
                    }, true);
                </script>
                <style type="text/css">
                    
                </style>  
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
            <div id="apDivPanel">
                <div id="apDivPanUsuario">
                    <div id="apDivNombrePanUsuario">Panel de usuario</div>
                    <c:if test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
                        <form action="/getUsuarioConectado" method="get">
                        <input type="submit"/>
                        </form>
                        Usuario: <c:out value="${usuario.nombre}"></c:out><br/>
                    </c:if>
                    
                </div>
                <div id="apDivNovedades">
                    <div id="apDivNombreNovedades">Novedades de la semana</div>
                </div>
                <div id="apDivLogoAppEngine">
                    <center><img src="Image/appengine-noborder-120x30.gif" style="top: 10px;"></img></center>
                </div>
            </div>
            <div id="apDivGeneral">
                
                <div id="apDivGaleria">
                    <div id="apDivTGal">Novedades</div>
                    <ul id="galeria">
                        <c:forEach var="pelicula" items="${peliculas}" varStatus="status">
                            <li><a href="/ir_ver_pelicula?idPelicula=${pelicula.idString}"><img src='http://localhost:8888/serve?blob-key=${pelicula.imagen}' alt="#" title="${pelicula.titulo}" /></a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
