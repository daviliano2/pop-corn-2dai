<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project PopCorn</title>
</head>
<body>
	<div>
		<div style="position:absolute;width:650px;height:500px;background-color: silver;">
			<form action="/crear" method="post">
				<p>Nombre de la pelicula: <input name="titulo" type="text"/><br> 
            	Sinopsis: <br><textarea name="sinopsis" rows="5" cols="70"></textarea><br>
            	Duracion: <input type="text" name="duracion"><br>
            	Actores(separados por comas): <textarea name="actores" rows="5" cols="70"></textarea><br>
            	Director: <input type="text" name="director"><br>
            	Categoria: <input type="text" name="categoria"><br>
            	Imagen: <input type="file" name="imagen"></p>
            	<p align="center">
					<input type="reset" value="Limpiar" />
					<input type="submit" value="Crear" /> 
				</p>
			</form>
		</div>
	</div>
</body>
</html>