package pl.lodz.sda.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.tools.HibernateSessionFactory;
import pl.lodz.sda.tools.PropertiesLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created by asia on 20/09/17.
 */

//chcemy pisac aplikacje trojwarstwowa:
// w model: sa dane
//klasa manager: zawolamy na niej find to nam znajdzie pracownika- ma bebechy w srodku
public class CompanyManager implements IManager<Company> {

    private Session session;

    private Transaction transaction;

    PropertiesLoader propertiesLoader = new PropertiesLoader();

    public CompanyManager() throws IOException{
        propertiesLoader.init();
    }

    //create session juz robi open session
    private void init() throws IOException {
        propertiesLoader.init();
         session = HibernateSessionFactory.createSession(propertiesLoader.getDb());
    }

    private void openTransaction(){
        this.transaction= session.beginTransaction();
    }

    private void commitTransaction(){
        this.transaction.commit();
    }

    private void closeSession(){
        session.close();
    }
    private void closeAll(){
        HibernateSessionFactory.closeAll(session);
    }


    @Override
    public Company save(Company entity) {
        try {
            init();
            openTransaction();
            session.save(entity);
            commitTransaction();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            closeSession();
        }
        return entity;
    }


    @Override
    public Company update(Company entity) {
        return null;
    }

    @Override
    public Company find(Long id) {
        try {
            init();
            Criteria criteria = session.createCriteria(Company.class).
                    add(Restrictions.
                            eq("id", id));
            List<Company> list = criteria.list();
            // CollectionUtils
            return !list.isEmpty() ? list.get(0) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession();
        }
    }


    @Override
    public List<Company> findAll() {
        try {
            init();
            return session.createCriteria(Company.class).list();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession();
        }

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete() {

    }
}
