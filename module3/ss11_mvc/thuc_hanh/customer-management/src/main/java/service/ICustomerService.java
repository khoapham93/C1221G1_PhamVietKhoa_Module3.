package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(Integer id);

    void update(Integer id, Customer customer);

    void remove(Integer id);
}
