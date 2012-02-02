<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script type="text/javascript" src="jQuery/js/calendar.js"></script>
<link rel="stylesheet" type="text/css" href="stylesheets/calendar.css" ></link>

<div style="width:650px;height: auto;left: 30px;">
    <form action="/editar_peli" method="get">
        <p> Nombre de la pel&iacute;cula: <input name="titulo" type="text" value="${pelicula.titulo}"></input><br/> 
            Sinopsis: <br/><textarea name="sinopsis" rows="5" cols="70" placeholder="Sinopsis"></textarea><br/>
            Duraci&oacute;n: <input type="text" name="duracion" value="${pelicula.duracion}"></input><br/>
            Actores: <br/><textarea name="actores" rows="5" cols="70" placeholder="Actores separados por comas"></textarea><br/>
            Director: <input type="text" name="director" value="${pelicula.director}"></input><br/>
            Fecha de estreno: <input size="10" id="fc_1328006263" type="text" name="fecha" title="MM/DD/YYYY" onClick="displayCalendar(this);" value="${pelicula.fechEstreno}"></input><br/>
            Categor&iacute;a: 
            <select name="categoria">
                <option value="default" selected="selected">Elige una categoria</option>
                <c:forEach var="categoriab" items="${categorias}" varStatus="status">
                    <option value="${categoriab.nombre}"><c:out value="${categoriab.nombre}" /></option>
                </c:forEach>
            </select><br/>
            Trailer:<input type="text" name="trailer" value="${pelicula.trailer}"></input><br/>
        </p>
        <p align="center">
            <input type="reset" value="Limpiar" />
            <input type="hidden" name="idPelicula" value="${pelicula.idString}"/>
            <input type="submit" value="Editar" /> 
        </p>
    </form>
</div>
