<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project PopCorn</title>
</head>
<body>
	<div style="text-align:center; background-color: silver;">
		<img src="Image/Project_Popcorn.png" width="500" height="500">
		<br><br>
		<form action="crear_pelicula.jsp">
			<input type="submit" style="text-align: center;" value="CREAR PELICULA"/>
		</form>
		<form action="ver_pelicula.jsp">
    		<input type="submit" style="text-align:center" value="VER PELICULA"  />
    	</form>
    	<form action="borrar_pelicula.jsp">
    		<input type="submit" style="text-align:center" value="BORRAR PELICULA"  />
    	</form>
    	<form action="crear_usuario.jsp">
    		<input type="submit" style="text-align:center" value="CREAR USUARIO"  />
    	</form>
    	<form action="login_usuario.jsp">
    		<input type="submit" style="text-align:center" value="LOGIN"  />
    	</form>
    	<br>
    </div>
</body>
</html>