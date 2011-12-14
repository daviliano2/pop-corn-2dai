<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Login Usuarios</title>
     
        <script type="text/javascript" src="jQuery/js/jquery-1.4.3.min.js"></script>
        <script type="text/javascript">
            $(document).ready(
                function() {
                    $("#botonLogin").click(
                        function() {                         
                            $.getJSON(
                                "/ir_conectar_usuario",
                                {
                                    "username":$("#username").val(),
                                    "password":$("#password").val()
                                },
                                function(str) {
                                    if(str) {
                                        alert("Nombre de usuario, o contraseña incorrectos.");
                                    } else {
                                        window.location = "/inicio";
                                    }
                                }
                            );
                        }
                    );
                }
            );
        </script>
    </head>
    <body>
        <c:if test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
            <strong>Estas conectado como : ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
            </strong>
            <a  href="/logout" > Desconectar</a>
        </c:if>
        <c:if test="${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">

            <form id="formLogin">
                    Usuario: <input id="username" type="text" maxlength="20" /> 
                    Contraseña: <input id="password" type="password" maxlength="20" />                     
                    <input type="button" value="Conectar" id="botonLogin" />                  
            </form> 
        </c:if>
    </body>
</html>
