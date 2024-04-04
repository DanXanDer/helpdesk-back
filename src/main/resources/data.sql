/*-- Privilegios de administrador
INSERT INTO privilege(icon, authority, url)
VALUES ('corporate_fare', 'Empresas', 'empresas');
INSERT INTO privilege(icon, authority, url)
VALUES ('manage_accounts', 'Usuarios', 'gestionar-usuarios');
INSERT INTO privilege(icon, authority, url)
VALUES ('confirmation_number', 'Historial de tickets', 'historial-tickets');

--Privilegios de cliente
INSERT INTO privilege(icon, authority, url)
VALUES ('report', 'Reportar incidente', 'reportar-incidente');
INSERT INTO privilege(icon, authority, url)
VALUES ('flag_circle', 'Mis reportes', 'mis-reportes');

INSERT INTO privilege(icon, authority, url)
VALUES ('confirmation_number', 'Tickets', 'tickets');

--Privilegios en común
INSERT INTO privilege(icon, authority, url)
VALUES ('person', 'Actualizar información', 'actualizar-informacion');

-- Insertar roles
INSERT INTO role(name, description)
VALUES ('Superadministrador', 'Rol de superadministrador');
INSERT INTO role(name, description)
VALUES ('Administrador', 'Rol de administrador');
INSERT INTO role(name, description)
VALUES ('Cliente', 'Rol de cliente');
INSERT INTO role(name, description)
VALUES ('Trabajador', 'Rol de trabajador');


-- Insertar privilegios por rol superadministrador
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (1, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (2, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (3, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (4, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (5, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (6, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (7, 1);

-- Insertar privilegios por rol administrador
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (1, 2);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (2, 2);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (3, 2);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (7, 2);

-- Insertar privilegios por rol cliente
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (4, 3);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (5, 3);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (7, 3);

-- Insertar privilegios por rol trabajador
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (6, 4);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (7, 4);

-- Insertar preguntas secretas
INSERT INTO secret_question(name)
VALUES ('¿Cuál es el nombre de tu mascota?');
INSERT INTO secret_question(name)
VALUES ('¿Cuál es tu comida favorita?');
INSERT INTO secret_question(name)
VALUES ('¿Cuál es tu color favorito?');
INSERT INTO secret_question(name)
VALUES ('¿Cuál es tu película favorita?');
INSERT INTO secret_question(name)
VALUES ('¿Cuál es tu libro favorito?');



*/