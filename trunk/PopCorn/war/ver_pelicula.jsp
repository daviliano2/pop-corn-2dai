<%@ page language="java" contentType="text/html; "%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="popcorn.dao.*" %>
<%@ page import="popcorn.persistence.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project PopCorn</title>
<link rel="stylesheet" type="text/css" href="stylesheets/Estiloweb.css" />
</head>
<body>
	<% 
	String pelicula;
	PeliculaDAO peliculaDAO = new PeliculaDAOImpl();
    List<Pelicula> peliculas = peliculaDAO.getAll(Pelicula.class);
    for(Pelicula p : peliculas) {
    %>
	<div id="apDiv1" style="background: url('Image/popcorn.jpg');">
     	<div id="apDiv2">
			<script language="JavaScript" type="text/javascript">
				document.write('Esta p&aacute;gina est&aacute; optimizada para una resoluci&oacute;n de 1366 x 768. Tu pantalla tiene una resoluci&oacute;n de ' + screen.width + ' x ' + screen.height + '.')
			</script>
        	<p style="text-align:center">logo y menu. Elige la pelicula</p>
        </div>
		<div id="apDiv3">
   	  		<table height="300px" width="600px">
   	  			<tr>
   	  				<td rowspan="2" align="center" width="350px">
   	  					<img id="img1" src=Image/<%= p.getImagen() %>>
   	  				</td>
   	  				<td align="center" height="75px">
   	  					<h2><%=p.getTitulo() %></h2>
   	  				</td>
   	  			</tr>
   	  			<tr>
   	  				<td align="char">
   	  					<p><b>Director:</b> <%=p.getDirector() %><br>
   	  					<b>Duraci&oacute;n:</b> <%=p.getDuracion() %> min.<br>
   	  					<b>Categoria:</b> <%=p.getCategoria() %><br>
   	  					<b>Actores: </b><%=p.getActores() %></p> 
   	  				</td>
   	  			</tr>
   	  		</table>  	  		
		</div>
        <div id="apDiv4">
        	<div id="apDiv5">
				<iframe id="iframe2" src="login_usuario.jsp" scrolling="no"></iframe><br><br>
                <p>Introduce tu comentario sobre la pelicula: </p>
                <form action="/sign" method="post">
      				<textarea name="content" rows="5" cols="70"></textarea>
                    <input type="submit" value="Comentar" />
      				<input type="reset" value="Limpiar" />
    			</form>
    			<p>Tu valoraci&oacute;n: <a href="rating_popcorn.jsp" >Ir a votar</a></p>
            </div>
        </div>
        <div id="apDiv6">
        	<iframe id="iframe1" src="ver_comentarios.jsp"></iframe>
        </div>
        <div id="apDiv7">
        	<p><b>Sinopsis</b></p>
			<div id="apDiv8">
				<p style="text-align: justify;">
					<%= p.getSinopsis() %>
				</p>
			</div>
        </div>
	</div>
	<%
	}
    %>
</body>
</html>