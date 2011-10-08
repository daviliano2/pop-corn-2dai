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
<center>
<img src="http://t0.gstatic.com/images?q=tbn:ANd9GcS-wIDEBnW51r3V-OWFJzxBbwig2BcmeHeMGoDr90Ij6Tq7HEpTgA">
</center>
<form action="/sign" method="post">
      <center>
      <div><textarea name="content" rows="3" cols="50"></textarea></div>
      Puntuacion<select id="Puntuacion" name="Puntuacion">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      </select>
      <div><input type="submit" value="Comenta" /></div>
      </center>
    </form>
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