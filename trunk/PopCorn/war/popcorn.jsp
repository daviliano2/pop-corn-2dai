<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.persistence.Query"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="popcorn.Comentario" %>
<%@ page import="popcorn.EMF" %>

<html>
<head>
    <link type="text/html" />
    <link rel="stylesheet" type="text/css" href="stylesheets/ui.stars.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/ui.stars.min.js"></script>
    
    <script type="text/javascript">
    $(function(){
        $("#ratings").children().not(":radio").hide(); //Escondemos todo el contenido exceptuando los radios.
        $("#ratings").stars({
            oneVoteOnly: true, //Este parámetro sirve para que una vez pulsada alguna estrella haga el submit.
            split: 2, //Dividimos las estrellas en medios 
        });
    });
</script>
  </head>

  <body>

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
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Conectate</a>
para poder postear.</p>
<%
    }
%>
<table align="center" width="60%" border=3>
	<tr>
		<td align="center">
			<img src="http://t0.gstatic.com/images?q=tbn:ANd9GcS-wIDEBnW51r3V-OWFJzxBbwig2BcmeHeMGoDr90Ij6Tq7HEpTgA">
		</td>
	</tr>
	<tr>
		<td align="center">
			<p>Tu valoración:</p> 
			<form id="ratings" action="/rating" method="post">
    			<input type="radio" name="rate" value="0.5" id="rate1" />
     			<input type="radio" name="rate" value="1" id="rate2" />
     			<input type="radio" name="rate" value="1.5" id="rate3" />
      			<input type="radio" name="rate" value="2" id="rate4" />
      			<input type="radio" name="rate" value="2.5" id="rate5" />
      			<input type="radio" name="rate" value="3" id="rate1" />
      			<input type="radio" name="rate" value="3.5" id="rate2" />
      			<input type="radio" name="rate" value="4" id="rate3" />
      			<input type="radio" name="rate" value="4.5" id="rate4" />
      			<input type="radio" name="rate" value="5" id="rate5" />
      			<div><input type="submit"/></div>
    		</form>
    	</td>
	</tr>
	<tr>
		<td align="center">
			<form action="/sign" method="post">
      			<div><textarea name="content" rows="3" cols="50"></textarea></div>
      			<div><input type="submit" value="Comenta" /></div>
    		</form>
		</td>
	</tr>
</table>
<%
	EntityManager em = EMF.get().createEntityManager();
	String query = "SELECT FROM popcorn.Comentario ORDER by date desc";
	Query consulta = em.createQuery(query);
	List<Comentario> comentarios = (List<Comentario>) consulta.getResultList();
    if (comentarios.isEmpty()) {
%>
<center>
<p>El analisis no tiene comentarios.</p>
</center>
<%
    } else {
        for (Comentario c : comentarios) {
            if (c.getAuthor() == null) {
%>
<center>
<p>Tienes que estar logueado para comentar.
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Conectate</a>
</p>
</center>
<%
            } else {
%>
<center>
<p><b><%= c.getAuthor().getNickname() %></b> wrote:</p>
<blockquote><%= c.getContent() %></blockquote>
</center>
<%
            }
%>
<!--  <blockquote><%= c.getContent() %></blockquote> -->
<%
        }
    }
    em.close();
%>

    

  </body>
</html>