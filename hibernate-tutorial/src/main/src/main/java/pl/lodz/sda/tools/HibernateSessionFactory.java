package pl.lodz.sda.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lodz.sda.environment.DB;

import static pl.lodz.sda.environment.DB.H2;

public class HibernateSessionFactory {


    private static String getConfigurationFileName(DB db) {
        if (db == H2) {
            return "hibernate.cfg-h2.xml";
        } else {
            return "hibernate.cfg-mysql.xml";
        }
    }

    public static SessionFactory createSessionFactory(DB db) {

        // Tworzymy obiekt SessionFactory na podstawie którego będziemy tworzyć osobne sesje
        // Pamiętamy, że sessionFactory(pula polaczen) powinno być tworzone jedno per połączenie do DB
        // Ustawiamy konfigurację dla naszego sessionFactory
        String configurationFileName = getConfigurationFileName(db);
        System.out.println("create session factory");
        return SingletonSessionFactory.getInstance(configurationFileName);
    }

    public static Session createSession(DB db) {
        SessionFactory sessionFactory = createSessionFactory(db);
        return sessionFactory.openSession();
    }


    public static Session getSession(DB db) {
        SessionFactory sessionFactory = createSessionFactory(db);
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        session.close();
    }

    public static void closeSessionFactory(SessionFactory sessionFactory) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.close();
        sessionFactory.close();
    }

    public static void closeAll(Session session) {
        SessionFactory sessionFactory = session.getSessionFactory();
        session.close();
        sessionFactory.close();
    }


}
