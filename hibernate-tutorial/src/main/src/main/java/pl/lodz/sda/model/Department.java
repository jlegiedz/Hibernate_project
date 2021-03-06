package pl.lodz.sda.model;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    Long id;

    @Column(name = "name")
    String name;

    @Embedded
    DepartmentAddress departmentAddress;

    //The @JoinColumn annotation is used to specify the FOREIGN KEY column used when joining an entity association or an embeddable collection.
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    Company company;

    public Department() {
    }

    public Department(Company company) {
        this.company = company;
    }

    public Department(Long id, String name, Company company, DepartmentAddress departmentAddress) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.departmentAddress=departmentAddress;
    }

    public DepartmentAddress getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(DepartmentAddress departmentAddress) {
        this.departmentAddress = departmentAddress;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentAddress=" + departmentAddress +
                '}';
    }
}
