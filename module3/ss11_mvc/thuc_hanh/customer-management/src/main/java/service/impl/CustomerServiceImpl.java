package service.impl;

import model.Customer;
import service.ICustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService {
    private static Map<Integer, Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "John", "john@gmail.com", "Hanoi"));
        customers.put(2, new Customer(2, "Bill", "bill@gmail.com", "Danang"));
        customers.put(3, new Customer(3, "Alex", "alex@gmail.com", "Saigon"));
        customers.put(4, new Customer(4, "Adam", "adam@gmail.com", "Beijin"));
        customers.put(5, new Customer(5, "Sophia", "sophia@gmail.com", "Miami"));
        customers.put(6, new Customer(6, "Rose", "rose@gmail.com", "Newyork"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(Integer id) {
        return customers.get(id);
    }

    @Override
    public void update(Integer id, Customer customer) {
        customers.put(id, customer);
    }

    @Override
    public void remove(Integer id) {
        customers.remove(id);
    }
}
