package pl.lodz.sda.dao;

import pl.lodz.sda.model.Company;

import java.util.List;

/**
 * Created by asia on 20/09/17.
 */
public interface IManager<T> {

    public T save(T entity);

    public T update(T entity);

    public T find(Long id);

    public List<T> findAll();

    public void deleteAll();

    public void delete();

}
