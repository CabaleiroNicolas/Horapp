-- SAVE --

INSERT INTO users(id_user, lastname, name, username, email, enabled, account_non_locked, role, password)
VALUES (100000, 'Alderete', 'Matias', 'matiias4k', 'matias@mail.com', true, true, 'STUDENT', '$2a$10$NfltMVmneX9MbbwCKBVoo.VcWViS85DwNGGlJdJYiFAsUk4ofycPq');

-- FIND BY ID --

INSERT INTO users(id_user, lastname, name, username, email, enabled, account_non_locked, role, password)
VALUES (100001, 'Ochoa', 'Ivan', 'Ivaan8a', 'ivan@mail.com', true, true, 'STUDENT', '$2a$10$NfltMVmneX9MbbwCKBVoo.VcWViS85DwNGGlJdJYiFAsUk4ofycPq');

INSERT INTO majors(id_major, major_name, id_user, deleted) VALUES
(100001, 'ISI', 100001, false);

-- DELETE BY ID --

INSERT INTO users(id_user, lastname, name, username, email, enabled, account_non_locked, role, password)
VALUES (100002, 'Ochoa', 'Guillermo', 'Memo8a', 'guille@mail.com', true, true, 'STUDENT', '$2a$10$NfltMVmneX9MbbwCKBVoo.VcWViS85DwNGGlJdJYiFAsUk4ofycPq');

INSERT INTO majors(id_major, major_name, id_user, deleted) VALUES
(100002, 'MECANICA', 100002, false);

-- FIND ALL BY USER --

INSERT INTO users(id_user, lastname, name, username, email, enabled, account_non_locked, role, password)
VALUES (100003, 'Cabaleiro', 'Nicolas', 'NicoCab', 'nicolas@mail.com', true, true, 'STUDENT', '$2a$10$NfltMVmneX9MbbwCKBVoo.VcWViS85DwNGGlJdJYiFAsUk4ofycPq');

INSERT INTO majors(id_major, major_name, id_user, deleted) VALUES
(100003, 'ELECTRICA', 100003, false);