package leesh.study.hellorest.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name="student")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "major")
    private String major;

}
