<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript" src="jQuery/js/simple.carousel.js"></script>
<script>var $m = jQuery.noConflict();</script>
<script >
    $m(function() {
        // example 1
        $m("ul.example1").simplecarousel({
            width:500,
            height:100,
            visible: 3,
            //auto: 6000,
            vertical: true,
            fade: 400,
            next: $('.next'),
            prev: $('.prev')
        });
            
        // example 2
        $m("ul.example2").simplecarousel({
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

<center>
    <!--<ul class="example1"> -->
        <c:forEach var="coment" items="${comentarios}" varStatus="status">
            <!--<li> -->
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
                                    &nbsp;&nbsp;&nbsp;&minus;&nbsp;<c:out value="${coment.content}"/>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <form action="/borrar_comentario" method="post">
                                            <input type="submit" value="borrar"/>
                                            <input type="hidden" name="idComentario" value="${coment.idString}" />
                                        </form>
                                    </sec:authorize>
                                    <br/>
                                </c:when>                            
                                <c:otherwise>
                                    <p>La noticia no tiene comentarios.</p>
                                </c:otherwise>                            
                            </c:choose>
                        </td>
                    </tr>                  
                </table>   
            <!--</li>-->
        </c:forEach>
   <!-- </ul>
</center>
<span class="prev">prev</span>
<span class="next">next</span>