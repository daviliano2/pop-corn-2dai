<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
    </head>
    <body>
        <center>
            <c:forEach var="comentarios" items="${comentarios}" varStatus="status">
                <table border="1"><tr><td>
                    <c:out value="${comentarios.author}"/><br/>
                    <strong><c:out value="${comentarios.content}"/><br/></strong>
                    <c:out value="${comentarios.fecha}"/><br/>
                </td></tr></table>
            </c:forEach>
        </center>
    </body>
</html>