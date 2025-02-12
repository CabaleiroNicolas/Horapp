-- FIND BY ID --

INSERT INTO users (id_user, lastname, name, username, email, enabled, role, password) VALUES
(100000, 'Alderete', 'Matias', 'MatiiasAld', 'matiasAld@mail.com', true, 'STUDENT', '$2a$10$NfltMVmneX9MbbwCKBVoo.VcWViS85DwNGGlJdJYiFAsUk4ofycPq');

INSERT INTO time_tables (id_time_table, id_user, deleted) VALUES
(100000, 100000, false);

INSERT INTO majors( id_major, major_name, id_user, deleted) VALUES
(100000, 'ISI', 100000, false);

INSERT INTO courses(id_course, course_name, id_major, id_time_table, id_user, deleted) VALUES
(100000, 'ALGEBRA', 100000, 100000, 100000, false);

-- DELETE BY ID --

INSERT INTO users (id_user, lastname, name, username, email, enabled, role, password) VALUES
(100001, 'Agullo', 'Keren', 'KerenAgMe', 'keren@mail.com', true, 'STUDENT', '$2a$10$NfltMVmneX9MbbwCKBVoo.VcWViS85DwNGGlJdJYiFAsUk4ofycPq');

INSERT INTO time_tables (id_time_table, id_user, deleted) VALUES
(100001, 100001, false);

INSERT INTO majors( id_major, major_name, id_user, deleted) VALUES
(100001, 'Ing Mecanica', 100001, false);

INSERT INTO courses(id_course, course_name, id_major, id_time_table, id_user, deleted) VALUES
(100001, 'ANALISIS', 100001, 100001, 100001, false);


