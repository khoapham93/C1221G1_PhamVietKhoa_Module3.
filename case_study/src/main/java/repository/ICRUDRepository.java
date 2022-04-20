package repository;

import models.Customer;
import models.CustomerType;

import java.util.List;

public interface ICRUDRepository<E> {

    void save(Customer customer);

    boolean update(Customer customer);

    boolean remove(Integer id);
}
