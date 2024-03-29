<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript" src="jQuery/js/jquery-1.4.3.min.js"></script>
<script>var $l = jQuery.noConflict();</script>
<script type="text/javascript">
    $l(document).ready(
    function() {
        $l("#botonLogin").click(
        function() {                         
            $l.getJSON(
            "/ir_conectar_usuario",
            {
                "username":$l("#username").val(),
                "password":$l("#password").val()
            },
            function(str) {
                if(str) {
                    alert("Nombre de usuario o contraseña incorrectos.");
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
