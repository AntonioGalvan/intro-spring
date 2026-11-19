UPDATE usuarios
SET id_rol = (SELECT id_rol FROM roles WHERE nombre_rol = 'admin')
WHERE correo = 'admin@correo.com';