package pl.lodz.sda.dao;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by asia on 20/09/17.
 */
@Entity
// bez PrimaryKeyJoinColumn Hibertate sam doda klucz obcy
//@PrimaryKeyJoinColumn(name="ID") // podana informacja po czym join
public class RegularEmployee extends Employee{

    @Column(name = "salary")
    int salary;


    public RegularEmployee(){

    }

    public RegularEmployee(String name) {
        super(name);
    }

    public RegularEmployee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "RegularEmployee{" +
                "id=" + id +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}
