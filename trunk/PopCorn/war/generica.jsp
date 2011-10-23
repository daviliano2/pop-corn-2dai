<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="stylesheets/Estiloweb.css" />
</head>
<body>
	<div id="apDiv1" style="background: url('Image/popcorn.jpg');">
     	<div id="apDiv2">
			<script language="JavaScript" type="text/javascript">
				document.write('Esta página está optimizada para una resolución de 1366 x 768. Tu pantalla tiene una resolución de ' + screen.width + ' x ' + screen.height + '.')
			</script>
        	<p style="text-align:center">logo y menu.</p>
        </div>
		<div id="apDiv3">
   	  <img style="text-align:right" src="http://1.bp.blogspot.com/-FveumGp8cE8/Tmjzwc4cTlI/AAAAAAAAAPI/ARSTv5OktM4/s640/la-deuda-espana.jpg">
		</div>
        <div id="apDiv4">
        	<div id="apDiv5">
        		<%
    			UserService userService = UserServiceFactory.getUserService();
    			User user = userService.getCurrentUser();
   				if (user != null) {
				%>
					<p>Hola, <%= user.getNickname() %>! (Puedes
					<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">salir</a>.)</p>
				<%
   				} else {
				%>
					<p>Hola!
					<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Conectate</a> para poder comentar.</p>
				<%
    			}
				%>
            	<!--  <form action="/rating" method="post">
    				<p>Nombre de Usuario: <input name="usuario" type="text" maxlength="50" /> 
            		Contraseña: <input name="password" type="password" maxlength="20" />
					<input type="submit" value="Acceder" /> </p><br>
				</form> -->
                <p>Introduce tu comentario sobre la pelicula: </p>
                <form action="/sign" method="post">
      				<textarea name="content" rows="5" cols="70"></textarea>
                    <input type="submit" value="Comentar" />
      				<input type="reset" value="Limpiar" />
    			</form>
            </div>
        </div>
        <div id="contenidos" style="position: absolute;width: 621px;height: 800px;left: 674px;top: 339px;z-index: 2;">
        	<iframe src="VerComentarios.jsp" style="position: absolute;width: 615px;height: 799px;"></iframe>
        </div>
        <div id="apDiv7">
        </div>
	</div>
</body>
</html>