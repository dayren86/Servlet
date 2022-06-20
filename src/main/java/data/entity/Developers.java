package data.entity;

import lombok.*;

@Data

@NoArgsConstructor
public class Developers {
    private int id;
    private String name;
    private int age;
    private Sex sex;
    private int salary;

    public Developers(String name, int age, Sex sex, int salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }
    public enum Sex {
        male,
        female,
        unknown
    }
}
