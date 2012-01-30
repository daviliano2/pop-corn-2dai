<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

         
        <center>
            <c:forEach var="coment" items="${comentarios}" varStatus="status">
                <table width="100%" style="text-align: justify  ">
                    <tr>
                        <td>     
                            <c:choose>
                                <c:when test="${!empty coment}">
                                    <c:choose>
                                        <c:when test="${empty coment.autor}">
                                            El d&iacute;a <fmt:formatDate value="${coment.fecha}" pattern="dd/MM/yyyy"/> a las <fmt:formatDate value="${coment.fecha}" pattern="hh:mm:ss"/><br/>
                                            <b>an&oacute;nimo </b>escribi&oacute;:<br/>
                                        </c:when>
                                        <c:otherwise>
                                            El d&iacute;a <fmt:formatDate value="${coment.fecha}" pattern="dd/MM/yyyy"/> a las <fmt:formatDate value="${coment.fecha}" pattern="hh:mm:ss"/><br/>
                                            <b><c:out value="${coment.autor}"/></b> escribi&oacute;:<br/>
                                        </c:otherwise>
                                    </c:choose>
                                            &nbsp;&nbsp;&nbsp;&minus;&nbsp;<c:out value="${coment.content}"/><br/>
                                            <hr/>
                                </c:when>
                                <c:otherwise>
                                    <p>La noticia no tiene comentarios.</p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>  
                </table>                   
            </c:forEach>
        </center>
 