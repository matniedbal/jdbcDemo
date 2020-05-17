package eu.mrndesign.matned.hibernate.demo;

import eu.mrndesign.matned.hibernate.demo.model.Behaviour;
import eu.mrndesign.matned.hibernate.demo.model.Student;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // trzeba mieć stworzoną bazę i tabelę
        StudentDao dao = new StudentDao();
        Scanner scanner = new Scanner(System.in);
        String komenda;
        do{
            // https://pl.spoj.com/
            System.out.println("Podaj komendę [add/list/delete/update/quit]");
            komenda = scanner.nextLine();
            if(komenda.equalsIgnoreCase("add")){
                addStudents(dao, scanner);
            }else if(komenda.equalsIgnoreCase("list")){
                getAll(dao);
            }else if(komenda.equalsIgnoreCase("delete")){
                detele(dao, scanner);
            }else if(komenda.equalsIgnoreCase("update")){
                updateStudent(dao,scanner);
            }
        }while (!komenda.equalsIgnoreCase("quit"));
    }

    private static void updateStudent(StudentDao dao, Scanner scanner) {
        System.out.println("Podaj parametr: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());
        Optional<Student> studentOptional = dao.findById(id);   // szukamy studenta
        if (studentOptional.isPresent()) {                       // jeśli uda się go odnaleźć
            Student student = studentOptional.get();            // wyciągamy studenta z Optional (Box, opakowanie)
            System.out.println("Próbujesz edytować rekord: " + student);
            System.out.println("Podaj parametry: IMIE NAZWISKO ŚREDNIA WIEK ŻYWY ZACHOWANIE");
            String linia = scanner.nextLine();
            String[] slowa = linia.split(" ");
            student = Student.builder()
                    .name(slowa[0])
                    .surname(slowa[1])
                    .average(Double.parseDouble(slowa[2]))
                    .age(Integer.parseInt(slowa[3]))
                    .isDead(Boolean.parseBoolean(slowa[4]))
                    .behaviour(Behaviour.valueOf(slowa[5].toUpperCase()))
                    .id(id)
                    .build();
            dao.saveOrUpdate(student);
        } else {
            System.err.println("Error, student z takim id nie istnieje.");
        }
    }

    private static void detele(StudentDao dao, Scanner scanner) {
        System.out.println("Podaj id do usunięcia: ");
        // nie da się usunąć rekordu po id (bezpośrednio z sesji)System.out.println("Podaj parametry: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());
        Optional<Student> studentOptional = dao.findById(id);   // szukamy studenta
        if(studentOptional.isPresent()) {                       // jeśli uda się go odnaleźć
            Student student = studentOptional.get();            // wyciągamy studenta z Optional (Box, opakowanie)
            dao.delete(student);                                // przekazujemy do usunięcia
        }
    }

    private static void getAll(StudentDao dao) {
        for (Student el: dao.getAll()) {
            System.out.println(el);
        }
    }


    private static void addStudents(StudentDao dao, Scanner scanner) {
        System.out.println("Podaj parametry: IMIE NAZWISKO ŚREDNIA WIEK ŻYWY ZACHOWANIE");
        String linia = scanner.nextLine();
        String[] slowa = linia.split(" ");
        Student student = Student.builder()
                .name(slowa[0])
                .surname(slowa[1])
                .average(Double.parseDouble(slowa[2]))
                .age(Integer.parseInt(slowa[3]))
                .isDead(Boolean.parseBoolean(slowa[4]))
                .behaviour(Behaviour.valueOf(slowa[5].toUpperCase()))
                .build();
        dao.saveOrUpdate(student);
    }
}