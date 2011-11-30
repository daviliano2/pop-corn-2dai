<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
    <head>
        <script type="text/javascript">            
           
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <form action="/inicio">
            <input type="submit" value="INICIO"/>
        </form>
        <c:if test="${valido == false}">
            <script>
                alert("Usuario ya registrado, inserta otro nombre");
            </script>
        </c:if>        
        <form action="/comprobar_usuario" method="get">
            <p>
                Nombre de Usuario: <input name="username" type="text" maxlength="50" /> 
                Contraseña: <input name="password" type="password" maxlength="20" />                                       
                <input type="hidden" value="${roles.idString}" name="idRol"/>
                <input type="submit" value="${roles.nombre}" />                   
            </p>
        </form> 
    </body>
</html>

