
CREATE TABLE clientes (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    correo VARCHAR(45) NOT NULL,
    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE
);

SELECT * FROM clientes;
----------------------------
SELECT c.id_cliente, c.nombre, c.apellido,
	c.correo, c.fecha_registro
FROM clientes as c

INSERT INTO clientes (nombre, apellido, correo, fecha_registro)
	VALUES ('claudio', 'HDZ', 'clau@gmail.com', '2024-04-28'),
		('luis', 'HDZ', 'luis@gmail.com', '2024-04-28'),
		('jose', 'HDZ', 'jose@gmail.com', '2024-04-28'),
		('x', 'HDZ', 'x@gmail.com', '2024-04-28');