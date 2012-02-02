<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div style="width:650px;height: auto;margin-left: 50px;">
    <br/>
    <c:forEach var="categoria" items="${categorias}" varStatus="status">
        <strong><c:out value="${categoria.nombre}"></c:out>:</strong><br/>
        <c:forEach var="pelicula" items="${peliculas}" varStatus="status">
            <c:choose>
                <c:when test="${categoria.nombre eq pelicula.categoria}">                                                                        
                    <a onclick="ver_pelicula('${pelicula.idString}')" style="cursor: pointer; text-decoration: underline;">
                        <c:out value="${pelicula.titulo}"></c:out>
                    </a>,                                 
                </c:when>                                
            </c:choose>  
        </c:forEach>
        <br/>
    </c:forEach>
</div>