package pl.lodz.sda.tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by asia on 18/09/17.
 */

//lazy- dopiero gdy zrobimy geta to zainstancjonujemy instance- lazy jest wtedy kiedy uzywam kiedy potrzebuje
public class SingletonSessionFactory {
    private static SessionFactory instance;

    private SingletonSessionFactory() {

    }

    public static SessionFactory getInstance(String configurationFile) {

        if (instance == null) {
            instance = new Configuration().configure(configurationFile).
                    buildSessionFactory();
        }
        return instance;
    }

}
