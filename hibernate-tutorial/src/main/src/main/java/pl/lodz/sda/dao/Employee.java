package pl.lodz.sda.dao;

import javax.persistence.*;

/**
 * Created by asia on 19/09/17.
 */
@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type") // kolumna ybedzie zawierac albo contractemployee albo regularemployee
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    int id;

    @Column
    String name;

    public Employee(String name){

    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
