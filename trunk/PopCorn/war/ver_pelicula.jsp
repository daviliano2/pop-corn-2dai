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
				document.write('Esta página está optimizada para una resolución de 1366 x 768. Tu pantalla tiene una resolución de ' + screen.width + ' x ' + screen.height + '.')
			</script>
        	<p style="text-align:center">logo y menu. Elige la pelicula</p>
        </div>
		<div id="apDiv3">
   	  <img style="text-align:right" src=<%=p.getImagen() %>>
   	  <!-- "http://1.bp.blogspot.com/-FveumGp8cE8/Tmjzwc4cTlI/AAAAAAAAAPI/ARSTv5OktM4/s640/la-deuda-espana.jpg" -->
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
                <p>Introduce tu comentario sobre la pelicula: </p>
                <form action="/sign" method="post">
      				<textarea name="content" rows="5" cols="70"></textarea>
                    <input type="submit" value="Comentar" />
      				<input type="reset" value="Limpiar" />
    			</form>
    			<p>Tu valoraci&oacuten: <a href="rating_popcorn.jsp" >Ir a votar</a></p>
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
				<!--  En el a&ntilde;o 1965, tres j&oacute;venes israelitas, agentes del Mossad, se embarcan en una misi&oacute;n secreta que tiene 
				como objetivo capturar y matar a un importante criminal de guerra nazi que se ha fugado de la justicia.
				Treinta a&ntilde;os despu&eacute;s, un anciano proclama en Ucrania que &eacute;l es el nazi que buscaban y que a&uacute;n est&aacute; vivo. 
				Uno de los ex agentes del Mossad que participaron en la misi&oacute;n viaja hasta el ex pa&iacute;s sovi&eacute;tico para comprobar 
				la verdad de su afirmaci&oacute;n. Y es que, por mucho tiempo que pase, las deudas siempre deben saldarse. 
				-->
				</p>
			</div>
        </div>
	</div>
	<%
	}
    %>
</body>
</html>