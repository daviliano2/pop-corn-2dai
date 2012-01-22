<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <script type="text/javascript">
            $(document).ready(
            function () {
                $("#botonRegistro").click(
                function() {
                    
                    var categorias = "";
                    $("input[name='cat']").each(
                    function(i, e) {
                        if (e.checked) {
                            if (categorias != "") {
                                categorias += ",";
                            }
                            categorias+=e.id;
                                                        
                        }
                    }
                   
                    );
                    $.getJSON(                    
                    "/ir_comprobar_usuario",
                    {                      
                        "user":$("#user").val(), //AL ESTAR EN LA JSP:INCLUDE DENTRO DE INICIO NO FUNCIONABA
                        "pass":$("#pass").val(), //PORQUE EL LOGIN YA TIENE UN SCRIPT CON USERNAME Y PASSWORD, O 
                        "nombre":$("#nombre").val(), //POR LO MENOS CREO QUE ERA ESO, PORQUE AHORA SI FUNCIONA.
                        "apellido":$("#apellido").val(),
                        "idRol":$("#idRol").val(),
                        "categ":categorias
                    },
                    
                    function(str) {
                        if(str == null) {
                            alert("Nombre de usuario ya registrado");  
                                        
                        } else {
                            alert("Registro completado");
                            window.location = "/inicio";
                        }
                    }
                );
                }
            );
            }
        );
        </script>

        <div>        
            <form id="formRegistro">
                <table style="border:5px">
                    <tr>
                        <td>Nombre de Usuario: </td>
                        <td><input id="user" type="text" maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>Contraseña:</td>
                        <td><input id="pass" type="password" maxlength="20" /></td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td><input id="nombre" type="text" maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>Apellido: </td>
                        <td><input id="apellido" type="text" maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>Categorias favoritas:</td>
                    </tr>
                </table>
                <table>
                    <tr>
                        
                        <td>
                            <c:set var="nombresCategoria" value="${categorias}"/>
                            <c:set var="numCat" value="${numero}"/>
                            <table>
                                <c:forEach var="i" begin="0" end="${numCat}" step="4">
                                    <tr>
                                        <c:forEach items="${nombresCategoria}" var="currentName" varStatus="status" begin="${i}" end="${i+3}">
                                            <td>
                                                <input type="checkbox"  id="${currentName.strId}" name="cat" value="${currentName.nombre}"/><c:out value="${currentName.nombre}"/><br />
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
                

                <input type="hidden" value="${roles.idString}" id="idRol"/>
                <input type="button" value="Registro" id="botonRegistro"/> 
                <input type="reset" value="Limpiar"/>
            </form> 
        </div>