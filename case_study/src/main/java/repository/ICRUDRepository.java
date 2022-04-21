package repository;

import models.Customer;
import models.CustomerType;

import java.util.List;

public interface ICRUDRepository<E> {

    void save(E e);

    boolean update(E e);

    boolean remove(Integer id);
}
