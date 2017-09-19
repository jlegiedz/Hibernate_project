package pl.lodz.sda.exercise.collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.dao.Address;
import pl.lodz.sda.dao.Company;
import pl.lodz.sda.dao.Department;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

import java.util.HashSet;
import java.util.Set;

public class CollectionTest {

    public static void main(String[] args) {

        Address address = new Address("Piotrkowska", 101, " 90-000", "Poland",null);
        Company company = new Company();
        company.setName("test");
        company.setAddress(address);
        address.setCompany(company);

        //tworze departament mwiac mu do jakiej company nalezy
        Department dp = new Department(company);
        Department dp2 = new Department(company);
        Set<Department> dps = new HashSet<>();
        dps.add(dp);
        dps.add(dp2);
        // doddaje do setu departmentow
        company.setDepartment(dps);

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateSessionFactory.createSession(DB.H2);
            tx = session.beginTransaction();

            //insert- save robi tu insert
            System.out.println("save");
            // majac kaskade na company jak wywolam save na company to doda mi sie adres i departmenty zasetowane na tej company
            session.save(company);
//            session.save(address);
//            session.save(dp);
//            session.save(dp2);

            tx.commit();

//            tx = session.beginTransaction();
//            //update
//            company.setName(company.getName() + Math.random());
//            session.save(dp);
//            session.save(dp2);
//            tx.commit();

            System.out.println("delete");
            tx = session.beginTransaction();
            session.delete(company);
            tx.commit();

            System.out.println("COMPANY: "+ company);
            System.out.println("DP:" + dp);
            System.out.println("DP2: " + dp2);
        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeAll(session);
        }

    }
}
