<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


        <!-- HAY QUE CAMBIAR LO DEL FOREACH POR OTRA CONSULTA QUE MUESTRE EL VOTO QUE HA EMITIDO EL USUARIO 
        <//c:forEach var="valoraciones" items="${valoraciones}" varStatus="status" begin="${tamaño-1}">
            <//c:out value="${valoraciones.valoracion}"/>            
        <///c:forEach> -->
        <!--<strong>Tu valoracion: </strong><span id="voto"></span><br/> NO ESTA COMPLETO -->
        <strong>Valoracion de los usuarios: </strong><c:out value="${media}"/>
