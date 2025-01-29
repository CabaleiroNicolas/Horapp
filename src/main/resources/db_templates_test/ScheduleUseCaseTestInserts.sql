-- SAVE --

INSERT INTO courses (id_course, course_name, deleted) VALUES
 (12000 ,'FISICA 2', false);

INSERT INTO schedules (id_schedule, course_group, id_course) VALUES
(150000, '3K1', 12000);

-- FIND BY ID --

INSERT INTO courses (id_course, course_name, deleted) VALUES
 (13000 ,'Arquitectura de Computadoras', false);

INSERT INTO schedules (id_schedule, course_group, id_course) VALUES
(140000, '1K5', 13000);

INSERT INTO days_and_times (id_day_and_time, end_time, start_time, id_schedule, day_of_week) VALUES
 (100001, '18:00', '16:00', 140000, 'MONDAY');

