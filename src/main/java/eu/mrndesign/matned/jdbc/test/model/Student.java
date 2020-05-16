package eu.mrndesign.matned.jdbc.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;
    private String name;
    private String surname;
    private double average;
    private int age;
    private boolean isPresent;


}
