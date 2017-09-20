package pl.lodz.sda.dao;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by asia on 20/09/17.
 */
@Entity
//@DiscriminatorValue("contractemployee")
public class ContractEmployee extends Employee {

    @Column(name = "hour_rate")
    int hourRate;

    public ContractEmployee(){

    }

    public ContractEmployee(String name, int hourRate) {
        super(name);
        this.hourRate = hourRate;
    }

    public int getHourRate() {
        return hourRate;
    }

    public void setHourRate(int hourRate) {
        this.hourRate = hourRate;
    }

    @Override
    public String toString() {
        return "ContractEmployee{" +
                "id=" + id +
                ", hourRate=" + hourRate +
                ", name='" + name + '\'' +
                '}';
    }
}
