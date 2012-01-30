<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!--<script type="text/javascript" src="jquery-1.4.4.min.js"></script>-->
<script type="text/javascript" src="jQuery/js/simple.carousel.js"></script>
<script>var $n = jQuery.noConflict();</script>
<script >
    $n(function() {
        // example 1
        $n("ul.example1").simplecarousel({
            width:100,
            height:100,
            visible: 4,
            auto: 5000,
            vertical: true,
            pagination: true,
            next: $('.next'),
            prev: $('.prev')
        });
            
        // example 2
        $n("ul.example2").simplecarousel({
            width:700,
            height:400,
            auto: 4000,
            fade: 400,
            pagination: true
        });
    });        
</script>

<style>
    .example1 {
        margin:0;
        padding:0;
        list-style:none;
    }        
    li {
        text-align:center;
    }        
    li span {
        display:block;
        margin:5px;
        background:red;
    }        
    .next,
    .prev {
        cursor:pointer;
    }        
    .example2 {
        margin:0;
        padding:0;
        list-style:none;
        width:260px;
        height:260px;
        overflow:hidden;
    }        
    .example2 li {
        text-align:left;
        display:block;
        width:215px;
        height:255px;
    }        
    .carousel-pagination li {
        display:block;
        width:10px;
        height:10px;
        margin-right:5px;
        cursor:pointer;
        float:left;
        background:#333;
    }        
    .carousel-pagination .carousel-pagination-active {
        background:#ff0000;
    }
</style>

Estrenos
<ul class="example2">
    <c:forEach var="peli" items="${peliculas}" varStatus="status">
        <li>
            <a onclick="ver_pelicula('${peli.idString}')" style="cursor:pointer">
                <img height="255" width="215" src='/serve?blob-key=${peli.imagen}' alt="#" title="${peli.titulo}"></img> </a>
        </li>
        <!--<li> //TAMBIEN FUNCIONAN LOS VIDEOS DE YOUTUBE EN ESTE CARROUSEL
            <iframe width="294" height="220" src="http://www.youtube.com/embed/rdNdmc83xe4" frameborder="0" allowfullscreen style="margin: 10px 25px;">
                </iframe>
        </li>-->
    </c:forEach>        
</ul>
<br/>
Noticias
<ul class="example1">
    <c:forEach var="noticia" items="${noticias}" varStatus="status">
        <li>
            <a onclick="ver_noticia('${noticia.idString}')" style="cursor:pointer"><c:out value="${noticia.titulo}"></c:out></a>
        </li>
    <!--<li><span>1</span></li>-->
    </c:forEach>
</ul>

<span class="prev">prev</span>
<span class="next">next</span>