<?php
include_once("cone.php");
$conex=conBD();

$opcion=json_decode($_POST['empleados'],true);

$cuentaCorrectos=0;
//guardarEntrada($_POST['empleados']);
foreach ($opcion as $key => $value) :
	guardarEntrada(json_encode($value));
	$empleadoId=$value['empleado_id'];
	$empleadoNombre=$value['empleado_nombre'];
	$empleadoApellidoPaterno=$value['empleado_apellidoPaterno'];
	$empleadoApellidoMaterno=$value['empleado_apellidoMaterno'];
	$empleadoTelefono=$value['empleado_telefono'];
	$empleadoFechaNacimiento=$value['empleado_fechaNacimiento'];
	$empleadoArea=$value['empleado_area'];
	$empleadoEmail=$value['empleado_email'];

	if(existeEmpleado($conex,$empleadoId)){
		$verifica=actualizaEmpleado($conex,$empleadoId, $empleadoNombre, $empleadoApellidoPaterno, $empleadoApellidoMaterno, $empleadoTelefono, $empleadoFechaNacimiento, $empleadoArea, $empleadoEmail);
	}else{
		$verifica=insertaEmpleado($conex,$empleadoId, $empleadoNombre, $empleadoApellidoPaterno, $empleadoApellidoMaterno, $empleadoTelefono, $empleadoFechaNacimiento, $empleadoArea, $empleadoEmail);
	}

	if($verifica){
		$cuentaCorrectos++;
	}
endforeach;
guardarEntrada(json_encode($opcion[0]));
$respuesta['estado']=1;
$respuesta['mensaje']="Se sincronizo ".$cuentaCorrectos." registro(s) correctamente";/**/



echo json_encode($respuesta);
mysqli_close($conex);

function existeEmpleado($conex,$empleadoId){
	$datosJson= array();
	$selecLista="SELECT empleado_id
				FROM empleado 
				WHERE empleado_id = $empleadoId";
	$qSelectLista=mysqli_query($conex,$selecLista);
	if(mysqli_num_rows($qSelectLista)>0){
		return true;
	}
	return false;
}

function insertaEmpleado($conex,$empleadoId, $empleadoNombre, $empleadoApellidoPaterno, $empleadoApellidoMaterno, $empleadoTelefono, $empleadoFechaNacimiento, $empleadoArea, $empleadoEmail){
	//$datosJson= array();
	$insertEmpleado="INSERT INTO empleado(empleado_id, empleado_nombre, empleado_apellidoPaterno, empleado_apellidoMaterno, empleado_telefono, empleado_fechaNacimiento, empleado_area, empleado_email) VALUES($empleadoId, '$empleadoNombre', '$empleadoApellidoPaterno', '$empleadoApellidoMaterno', '$empleadoTelefono', '$empleadoFechaNacimiento', $empleadoArea, '$empleadoEmail');";
	$qInsertEmpleado=mysqli_query($conex,$insertEmpleado);
	if(!$qInsertEmpleado){
		return false;
	}
	return true;
}

function actualizaEmpleado($conex, $empleadoId, $empleadoNombre, $empleadoApellidoPaterno, $empleadoApellidoMaterno, $empleadoTelefono, $empleadoFechaNacimiento, $empleadoArea, $empleadoEmail){
	$datosJson= array();
	$updateEmpleado="UPDATE empleado 
					 SET empleado_nombre='$empleadoNombre',
					 	 empleado_apellidoPaterno='$empleadoApellidoPaterno', 
					 	 empleado_apellidoMaterno='$empleadoApellidoMaterno', 
					 	 empleado_telefono='$empleadoTelefono', 
					 	 empleado_fechaNacimiento='$empleadoFechaNacimiento', 
					 	 empleado_area=$empleadoArea, 
					 	 empleado_email='$empleadoEmail'
					 WHERE empleado_id=$empleadoId;";
	$qUpdateEmpleado=mysqli_query($conex,$updateEmpleado);
	if(!$qUpdateEmpleado){
		return false;
	}
	return true;
}

function guardarEntrada($contenido){
	$archivo = fopen('archivo.txt','a');
	fputs($archivo,$contenido);
	fclose($archivo);
}

?>