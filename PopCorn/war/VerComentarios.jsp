<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="popcorn.dao.ComentarioDAO"%>
<%@page import="popcorn.dao.ComentarioDAOImpl"%>
<%@page import="javax.persistence.Query"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="popcorn.persistence.Comentario"%>
<%@page import="java.util.List"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
	</head>
	<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ComentarioDAO comentarioDAO =  new ComentarioDAOImpl();

		String query = "SELECT count (s) FROM Saludo s";
		Integer numComentarios = comentarioDAO.countAll(Comentario.class);
		out.println("<center>Hay " + numComentarios + " comentarios.");
		out.println("<br>Ir a página ");
		final int TAMANO_PAGINA = 10;
		Integer numeroPaginas = 1 + (numComentarios - 1) / TAMANO_PAGINA;
		for (Integer i = new Integer(1); i <= numeroPaginas; i++) {
			out.println(" <a href='VerComentarios.jsp?pagina=" + i + "'>" + i + "</a> ");
		}
		out.println("</center>");
		int numPagina = 0;
		String strPagina = request.getParameter("pagina");
		if (strPagina != null && !strPagina.equals("")) {
			numPagina = Integer.parseInt(strPagina) - 1;
		}
		List<Comentario> greetings = comentarioDAO.getOrderedPaginated(Comentario.class, numPagina * TAMANO_PAGINA, TAMANO_PAGINA, "date", 2);
		if (greetings.isEmpty()) {
	%>
			<p>The guestbook has no messages.</p>
	<%
		} else {

			for (Comentario c : greetings) {
				out.print("<hr>");
				if (c.getAuthor() == null) {
	%>
					<p>An anonymous person wrote:</p>
	<%
				} else {
	%>
					<p><b><%=c.getAuthor().getNickname()%></b> wrote: </p>
	<%
				}
	%>
				<code><%=c.getContent()%></code>
				<br>
				<code><%=new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(c.getDate())%></code>
				<br>
				<a href="VerComentarios.jsp?idMensaje=<%=c.getId()%>&numPagina=<%=(numPagina + 1)%>">Votar</a>
	<%
				if (user != null && c.getAuthor() != null && user.equals(c.getAuthor())) {
	%>
					<form action="/borrar" method="get">
						<input type="submit" value="borrar" /> 
						<input type="hidden" name="idMensaje" value="<%=c.getId()%>" /> 
						<input type="hidden" name="numPagina" value="<%=numPagina + 1%>" />
					</form>
	<%
				}
			}
		}
		//ComentarioDAO.cerrar();
	%>
	</body>
</html>