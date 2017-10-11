package pl.lodz.sda.exercise.collection;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import pl.lodz.sda.model.Address;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.model.Department;
import pl.lodz.sda.model.DepartmentAddress;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionTest {

    public static void main(String[] args) {

        Address address = new Address("Piotrkowska", 101, " 90-000", "Poland",null);
        Company company = new Company();
        DepartmentAddress departmentAddress= new DepartmentAddress("Pabianicka","Lodz","Poland");
        company.setName("test");
        company.setAddress(address);
        address.setCompany(company);

        //tworze departament mowiac mu do jakiej company nalezy
        Department dp = new Department(company);
        Department dp2 = new Department(company);
        Set<Department> dps = new HashSet<>();
        dps.add(dp);
        dps.add(dp2);
        // doddaje do setu departmentow
        company.setDepartment(dps);
        dp.setDepartmentAddress(departmentAddress);

        Session session = null;
        Transaction tx = null;
        SessionFactory sessionFactory =null;
        try {

            // z uzyciem metody createSessionFactory
            sessionFactory = HibernateSessionFactory.createSessionFactory(DB.H2);
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            System.out.println("save");
            session.save(company);
            System.out.println(company.getDepartment());
            tx.commit();

            session = sessionFactory.openSession();
            session.beginTransaction();
            Company company1 = session.get(Company.class,1l);
            company1.getDepartment();
            session.getTransaction().commit();
            //jak jest cos lazy i zamykamy sesje to poleci wyjatek, nie mamy dostepu juz do danych
            //session.close();  // zakomenowalam do uzycia criteria
            //sprawdzamy czy jak sesje sa zmakniete mozemy sie dostac majac sama Factory do oddzialu
            System.out.println(company1.getDepartment());




            //zaleta pisania Criteria- na etapie kompilacji widzimy czy zapytanie jest dobrze napsiane
            // to jest jak select- musi byc otwarta sesja; otrzymamy zawsze liste

            System.out.println("SELECT USING CRITERIA");
            Criteria criteria = session.createCriteria(Company.class)
                    .add(Restrictions.eq("name","test"));
            List<Company> list = criteria.list();
            System.out.println(list.get(0));
            session.close();

            /*
            //bez sessionFactory
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
    */

//            String sql = "SELECT * FROM company c";
//            SQLQuery query = session.createSQLQuery(sql);
//            query.addEntity(Company.class);
//            List<Company> list = query.list();
//            System.out.println("Company from select: ");
//            list.forEach(System.out::println);

//            String sqlDep = "SELECT * FROM department d";
//            SQLQuery query = session.createSQLQuery(sqlDep);
//            query.addEntity(Department.class);
//            List<Department> list = query.list();
//            System.out.println("Departments from select: ");
//            list.forEach(System.out::println);

//            tx = session.beginTransaction();
//            //update
//            company.setName(company.getName() + Math.random());
//            session.save(dp);
//            session.save(dp2);
//            tx.commit();

//            System.out.println("delete");
//            tx = session.beginTransaction();
//            session.delete(company);
//            tx.commit();

      //      System.out.println("COMPANY: "+ company);
      //      System.out.println("DP:" + dp);
      //      System.out.println("DP2: " + dp2);
        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
            e.printStackTrace();
            //musze zamknac jak uzywam sessionFactory
            HibernateSessionFactory.closeSessionFactory(sessionFactory);
        } finally {
       //     HibernateSessionFactory.closeAll(session);
             HibernateSessionFactory.closeSessionFactory(sessionFactory);

        }

    }
}
