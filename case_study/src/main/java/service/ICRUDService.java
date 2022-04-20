package service;

import dto.CustomerDTO;
import models.Customer;
import models.CustomerType;

import java.util.List;
import java.util.Map;

public interface ICRUDService<E> {
    Map<String, String> save(E e);

    Map<String, String> update(E e);

    boolean remove(Integer id);

}
