<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project PopCorn</title>
</head>
<body>
	<div style="position:absolute;width:500px;height:500px;background-color: silver;">
		<form action="/crear" method="post">
			<p>Nombre de la pelicula: <input name="titulo" type="text"/> 
            Sinopsis: <textarea name="sinopsis" rows="5" cols="70"></textarea>
            Duracion: <input type="text" name="duracion">
            Actores(separados por comas): <input type="text" name="actores">
            Director: <input type="text" name="director">
            Categoria: <input type="text" name="categoria">
            
					<input type="reset" value="Limpiar" />
					<input type="submit" value="Crear" /> </p>
		</form>
	</div>
</body>
</html>