
-- SEQUENCES --

CREATE SEQUENCE IF NOT EXISTS public.categories_fb_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.majors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.time_tables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.schedules_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.days_and_times_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.feedbacks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- SCHEMAS --

    -- usuarios (alumno y admin)

CREATE TABLE public.users
(
      id_user               BIGINT PRIMARY KEY NOT NULL,
      lastname              VARCHAR(255) DEFAULT NULL,
      name                  VARCHAR(255) DEFAULT NULL,
      username              VARCHAR(255) NOT NULL UNIQUE,
      email                 VARCHAR(255) NOT NULL UNIQUE,
      enabled               BOOLEAN NOT NULL DEFAULT true,
      account_non_locked    BOOLEAN NOT NULL DEFAULT true,
      role VARCHAR(100)     NOT NULL,
      password VARCHAR(255) NOT NULL
);

ALTER TABLE public.users ALTER COLUMN id_user SET DEFAULT nextval('users_id_seq');
ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id_user;


    -- carreras
CREATE TABLE public.majors
(
      id_major              BIGINT PRIMARY KEY NOT NULL,
      major_name            VARCHAR(255) DEFAULT NULL,
      id_user               BIGINT DEFAULT NULL,
      deleted               BOOLEAN NOT NULL
);
ALTER TABLE public.majors ALTER COLUMN id_major SET DEFAULT nextval('majors_id_seq');
ALTER SEQUENCE public.majors_id_seq OWNED BY public.majors.id_major;


    -- horarios armados
CREATE TABLE public.time_tables
(
      id_time_table         BIGINT PRIMARY KEY NOT NULL,
      id_user               BIGINT NOT NULL,
      deleted               BOOLEAN NOT NULL
);
ALTER TABLE public.time_tables ALTER COLUMN id_time_table SET DEFAULT nextval('time_tables_id_seq');
ALTER SEQUENCE public.time_tables_id_seq OWNED BY public.time_tables.id_time_table;


    -- materias
CREATE TABLE public.courses
(
      id_course             BIGINT PRIMARY KEY NOT NULL,
      course_name           VARCHAR(255) DEFAULT NULL,
      id_major              BIGINT DEFAULT NULL,
      id_time_table         BIGINT DEFAULT NULL,
      id_user               BIGINT DEFAULT NULL,
      deleted               BOOLEAN NOT NULL
);
ALTER TABLE public.courses ALTER COLUMN id_course SET DEFAULT nextval('courses_id_seq');
ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id_course;


    -- comisiones
CREATE TABLE public.schedules
(
      id_schedule           BIGINT PRIMARY KEY NOT NULL,
      course_group          VARCHAR(255)  NOT NULL,
      id_course             BIGINT NOT NULL
);
ALTER TABLE public.schedules ALTER COLUMN id_schedule SET DEFAULT nextval('schedules_id_seq');
ALTER SEQUENCE public.schedules_id_seq OWNED BY public.schedules.id_schedule;


    -- horarios de comisiones
CREATE TABLE public.days_and_times
(
      id_day_and_time       BIGINT PRIMARY KEY NOT NULL,
      end_time              TIME NOT NULL,
      start_time            TIME NOT NULL,
      id_schedule           BIGINT NOT NULL,
      day_of_week           VARCHAR(225) DEFAULT NULL,
      CONSTRAINT day_check CHECK
      (((day_of_week)::TEXT = ANY ((ARRAY['MONDAY'::VARCHAR, 'TUESDAY'::VARCHAR,'WEDNESDAY'::VARCHAR,'THURSDAY'::VARCHAR,'FRIDAY'::VARCHAR,'SATURDAY'::VARCHAR])::TEXT[])))

);
ALTER TABLE public.days_and_times ALTER COLUMN id_day_and_time SET DEFAULT nextval('days_and_times_id_seq');
ALTER SEQUENCE public.days_and_times_id_seq OWNED BY public.days_and_times.id_day_and_time;


    -- retroalimentacion de los usuarios
CREATE TABLE public.feedbacks
(
      id_feedback           BIGINT PRIMARY KEY NOT NULL,
      description_name      VARCHAR(255) DEFAULT NULL,
      id_course             BIGINT DEFAULT NULL
);
ALTER TABLE public.feedbacks ALTER COLUMN id_feedback SET DEFAULT nextval('feedbacks_id_seq');
ALTER SEQUENCE public.feedbacks_id_seq OWNED BY public.feedbacks.id_feedback;


    -- categoria de la retroalimentacion
CREATE TABLE public.categories_fb
(
      id_category             BIGINT PRIMARY KEY NOT NULL,
      category_name           VARCHAR(255) NOT NULL,
      description_name        VARCHAR(255) NOT NULL,
      deleted                 BOOLEAN NOT NULL
);
ALTER TABLE public.categories_fb ALTER COLUMN id_category SET DEFAULT nextval('categories_fb_id_seq');
ALTER SEQUENCE public.categories_fb_id_seq OWNED BY public.categories_fb.id_category;


    -- tabla intemedia de relacion N a N de el feedback con su categoria
CREATE TABLE public.feedback_category_relationship
(
      id_category             BIGINT NOT NULL,
      id_feedback             BIGINT NOT NULL
);


-- FOREIGN KEYS --

ALTER TABLE public.time_tables
    ADD CONSTRAINT FK_USER_ON_TIME_TABLE FOREIGN KEY (id_user) REFERENCES public.users (id_user);
    
ALTER TABLE public.majors
    ADD CONSTRAINT FK_USER_ON_MAJOR FOREIGN KEY (id_user) REFERENCES public.users (id_user);
    
ALTER TABLE public.courses
    ADD CONSTRAINT FK_MAJOR_ON_COURSE FOREIGN KEY (id_major) REFERENCES public.majors (id_major);
    
ALTER TABLE public.courses
    ADD CONSTRAINT FK_TIME_TABLE_ON_COURSE FOREIGN KEY (id_time_table) REFERENCES public.time_tables (id_time_table);
    
ALTER TABLE public.courses
    ADD CONSTRAINT FK_USER_ON_COURSE FOREIGN KEY (id_user) REFERENCES public.users (id_user);
    
ALTER TABLE public.schedules
    ADD CONSTRAINT FK_COURSE_ON_SCHEDULE FOREIGN KEY (id_course) REFERENCES public.courses (id_course);
    
ALTER TABLE public.days_and_times
    ADD CONSTRAINT FK_SCHEDULE_ON_DAY_AND_TIME FOREIGN KEY (id_schedule) REFERENCES public.schedules (id_schedule);  
    
ALTER TABLE public.feedbacks
    ADD CONSTRAINT FK_COURSE_ON_FEEDBACK FOREIGN KEY (id_course) REFERENCES public.courses (id_course);
    
ALTER TABLE public.feedback_category_relationship
    ADD CONSTRAINT FK_CATEGORY_ON_FEEDBACK FOREIGN KEY (id_feedback) REFERENCES public.feedbacks (id_feedback);
    
ALTER TABLE public.feedback_category_relationship
    ADD CONSTRAINT FK_FEEDBACK_ON_CATEGORY FOREIGN KEY (id_category) REFERENCES public.categories_fb (id_category);
    
             