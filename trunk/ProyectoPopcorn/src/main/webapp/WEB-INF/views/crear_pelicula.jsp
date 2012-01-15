<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>


<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
        <title>Project PopCorn</title>
    </head>
    <body>
        <form action="/inicio">
            <input type="submit" value="INICIO"></input>
        </form>
        <c:if test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
            <strong>Estas conectado como : ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
            </strong>
            <a  href="/logout" > Desconectar</a>
        </c:if>
        <div>
            <div style="position:absolute;width:650px;height:500px;"><!--background-color: silver;-->
                <form action="<%=blobstoreService.createUploadUrl("/crear")%>" method="post" enctype="multipart/form-data">
                    <p>Nombre de la pelicula: <input name="titulo" type="text"></input><br/> 
                        Sinopsis: <br/><textarea name="sinopsis" rows="5" cols="70"></textarea><br/>
                        Duracion: <input type="text" name="duracion"></input><br/>
                        Actores(separados por comas): <textarea name="actores" rows="5" cols="70"></textarea><br/>
                        Director: <input type="text" name="director"></input><br/>
                        Categoria: 
                        <select name="categoria">
                            <option value="default" selected="selected">Elige una categoria</option>
                            <c:forEach var="categoriab" items="${categorias}" varStatus="status">
                                <option value="${categoriab.nombre}"><c:out value="${categoriab.nombre}" /></option>
                            </c:forEach>
                        </select><br/>
                        Imagen: <input type="file" name="imagen" id="imagen"></input>
                    </p>
                    <p align="center">
                        <input type="reset" value="Limpiar" />
                        <input type="submit" value="Crear" /> 
                    </p>
                </form>
            </div>
        </div>
    </body>
</html>