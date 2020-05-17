package eu.mrndesign.matned.jdbc.test;

import eu.mrndesign.matned.jdbc.test.model.Student;

public class Main {
    public static void main(String[] args) {

        StudentDao dao = new StudentDao();
//        Student student = Student.builder()
//               .name("Lech")
//               .surname("Kaczynski")
//               .average(2.6)
//               .age(60)
//                .isDead(true)
//                .id(4L)
//               .build();
        long studentId = 3L;
//        dao.addToDatabase(student);
        System.out.println(dao.showAll());
        dao.delete(studentId);
        System.out.println(dao.showAll());





    }
}
