CREATE DATABASE IF NOT EXISTS imasd;
USE imasd;
DROP TABLE IF EXISTS empleado;

CREATE TABLE IF NOT EXISTS empleado( 
empleado_id INTEGER primary key AUTO_INCREMENT, 
empleado_nombre text not null, 
empleado_apellidoPaterno text not null, 
empleado_apellidoMaterno text, 
empleado_telefono text not null, 
empleado_fechaNacimiento text not null, 
empleado_area int not null,
empleado_email text 
) charset=utf8mb4;