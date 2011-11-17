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
        <center>
            <c:forEach var="coment" items="${comentarios}" varStatus="status">
                <table border="1">
                    <tr>
                        <td>
                            <c:out value="${coment.author}"/><br/>
                            <strong><c:out value="${coment.content}"/><br/></strong>
                            <c:out value="${coment.fecha}"/><br/>
                        </td>
                    </tr>
                </table>                   
            </c:forEach>
        </center>
    </body>
</html>