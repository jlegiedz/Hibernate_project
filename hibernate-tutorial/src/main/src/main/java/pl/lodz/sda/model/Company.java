package pl.lodz.sda.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company implements Serializable{           //byz moc zrobic serializacje z jsona do javy i w druga strone

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "name")
    String name;

    // majac cascade: jesli usuwamy company- najpierw usunie departmnety tej company a potem company-> CollectioTest->Main
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Department> department = new HashSet<>();

    // mappedBy wymagany przy relacji dwukierunkowej
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // company to pole javove
    @JoinColumn//(name = "address_id")
    private Address address;

    public Company() {
    }

    public Company(String name, Set<Department> department, Address address) {
        this.name = name;
        this.department = department;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", address=" + getAddress() +
                '}';
    }
}
