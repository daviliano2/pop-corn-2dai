<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Demo formulario validado.</title>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">


$().ready(function() {	
	// Configuramos la validación de los distintos campos del formulario
	$("#signupForm").validate({
		// Empezamos por las reglas
		rules: {
			firstname: "required", // Para el campo firstname(nombre) pedimos que sea requerido.
			lastname: "required",  // Lo mismo para el campo lastname.
			username: { // Cuando hay mas de una regla abriremos llaves, aqui validamos username
				required: true, // Tienes que ser requerido
				minlength: 2    // Tiene que tener un tamaño mayor o igual a dos caracteres
			},
			password: {  // reglas para el campo password
				required: true, // Tienes que ser requerido
				minlength: 5    // Tiene que tener un tamaño mayor o igual a cinco caracteres
			},
			confirm_password: { // reglas para el campo confirm_password
				required: true, // Tienes que ser requerido 
				minlength: 5,   // Tiene que tener un tamaño mayor o igual a cinco caracteres
				equalTo: "#password"  // Tiene que ser igual que el campo password y para ello indicamos su id
			},
			email: {  // un nuevo caso es identificar que es un email valido osea que tiene formato de email
				required: true, 
				email: true  // para ello el metodo email: true comprobara esta validación
			},
			age: {  // Otros ejemplos podrian ser valor minimo o valor maximo
				required: true,
				min: 18,  // determina el valor minimo
				max:99    // determina el valor maximo
			},
			year: {  // Una cantidad entre un rango
				required: true,
				range: [1911, 1992]  // Aqui indico que no puede ser menor de 1911 ni mayor de 1992
			},	
			agree: "required"  // Este input es de typo checkbox si quiero que sea obligatorio marcarlo le doy el valor required
		},
		messages: { // La segunda parte es configurar los mensajes, por lo que tengo que ir indicando para cada campo y cada regla el mensaje que quiero mostrar si no se cumple.
			firstname: "Por favor, introduzca su Nombre",
			lastname: "Por favor, introduzca sus Apellidos",
			username: {
				required: "Por favor, introduzca su Nombre de Usuario",
				minlength: "El Nombre de usuario debe de tener al menos 2 caracteres"
			},
			password: {
				required: "Por favor, introduzca su password",
				minlength: "Su password debe de tener al menos 5 caracteres"
			},
			confirm_password: {
				required: "Por favor, introduzca de nuevo su password",
				minlength: "Su password debe de tener al menos 5 caracteres",
				equalTo: "Las password introducidas no son iguales"
			},
			email: "Por favor, introduzca un email valido",
			age: {
				required: "Por favor, introduzca su edad",
				min: "La edad no puede ser menor de 18 años",
				max: "La edad tiene que ser menor de 99 años"
			},
			year: {
				required: "Por favor, introduzca su año de nacimiento",
				range: "Tiene que poner un año entre 1911 y 1992"
				
			},
			agree: "Por favor acepte nuestra politica"
		}
	});
});
</script>
<style type="text/css">
#signupForm p{clear:both;}
#signupForm input{float:left;}
#signupForm label{width:150px;display:block;float:left;}
#signupForm { width: 670px; }
#signupForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
	float:left;
}


</style>
</head>

<body>

<form class="cmxform" id="signupForm" method="get" action="">
	<fieldset>
		<legend>Validación de un formulario utilizando jQuery</legend>
		<p>
			<label for="firstname">Nombre</label>
			<input id="firstname" name="firstname" />
		</p>
		<p>

			<label for="lastname">Apellidos</label>
			<input id="lastname" name="lastname" />
		</p>
		<p>
			<label for="username">Nombre de Usuario</label>
			<input id="username" name="username" />
		</p>
		<p>

			<label for="password">Password</label>
			<input id="password" name="password" type="password" />
		</p>
		<p>
			<label for="confirm_password">Repite password</label>
			<input id="confirm_password" name="confirm_password" type="password" />
		</p>
		<p>

			<label for="email">Email</label>
			<input id="email" name="email" />
		</p>
        <p>
			<label for="age">Edad</label>
			<input id="age" name="age" />
		</p>
        <p>

        	<label for="year">Año de nacimiento</label>
			<input id="year" name="year" />        	
        </p>
		<p>
			<label for="agree">Acepta las politicas de privacidad:</label>
			<input type="checkbox" class="checkbox" id="agree" name="agree" />
        </p>
        <p>

			<input class="submit" type="submit" value="Submit"/>
		</p>
	</fieldset>
</form>
</body>
</html>
