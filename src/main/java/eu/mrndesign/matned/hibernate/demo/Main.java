package eu.mrndesign.matned.hibernate.demo;

import eu.mrndesign.matned.hibernate.demo.model.Behaviour;
import eu.mrndesign.matned.hibernate.demo.model.Student;

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
                dao.select();
            }else if(komenda.equalsIgnoreCase("delete")){

            }else if(komenda.equalsIgnoreCase("update")){
            }
        }while (!komenda.equalsIgnoreCase("quit"));
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