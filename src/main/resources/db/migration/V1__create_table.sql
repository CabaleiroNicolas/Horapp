DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `deleted` bit(1) NOT NULL,
  `id_category` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `description_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `deleted` bit(1) NOT NULL,
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `deleted` bit(1) NOT NULL,
  `id_major` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint DEFAULT NULL,
  `major_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_major`),
  UNIQUE KEY `UKsdua43s4hmv7vctqpkjhuike3` (`id_user`),
  CONSTRAINT `FKhjkq1e08qorgtr0lael00f9nl` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `time_table`;
CREATE TABLE `time_table` (
  `deleted` bit(1) NOT NULL,
  `id_time_table` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_time_table`),
  KEY `FKh1o5gb95snjqmvbtl1rh5v6q8` (`id_user`),
  CONSTRAINT `FKh1o5gb95snjqmvbtl1rh5v6q8` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `deleted` bit(1) NOT NULL,
  `id_course` bigint NOT NULL AUTO_INCREMENT,
  `id_major` bigint NOT NULL,
  `id_time_table` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_course`),
  KEY `FK2vc5mc2yi1ljijr98no3p3p0` (`id_major`),
  KEY `FKf5qiga7gsvfb9k29yt6wep61` (`id_time_table`),
  KEY `FKowtokydvw7cb0ognfilhot604` (`id_user`),
  CONSTRAINT `FK2vc5mc2yi1ljijr98no3p3p0` FOREIGN KEY (`id_major`) REFERENCES `major` (`id_major`),
  CONSTRAINT `FKf5qiga7gsvfb9k29yt6wep61` FOREIGN KEY (`id_time_table`) REFERENCES `time_table` (`id_time_table`),
  CONSTRAINT `FKowtokydvw7cb0ognfilhot604` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id_course` bigint NOT NULL,
  `id_schedule` bigint NOT NULL AUTO_INCREMENT,
  `course_group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_schedule`),
  KEY `FKrmvag562fnoc08wxh64bv58e5` (`id_course`),
  CONSTRAINT `FKrmvag562fnoc08wxh64bv58e5` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `day_and_time`;
CREATE TABLE `day_and_time` (
  `end_time` time DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `id_day_and_time` bigint NOT NULL AUTO_INCREMENT,
  `id_schedule` bigint NOT NULL,
  `day` enum('FRIDAY','MONDAY','SATURDAY','SUNDAY','THURSDAY','TUESDAY','WEDNESDAY') DEFAULT NULL,
  PRIMARY KEY (`id_day_and_time`),
  KEY `FK4vvt9ell3w0j0a3snivtuiu85` (`id_schedule`),
  CONSTRAINT `FK4vvt9ell3w0j0a3snivtuiu85` FOREIGN KEY (`id_schedule`) REFERENCES `schedule` (`id_schedule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id_course` bigint DEFAULT NULL,
  `id_feedback` bigint NOT NULL AUTO_INCREMENT,
  `description_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_feedback`),
  KEY `FKe0v68ccjplvvmcws3i52o8hwl` (`id_course`),
  CONSTRAINT `FKe0v68ccjplvvmcws3i52o8hwl` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `feedback_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_category` (
  `id_category` bigint NOT NULL,
  `id_feedback` bigint NOT NULL,
  KEY `FK3veketv67w7e6hwtdnb5s8g2` (`id_category`),
  KEY `FKa83632ks48h743jnvuibiv4ut` (`id_feedback`),
  CONSTRAINT `FK3veketv67w7e6hwtdnb5s8g2` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `FKa83632ks48h743jnvuibiv4ut` FOREIGN KEY (`id_feedback`) REFERENCES `feedback` (`id_feedback`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;