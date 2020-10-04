<?php
define("HOSTNAME", "localhost");// Nombre del host
//define("PORT", "63342");// Número del puerto [ Opcional ]
define("DATABASE", "imasd"); // Nombre de la base de datos
define("USERNAME", "root"); // Nombre del usuario
define("PASSWORD", "root"); // Constraseña
	function conBD(){
		$conexion= mysqli_connect(HOSTNAME, USERNAME, PASSWORD, DATABASE);
		if(!$conexion){
			echo "Ocurrio un error";
		}
		return $conexion;
	} 
?>