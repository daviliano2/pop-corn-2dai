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

        <script type="text/javascript" src="jQuery/js/jquery-1.4.3.min.js"></script>
        <script>var $r = jQuery.noConflict();</script>
        <script type="text/javascript">
            $r(document).ready(
            function() {
                $r("#botonRegistro").click(
                function() {                                    
                    $r.getJSON(                    
                    "/ir_comprobar_usuario",
                    {                      
                        "user":$r("#user").val(), //AL ESTAR EN LA JSP:INCLUDE DENTRO DE INICIO NO FUNCIONABA
                        "pass":$r("#pass").val(), //PORQUE EL LOGIN YA TIENE UN SCRIPT CON USERNAME Y PASSWORD, O 
                        "nombre":$r("#nombre").val(), //POR LO MENOS CREO QUE ERA ESO, PORQUE AHORA SI FUNCIONA.
                        "apellido":$r("#apellido").val(),
                        "idRol":$r("#idRol").val()
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
    </head>
    <body>

        <form action="/inicio">
            <input type="submit" value="INICIO"/>
        </form>
        <div>        
            <form id="formRegistro">
                <table>
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
                    <tr>
                        <td><input name="Accion" type="checkbox" value="Accion"/> Acci&oacute;n</td>
                        <td><input name="Animacion" type="checkbox" value="Animacion"/> Animaci&oacute;n</td>
                        <td><input name="Belico" type="checkbox" value="Belico"/> B&eacute;lico</td>
                    </tr>
                    <tr>
                        <td><input name="Ciencia_Ficcion" type="checkbox" value="Ciencia Ficcion"/> Ciencia Ficci&oacute;n </td>
                        <td><input name="Comedia" type="checkbox" value="Comedia"/> Comedia</td>
                        <td><input name="Documental" type="checkbox" value="Documental"/> Documental</td>
                    </tr>
                    <tr>
                        <td><input name="Fantanstica" type="checkbox" value="Fantastica"/> Fant&aacute;stica</td>
                        <td><input name="Musical" type="checkbox" value="Musical"/> Musical</td>
                        <td><input name="Romantica" type="checkbox" value="Romantica"/> Rom&aacute;ntica</td>
                    </tr>
                    <tr>
                        <td><input name="Suspense" type="checkbox" value="Suspense"/> Suspense</td>
                        <td><input name="Terror" type="checkbox" value="Terror"/> Terror</td>
                        <td><input name="Western" type="checkbox" value="Western"/> Western</td>
                    </tr>
                </table>

                <input type="hidden" value="${roles.idString}" id="idRol"/>
                <input type="button" value="Registro" id="botonRegistro"/> 
            </form> 
        </div>
    </body>
</html>
