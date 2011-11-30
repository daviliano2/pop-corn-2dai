<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="imagetoolbar" content="no" />
        <title>Mostrar Peliculas</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script>
                !window.jQuery && document.write('<script src="jquery-1.4.3.min.js"><\/script>');
        </script>
        <script type="text/javascript" src="./fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
        <script type="text/javascript" src="./fancybox/jquery.fancybox-1.3.4.pack.js"></script>
        <link rel="stylesheet" type="text/css" href="./fancybox/jquery.fancybox-1.3.4.css" media="screen" />
        <link rel="stylesheet" href="style.css" />
        <script type="text/javascript">
            $(document).ready(function() {
                			
                $("a[rel=example_group]").fancybox({
                    'transitionIn'	: 'none',
                    'transitionOut'	: 'none',
                    'titlePosition'     : 'over',
                    'titleFormat'	: function(title, currentArray, currentIndex, currentOpts) {
                        return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
                    }
                   
                });
                 Response.Redirect("/ir_ver_pelicula");
            });
        </script>
    </head>
    <body>
        <form action="/inicio">
            <input type="submit" value="INICIO"/>
        </form>
        <div id="content">
            <p>
                <c:forEach var="pelicula" items="${peliculas}" varStatus="status">  
                    <a rel="example_group" href="http://localhost:8888/serve?blob-key=${pelicula.imagen}" title="${pelicula.titulo}">
                       <!--<img alt="" src="http://localhost:8888/serve?blob-key=${pelicula.imagen}"  />-->aki
                    </a>
                </c:forEach>
            </p>
        </div>
        <p>
            <a rel="example_group" href="././Image/aww-yeah.png" title="" >Pelis</a>

            <a rel="example_group" href="././Image/descarga.jpg" title=""></a>

            <a rel="example_group" href="././Image/like-a-sir.jpg" title=""></a>
        </p>
        <c:forEach var="pelicula" items="${peliculas}" varStatus="status">  
                    <img rel="example_group" src="http://localhost:8888/serve?blob-key=${pelicula.imagen}" title="${pelicula.titulo}"/>
                      
                                    </c:forEach>
        <!--
    <form action="/inicio">
        <input type="submit" value="INICIO"/>
    </form>-->
        <c:forEach var="pelicula" items="${peliculas}" varStatus="status">           
            <form action="/ir_ver_pelicula" method="get">               
                <input type="hidden" value="${pelicula.idString}" name="idPelicula"/>
                <input type="submit" value="${pelicula.titulo}"/>
            </form><br/>
        </c:forEach>

    </body>
</html>