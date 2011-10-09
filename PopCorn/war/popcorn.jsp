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
<table align="center" width="100%" style="background:url('http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2008/08/11/popcorn.jpg');">
	<tr>
		<td>
		<p align="center" style="background-color: white;">Aqui va el logo y el menu.</p>
		</td>
	</tr>
	<tr>
		<td>
			<table align="center" width="80%" style="background-color: white;">
				<tr>
					<td width="50%" align="center">
						<img src="http://1.bp.blogspot.com/-FveumGp8cE8/Tmjzwc4cTlI/AAAAAAAAAPI/ARSTv5OktM4/s640/la-deuda-espana.jpg">
						<br>
						<p>Tu valoración:</p> 
						
						<form id="ratings" action="/rating" method="post" style="margin-left: 5px;">
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
    					<br>
    					<br>
    					<br>
					</td>
					<td align="center" width="50%">
						<form action="/sign" method="post">
      						<div><textarea name="content" rows="5" cols="70"></textarea></div>
      						<div><input type="submit" value="Comenta" /></div>
    					</form>
					</td>
				</tr>
				<tr>
					<td>
						<p><b>Sinopsis</b></p>
						<p style="text-align: justify;">Incluso los mejores agentes secretos tienen una deuda pendiente con misiones pasadas. 
						Y ahora, Rachel Singer debe enfrentarse a la suya…
						John Madden, nominado por la Academia (Shakespeare in Love/Shakespeare enamorado), 
						dirige el thriller de espionaje LA DEUDA, rodado en decorados naturales en Tel Aviv, Reino Unido y Budapest. 
						El guión, escrito por Matthew Vaughn & Jane Goldman y Peter Straughan, es una adaptación de la película israelí Ha-Hov (La deuda). 
						En 2001, LA DEUDA ha sido galardonada con el Premio Especial Policía (Premio del Jurado) 
						en el Festival Internacional de Cine Policíaco de Beaune.
						La historia empieza en 1997, cuando dos agentes del Mossad ya retirados, Rachel (interpretada por la oscarizada Helen Mirren) 
						y Stephan (Tom Wilkinson, nominado en dos ocasiones por la Academia), reciben una noticia sorprendente acerca de su antiguo compañero David 
						(Ciarán Hinds, el próximo estreno Tinker, Tailor, Soldier, Spy). 
						Se convirtieron en figuras muy respetadas en Israel después de una misión que realizaron entre 1965 y 1966 cuando los tres 
						(interpretados en ese periodo por Jessica Chastain [El árbol de la vida, The Help], Marton Csokas [El señor de los anillos, Dream House] 
						y Sam Worthington [Avatar, Furia de titanes] respectivamente) localizaron al criminal de guerra nazi Dieter Vogel 
						(Jesper Christensen, de Casino Royale y Quantum of Solace), el temible "Cirujano de Birkenau", en Berlín Este.
						Rachel tuvo que superar una atracción sentimental mientras servía de cebo para que sus compañeros cerraran la pinza alrededor de Vogel.
						El equipo arriesgó mucho y pagó un considerable precio para cumplir la misión, 
						pero ¿de verdad la cumplieron? El suspense crece, pasando de un periodo a otro, y las revelaciones son cada vez más sorprendentes. 
						Rachel no tendrá más remedio que ocuparse personalmente del asunto. 
						</p>
    				</td>
    				<td  rowspan="3">
    					<%
						EntityManager em = EMF.get().createEntityManager();
						String query = "SELECT FROM popcorn.Comentario ORDER by date desc";
						Query consulta = em.createQuery(query);
						List<Comentario> comentarios = (List<Comentario>) consulta.getResultList();
   						if (comentarios.isEmpty()) {
						%>

							<p align="center">El analisis no tiene comentarios.</p>

						<%
   						} else {
        					for (Comentario c : comentarios) {
            					if (c.getAuthor() == null) {
						%>
									<p align="center"><a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Inicia sesion </a>para comentar</p>
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
   					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

   

    

  </body>
</html>