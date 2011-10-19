<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.persistence.Query"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="popcorn.*" %>

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
	<!--<form id="ratings" action="/rating" method="get" style="margin-left: 5px;"> 
	
	<img id="img1" onmouseover="Show1()" onclick="ShowRate1()" onmouseout="Hide1()" alt="" src="Image/Star1.jpg" width="20" />
    <img id="img2" onmouseover="Show2()" onclick="ShowRate2()" onmouseout="Hide2()" alt="" src="Image/Star1.jpg" width="20" />
    <img id="img3" onmouseover="Show3()" onclick="ShowRate3()" onmouseout="Hide3()" alt="" src="Image/Star1.jpg" width="20" />
    <img id="img4" onmouseover="Show4()" onclick="ShowRate4()" onmouseout="Hide4()" alt="" src="Image/Star1.jpg" width="20" />
    <img id="img5" onmouseover="Show5()" onclick="ShowRate5()"  alt="" src="Image/Star1.jpg" width="20" />&nbsp;
    <br />
    <br />
 	<div><input type="submit" hidden="" /></div>
 	
    </form>
    <br>
    
    
    	<form action="/rating" method="post">
      	<div><textarea name="voto" rows="2" cols="2"></textarea></div>
   		<div><input type="submit" value="Vota" /></div>
  	</form>
  	-->
  	
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
  	EntityManager em = EMF.get().createEntityManager();
	String query = "SELECT FROM popcorn.Valoracion";
	Query consulta = em.createQuery(query);
	List<Valoracion> valora = (List<Valoracion>) consulta.getResultList();
	float x = 0;
	int votos = 0;
	float i = 0;
	float media = 0;
	for(Valoracion v : valora) {		
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
	<%
	em.close();
	%>
	<br>
    <br>
    <a href="popcorn.jsp">Volver al hilo de la pelicula</a>

</body>
</html>