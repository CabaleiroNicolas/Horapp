-- SAVE ----

INSERT INTO courses (id_course, course_name, deleted) VALUES
 (100010 ,'ARCON', false);

 INSERT INTO categories_fb (id_category, category_name, description_name, deleted) VALUES
 (300004, 'Nombre de Curso', 'El nombre del curso esta mal escrito', false);

 INSERT INTO feedbacks (id_feedback, description_name, id_course) VALUES
 (400000, 'El nombre correcto es ARCOM', 100010);

 INSERT INTO feedback_category_relationship (id_category, id_feedback) VALUES
 (300004, 400000);