package pl.lodz.sda.exercise.basic;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.model.ContractEmployee;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;
import java.util.List;


/**
 * Created by asia on 20/09/17.
 */
public class App_inheritance {

    public static void main(String[] args) {


        ContractEmployee contractEmployee = new ContractEmployee();
        contractEmployee.setName("Zdziszek");
        contractEmployee.setHourRate(200);

        Session session = null;
        Transaction trx = null;

        try {
            session = HibernateSessionFactory.createSession(DB.H2);
            trx = session.beginTransaction();
            session.save(contractEmployee);
            trx.commit();


            SQLQuery sql = session.createSQLQuery("select * from ContractEmployee");
            sql.addEntity(ContractEmployee.class); // co tu si ezadzialo??
            List list = sql.list();
            System.out.println("EMPLOYEES: ");
            list.forEach(System.out::println);
        }
        catch(Exception e){

        }
        finally {
            HibernateSessionFactory.closeAll(session);
        }


    }
}
