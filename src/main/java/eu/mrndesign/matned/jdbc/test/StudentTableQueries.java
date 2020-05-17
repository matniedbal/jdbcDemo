package eu.mrndesign.matned.jdbc.test;

public interface StudentTableQueries {
    String CREATE_DATABASE_QUERY = "create database if not exists `jwro27_students_jdbc`;";
    String CREATE_TABLE_QUERY = "" +
            "drop table if exists `student`; \n" +
            "create table `student` (\n" +
            "`student_id` int NOT NULL AUTO_INCREMENT,\n" +
            "`name` varchar(30) NOT NULL,\n" +
            "`surname` varchar(30) NOT NULL,\n" +
            "`avarage` decimal(10,2) DEFAULT NULL,\n" +
            "`age` int DEFAULT NULL,\n" +
            "`isDead` boolean DEFAULT 0,\n" +
            "PRIMARY KEY (`student_id`)\n" +
            ");";
    String INSERT_STUDENT_QUERY = "insert into `student` (`name`, `surname`, `average`, `age`, `dead`) values ( ?, ?, ?, ?, ?);";

    String SELECT_ALL_QUERY = "SELECT * FROM student;";

    String UPDATE_STUDENT_QUERY = "" +
            "UPDATE `student` SET " +
            "`name` = ? ," +
            "`surname` = ?, " +
            "`average` = ?, " +
            "`age` = ?, " +
            "`dead` = ? " +
            "WHERE `student_id` = ?;";
        String DELETE_STUDENT_QUERY = "delete from `student` where `student_id` = ?;";
}
