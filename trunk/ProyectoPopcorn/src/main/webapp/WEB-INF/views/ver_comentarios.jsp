<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
    body
    {
        padding-top: 20px;
    }
    #wrapper
    {
        margin: auto;
        width: 645px;
    }
    .contents
    {
        width: 91%; /*height: 150px;*/
        margin: 0;
    }
    .contents > p
    {
        padding: 8px;
    }
    .table
    {
        width: 100%;
    }
    .table th, .table td
    {
        width: 16%;
        height: 20px;
        padding: 4px;
        text-align: left;
    }   
    .header
    {
        background-color: #4f7305;
        color: White;
    }
    #divs
    {
        margin: 0;
        height: 200px;
        font: verdana;
        font-size: 14px;
        background-color: White;
    }
    #divs > div
    {
        width: 98%;
        padding: 8px;
    }
    #divs > div p
    {
        width: 95%;
        padding: 8px;
    }
    ul.tab
    {
        list-style: none;
        margin: 0;
        padding: 0;
    }
    ul.tab li
    {
        display: inline;
        padding: 10px;
        color: Black;
        cursor: pointer;
    }
    #container
    {
        width: 100%;
        border: solid 1px red;
    }
</style>

<link href="stylesheets/smartpaginator.css" rel="stylesheet" type="text/css" />
<script src="jQuery/js/smartpaginator.js" type="text/javascript"></script>
<script>var $f = jQuery.noConflict();</script>

<script type="text/javascript">
    $f(document).ready(function() {
        $f('#green-contents').css('display', '');        

        $f('#green').smartpaginator(
        { 
            totalrecords: ${numTotal}, 
            recordsperpage: 5, 
            datacontainer: 'mt', 
            dataelement: 'tr', 
            initval: 0, 
            next: 'Next', 
            prev: 'Prev', 
            first: 'First', 
            last: 'Last', 
            theme: 'green' 
        });       

    });
</script>
<br/>
<div id="wrapper">   

    <div id="green-contents" class="contents">

        <table id="mt" cellpadding="0" cellspacing="0" border="0" class="table">
            <c:forEach var="coment" items="${comentarios}" varStatus="status">
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
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <form action="/borrar_comentario" method="post">
                                        <input type="submit" value="Borrar Admin"/>
                                        <input type="hidden" name="idComentario" value="${coment.idString}" />
                                    </form>
                                </sec:authorize>
                                <c:if test="${coment.autor eq sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username and !empty coment.autor}">                          
                                    <form action="/borrar_comentario2" method="post">
                                        <input type="submit" value="Borrar"/>
                                        <input type="hidden" name="idComentario" value="${coment.idString}"/>
                                    </form>                                   
                                </c:if> 
                                <hr/>
                            </c:when>                            
                            <c:otherwise>
                                <p>La noticia no tiene comentarios.</p>
                            </c:otherwise>                            
                        </c:choose>
                    </td>
                </tr>      
            </c:forEach>
        </table> 
        
        <div id="green" style="margin: auto; width: 500px">
        </div>
        
    </div>
    
</div>
