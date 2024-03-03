INSERT INTO privilege(icon, name, url)
VALUES ('report', 'Reportar incidente', 'reportar-incidente');
INSERT INTO privilege(icon, name, url)
VALUES ('manage_accounts', 'Gestionar usuarios', 'gestionar-usuarios');
INSERT INTO privilege(icon, name, url)
VALUES ('flag_circle', 'Mis reportes', 'mis-reportes');
INSERT INTO privilege(icon, name, url)
VALUES ('person', 'Actualizar información', 'actualizar-informacion');
INSERT INTO privilege(icon, name, url)
VALUES ('summarize', 'Incidentes reportados', 'incidentes-reportados');
INSERT INTO privilege(icon, name, url)
VALUES ('confirmation_number', 'Mis tickets', 'mis-tickets');
INSERT INTO privilege(icon, name, url)
VALUES ('person_add', 'Crear usuario', 'crear-usuario');
INSERT INTO privilege(icon, name, url)
VALUES ('confirmation_number', 'Ver tickets', 'ver-tickets');
INSERT INTO privilege(icon, name, url)
VALUES ('add_business', 'Agregar empresa', 'agregar-empresa');

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
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (8, 1);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (9, 1);

-- Insertar privilegios por rol administrador
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (2, 2);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (7, 2);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (8, 2);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (9, 2);

-- Insertar privilegios por rol cliente
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (1, 3);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (3, 3);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (4, 3);

-- Insertar privilegios por rol trabajador
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (5, 4);
INSERT INTO role_privilege(id_privilege, id_role)
VALUES (6, 4);

