<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
    </head>
    <body>
        
        <c:forEach var="valoraciones" items="${valoraciones}" varStatus="status" begin="${tamaño-1}">
            <c:out value="${valoraciones.valoracion}"/>            
        </c:forEach>
        <strong>Valoracion de los usuarios: </strong><c:out value="${media}"/>
    </body> 
</html>