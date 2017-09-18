package pl.lodz.sda.exercise.collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.dao.Company;
import pl.lodz.sda.dao.Department;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

import java.util.HashSet;
import java.util.Set;

public class CollectionTest {

    public static void main(String[] args) {

        Company company = new Company();
        company.setName("test");

        Department dp = new Department(company);
        Department dp2 = new Department(company);
        Set<Department> dps = new HashSet<>();
        dps.add(dp);
        dps.add(dp2);
        company.setDepartment(dps);

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateSessionFactory.createSession(DB.H2);
            tx = session.beginTransaction();

            session.save(company);
            tx.commit();
            tx = session.beginTransaction();
            session.save(dp);
            session.save(dp2);
            tx.commit();
            System.out.println(company);
            System.out.println(dp);
            System.out.println(dp2);
        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeAll(session);
        }

    }
}
