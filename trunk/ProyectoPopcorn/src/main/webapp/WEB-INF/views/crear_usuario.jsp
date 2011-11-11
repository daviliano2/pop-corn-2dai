<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de Usuarios</title>
</head>
<body>
	<div style="position:absolute;width:650px;height:60px;background-color: silver;">
	<form action="/crear_usuario" method="post">
    <p>Nombre de Usuario: <input name="usuario" type="text" maxlength="50" /> 
    Contraseña: <input name="password" type="password" maxlength="20" />
	<input type="submit" value="Acceder" /> </p></br>
	</form> 
	</div>
</body>
</html>