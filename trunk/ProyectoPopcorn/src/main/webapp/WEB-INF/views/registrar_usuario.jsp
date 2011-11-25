<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <form action="/crear_usuario" method="get">
            <p>
                Nombre de Usuario: <input name="username" type="text" maxlength="50" /> 
                Contraseña: <input name="password" type="password" maxlength="20" />
                <input type="hidden" value="ROLE_USER" name="rol"/>
                <input type="submit" value="Crear Usuario" /> 
            </p>
        </form>
    </body>
</html>
