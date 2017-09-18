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
            Task task = new Task();
            task.setId(1L);
            task.setName("Hello world task");
            task.setDescription("Hello world task description");
            // zapisujemy w sesji nasz obiekt
            System.out.println("save");
            session.save(task);
            // Commitujemy zmiany
            System.out.println("commit");
            tx.commit();
        } catch (Exception e) {
            //log
        } finally {
            // Zamykamy sessionFactory
            HibernateSessionFactory.closeAll(session);
        }

    }
}
