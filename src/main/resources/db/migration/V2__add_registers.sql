
insert into user (username, name ,lastname, email, deleted)
    values
        ('ivaan8a', 'ivan', 'ochoa', 'ivan@mail.com', 0),
        ('nicoCabaleiro', 'Nicolas', 'Cabaleiro', 'nicocab@mail.com', 0),
        ('matiAlderete', 'Matias', 'Alderete', 'matii4k@mail.com', 1);

insert into time_table (id_user, deleted)
    values
        (1, 0), (2, 0), (3,1);

insert into major (major_name, deleted)
    values
        ('Ingenieria en Sistemas', 0),
        ('Ingenieria Mecanica', 0),
        ('Programacion', 0);

insert into major (major_name, deleted, id_user)
    values
        ('Ingenieria en Sistemas', 0, 1),  ('Ingenieria Mecanica', 0, 2), ('Programacion', 1, 3);

insert into course (id_major, id_user, id_time_table ,course_name, deleted)
    values
        (3, 1, 1,'Algebra', 0), (3, 1, 1,'Analisis', 0), (3, 1, 1,'Economia', 0),
        (4, 2, 2,'Algebra', 0), (4, 2, 2,'Analisis', 0), (4, 2, 2,'Economia', 0);

insert into course (id_major, course_name, deleted)
    values
        (1, 'Algebra', 0), (1, 'Analisis', 0), (1,'Economia', 0);


insert into feedback (id_course, description_name)
    values
        (7, 'Buenas. Podrian cambiar este horario? La clase comienza a las 08:30 el dia lunes y termina a las 10:30 en la 1k1!'),
        (8, 'Buenas. Podrian cambiar este horario? Analisis para la comision 1k3 es Miercoles de 09:00 a 11:00 y Jueves de 10:00-12:00!'),
        (9, 'Buenas. Economia en la 3k4 es MARTES y JUEVES!!');


insert into category (category_name, description_name, deleted)
    values
        ('Horario incorrecto', 'El horario real no es como está especificado',0),
        ('Dia incorrecto', 'El dia real no es como esta especificado',0);


insert into feedback_category (id_feedback, id_category)
    values
        (1,1),
        (2, 1), (2,2),
        (3,2);


insert into schedule (id_course, course_group)
    values
        (7, '1k1'),(7, '1k2'),(7, '1k3'),(7, '1k4'),(7, '1k5'),
        (8, '1k1'),(8, '1k2'),(8, '1k3'),(8, '1k4'), (7, '1k5'),
        (9, '3k1'), (9, '3k2'), (9, '3k3'), (9, '3k4');

insert into day_and_time (start_time, end_time, id_schedule, day)
values
        ('08:00', '10:00', 1, 'MONDAY'), ('08:00', '10:00', 1, 'WEDNESDAY'),
        ('08:00', '10:00', 8, 'WEDNESDAY'), ('08:00', '10:00', 8, 'FRIDAY'),
        ('18:00', '20:00', 14, 'MONDAY'), ('19:00', '20:30', 14, 'WEDNESDAY');

