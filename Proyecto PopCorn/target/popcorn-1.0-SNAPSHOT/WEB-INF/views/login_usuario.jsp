<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.persistence.Query"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List" %>
<%@ page import="popcorn.dao.*" %>
<%@ page import="popcorn.persistence.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login de Usuarios</title>
</head>
<body>
	<% 
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    List<Usuario> usuarios = usuarioDAO.getAll(Usuario.class);
    %>
	<div style="position:absolute;width:650px;height:60px;background-color: silver;">
	<form action="/login" method="post">
    <p>Nombre de Usuario: <input name="usuario" type="text" maxlength="50" /> 
    Contraseña: <input name="password" type="password" maxlength="20" />
	<input type="submit" value="Acceder" /> </p><br/>
	</form> 
	</div>
	<%
	for(Usuario u : usuarios) {
		if(u.getNombreUsuario().compareTo(request.getParameter("userName")) == 0 && 
				u.getPassword().compareTo(request.getParameter("pass")) == 0) {
			%>
			<div>
			<p align="center"><strong>Has hecho login correctamente</strong></p>
			<p align="center"><strong>Bienvenido <%=u.getNombreUsuario() %></strong></p>
			</div>
			<%
			
		}
	}
	%>
</body>
</html>