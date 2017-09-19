package pl.lodz.sda.exercise.basic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.dao.Task;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;
/**
 *
 */
public class App {
    public static void main(String[] args) {

        // Tworzymy sesję
        System.out.println("create session");
        Session session = HibernateSessionFactory.createSession(DB.H2);
        try {

            // Rozpoczynamy transakcję w sesji
            System.out.println("Begin transaction");
            Transaction tx = session.beginTransaction();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i <100 ; i++) {
                Task task = new Task("Hello world task","Hello world task description");
                // zapisujemy w sesji nasz obiekt
                System.out.println("save" + i + " " + task);
                session.save(task);
                //batch z czyszczeniem cacha
                if(i % 50 == 0){
                    session.flush();
                    session.clear(); // release memory
                }
            }

            // Commitujemy zmiany
            System.out.println("commit");
            System.out.println(System.currentTimeMillis()-startTime);
            tx.commit();
            System.out.println(System.currentTimeMillis()-startTime);
        } catch (Exception e) {
            //log
        } finally {
            // Zamykamy sessionFactory
           HibernateSessionFactory.closeAll(session);
        }

    }
}
