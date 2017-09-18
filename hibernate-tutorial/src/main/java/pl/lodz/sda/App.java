package pl.lodz.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Tworzymy obiekt SessionFactory na podstawie którego będziemy tworzyć osobne sesje
        // Pamiętamy, że sessionFactory powinno być tworzone jedno per połączenie do DB
        SessionFactory sessionFactory;
        // Ustawiamy konfigurację dla naszego sessionFactory
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        // Tworzymy sesję
        Session session = sessionFactory.openSession();

        // Rozpoczynamy transakcję w sesji
        Transaction tx = session.beginTransaction();
        Task task = new Task();
        task.setId(1L);
        task.setName("Hello world task");
        task.setDescription("Hello world task description");
        // zapisujemy w sesji nasz obiekt
        session.save(task);
        // Commitujemy zmiany
        tx.commit();
        // Zamykamy sesję
        session.close();

        // Zamykamy sessionFactory
        sessionFactory.close();
    }
}
