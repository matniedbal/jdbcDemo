package eu.mrndesign.matned.jdbc.test;

import eu.mrndesign.matned.jdbc.test.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {

    private MysqlConnection mysqlConnection;

    public StudentDao() {
        this.mysqlConnection = new MysqlConnection();

    }

    public void addToDatabase(Student student){
        //połącz
        try {
            Connection connection = mysqlConnection.getConnection();

            //otwarcie zapytania
            PreparedStatement statement = connection.prepareStatement(StudentTableQueries.INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.setDouble(3,student.getAverage());
            statement.setInt(4,student.getAge());
            statement.setBoolean(5,student.isDead());

            // 3. realizacja zapytnia
            // ile zostało edytowanych rekordów
            int affectedRecords = statement.executeUpdate();

            // todo: trzeba jeszcze odebrać identyfikator wygenerowanego rekordu.
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //przetworzenie - parsowanie zapytania
        // sprzątnięcie
    }

    public List<Student> showAll(){
        List<Student> list = new ArrayList<>();
        try(Connection connection = mysqlConnection.getConnection()){

            try(PreparedStatement statement = connection.prepareStatement(StudentTableQueries.SELECT_ALL_QUERY)){
                ResultSet resultSet = statement.executeQuery();


                while(resultSet.next()){
                    Student newStudent = Student.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .surname(resultSet.getString(3))
                            .average(resultSet.getDouble(4))
                            .age(resultSet.getInt(5))
                            .isDead(resultSet.getBoolean(6))
                            .build();

                    list.add(newStudent);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return list;
    }

    public void update(Student student){
        try(Connection connection = mysqlConnection.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(StudentTableQueries.UPDATE_STUDENT_QUERY)){
                statement.setString(1,student.getName());
                statement.setString(2,student.getSurname());
                statement.setDouble(3,student.getAverage());
                statement.setInt(4,student.getAge());
                statement.setBoolean(5,student.isDead());

                statement.setLong(6, student.getId());
                int affectedRecords = statement.executeUpdate();
                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void delete(Student student){
        if (student.getId() == null) {
            System.err.println("Can't edit student without id.");
            return;
        }
        delete(student.getId());
    }


    public void delete(Long studentId){
        try(Connection connection = mysqlConnection.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(StudentTableQueries.DELETE_STUDENT_QUERY)){
                statement.setLong(1, studentId);
                int affectedRecords = statement.executeUpdate();
                System.out.println("Usuniętych rekordów: " + affectedRecords);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
