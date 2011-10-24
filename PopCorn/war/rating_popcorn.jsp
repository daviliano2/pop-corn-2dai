<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.persistence.Query"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="popcorn.dao.*" %>
<%@ page import="popcorn.persistence.*" %>

<html>
<head>
    <link type="text/html" />
    <!-- <script type="text/javascript" src="js/star_rating.js"></script>
    <script type="text/javascript">
    	function mostrar_voto() {
    		document.getElementById("ratings").value = 3;
    	}
	</script> -->
	<style type="text/css">
	td
	{
		border-style:solid;
		border-color:black;
		border-bottom-width:15px;
	}
	</style>
</head>
<body>

	<p>Tu valoración:</p>
	  	
  	<form action="/rating" method="post">
    <select id="rating" name="valoracion">
    	<option value="Puntuacion" selected="selected"></option>
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<input type="submit" value="vota" />
	</form>
  	<%   	
  	ValoracionDAO valoracionDAO = new ValoracionDAOImpl();
  	//String query = "SELECT count (c) FROM Comentario c";
  	List<Valoracion> valoraciones = valoracionDAO.getAll(Valoracion.class);
  	
	float x = 0;
	int votos = 0;
	float i = 0;
	float media = 0;
	for(Valoracion v : valoraciones) {		
		//votos = Integer.parseInt(v.getValoracion());
		/*
			TODO ESTO ES PROVISIONAL PORQUE NO FUNCIONA EL PARSE-INT DE LOS H***=@#~(/&%+ç****)
			Y DE MOMENTO FUNCIONA, MUY CUTRE, MUY SIMPLE, PERO SACA LA MEDIA DE LAS VOTACIONES xDDDDD
		*/
		if(v.getValoracion().compareTo("1") == 0) {
			votos = 1;
		} else {
			if(v.getValoracion().compareTo("2") == 0) {
				votos = 2;
			} else {
				if(v.getValoracion().compareTo("3") == 0) {
					votos = 3;
				} else {
					if(v.getValoracion().compareTo("4") == 0) {
						votos = 4;
					} else {
						if(v.getValoracion().compareTo("5") == 0) {
							votos = 5;
						}
					}
			 	}
			}
		}
		x = x + votos;
		i++;
	}
	if(i != 0) {
		media = x / i;
	} else {
		media = x;
	}
	%>
	<blockquote>El numero de votaciones es <%=i %></blockquote>
	<blockquote>La media de las votaciones es <%=media %></blockquote>
	<br>
    <br>
    <a href="ver_pelicula.jsp">Volver al hilo de la pelicula</a>

</body>
</html>