
-- SAVE ----

INSERT INTO courses (id_course, course_name, deleted) VALUES
 (100000 ,'AM1', false);

INSERT INTO schedules (id_schedule, course_group, id_course) VALUES
 (100000, '1K1', 100000);

---------
-- FIND BY ID -------

INSERT INTO courses (id_course, course_name, deleted) VALUES
 (100001 ,'AM1', false);

INSERT INTO schedules (id_schedule, course_group, id_course) VALUES
 (100001, '1K1', 100000);

INSERT INTO days_and_times (id_day_and_time, end_time, start_time, id_schedule, day_of_week) VALUES
 (100000, '10:00', '08:00', 100001, 'MONDAY');

-------


