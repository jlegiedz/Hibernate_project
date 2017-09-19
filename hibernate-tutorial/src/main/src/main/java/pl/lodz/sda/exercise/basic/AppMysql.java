package pl.lodz.sda.exercise.basic;


import org.hibernate.Session;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;
import org.hibernate.Transaction;

/**
 * Created by asia on 18/09/17.
 */
public class AppMysql {

    public static void main(String[] args) {
        System.out.println("cretae session");
        Session session = null;
        Transaction tr =null;
        try {
                session = HibernateSessionFactory.createSession(DB.MYSQL);
        }
        catch(Exception e){
            System.out.println(e);
        }finally {
            HibernateSessionFactory.closeAll(session);
        }

    }
}
