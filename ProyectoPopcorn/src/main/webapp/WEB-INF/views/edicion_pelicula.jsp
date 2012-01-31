<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="position:absolute;width:650px;height:500px;left: 30px;">
    <form action="/editar_peli" method="get">
        <p> Nombre de la pel&iacute;cula: <input name="titulo" type="text" value="${pelicula.titulo}"></input><br/> 
            Sinopsis: <br/><textarea name="sinopsis" rows="5" cols="70" placeholder="Sinopsis"></textarea><br/>
            Duraci&oacute;n: <input type="text" name="duracion" value="${pelicula.duracion}"></input><br/>
            Actores: <br/><textarea name="actores" rows="5" cols="70" placeholder="Actores separados por comas"></textarea><br/>
            Director: <input type="text" name="director" value="${pelicula.director}"></input><br/>
            Fecha de estreno: <input type="date" name="fecha"/><br/>
            Categor&iacute;a: 
            <select name="categoria">
                <option value="default" selected="selected">Elige una categoria</option>
                <c:forEach var="categoriab" items="${categorias}" varStatus="status">
                    <option value="${categoriab.nombre}"><c:out value="${categoriab.nombre}" /></option>
                </c:forEach>
            </select><br/>            
        </p>
        <p align="center">
            <input type="reset" value="Limpiar" />
            <input type="hidden" name="idPelicula" value="${pelicula.idString}"/>
            <input type="submit" value="Editar" /> 
        </p>
    </form>
</div>
