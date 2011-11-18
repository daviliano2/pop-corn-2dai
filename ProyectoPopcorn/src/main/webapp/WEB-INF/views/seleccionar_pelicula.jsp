<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Menu Peliculas</title>
    </head>
    <body>
        <form action="/inicio">
            <input type="submit" value="INICIO"/>
        </form>
        <c:forEach var="pelicula" items="${peliculas}" varStatus="status">           
            <form action="/ir_ver_pelicula" method="get">               
                <input type="hidden" value="${pelicula.idString}" name="idPelicula"/>
                <input type="submit" value="${pelicula.titulo}"/>
            </form><br/>
        </c:forEach>
        
    </body>
</html>
