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
	</head>
	<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ComentarioDAO comentarioDAO =  new ComentarioDAOImpl();
		
		Integer numComentarios = comentarioDAO.countAll(Comentario.class);
		
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
		List<Comentario> comentarios = comentarioDAO.getOrderedPaginated(Comentario.class, numPagina * TAMANO_PAGINA, TAMANO_PAGINA, "date", 2);
		if (comentarios.isEmpty()) {
	%>
			<p>No hay valoraciones</p>
	<%
		} else {
			for (Comentario c : comentarios) {
				out.print("<hr>");
				if (c.getAuthor() == null) {
	%>
					<p>Anónimo escribió:</p>
	<%
				} else {
	%>
					<p><b><%=c.getAuthor().getNickname()%></b> escribió: </p>
	<%
				}
	%>
				<code><%=c.getContent()%></code>
				<br>
				<code><%=new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(c.getDate())%></code>
				<br>
	<%			
				}
		}
		//ComentarioDAO.cerrar();
	%>
	</body>
</html>