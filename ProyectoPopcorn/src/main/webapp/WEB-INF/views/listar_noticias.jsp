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
<script>var $o = jQuery.noConflict();</script>

<script type="text/javascript">
    $o(document).ready(function() {
        $o('#green-contents').css('display', '');        

        $o('#green').smartpaginator(
        { 
            totalrecords: ${numNoticias}, 
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
            <c:forEach var="noticia" items="${noticias}" varStatus="status">
                <tr>                 
                    <td>   
                        <img height="95" width="80" src='/serve?blob-key=${noticia.imagen}' alt="#"></img>
                        <strong><c:out value="${noticia.titulo}"></c:out></strong><br/>
                        <c:out value="${noticia.contenido}"></c:out><br/>
                        <c:if test="${!empty noticia.trailer}">
                            <a href="http://www.youtube.com/embed/${noticia.trailer}" target="_blank">Ver trailer</a><br/>
                        </c:if>
                        <c:if test="${!empty noticia.fuenteNoticia}">
                            <a href="${noticia.fuenteNoticia}" target="_blank">Ir a la fuente de la noticia</a><br/>
                        </c:if>
                    </td>
                    <td>   

                    </td>
                </tr>    
            </c:forEach>
        </table> 
        <div id="green" style="margin: auto; width: 500px">
        </div>
    </div>
</div>
