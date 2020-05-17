package eu.mrndesign.matned.hibernate.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="student")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_surname")
    private String surname;

    @Column(name="student_average")
    private double average;

    @Column(name="student_age")
    private int age;

    @Column(name = "dead")
    private boolean isDead;

    @Enumerated(value = EnumType.STRING)
    private Behaviour behaviour;


}
