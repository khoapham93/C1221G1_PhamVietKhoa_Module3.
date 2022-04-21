package service.impl;

import dto.CustomerDTO;
import models.Customer;
import repository.ICustomerRopository;
import repository.impl.CustomerRepositoryImpl;
import service.ICustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService {
    private ICustomerRopository iCustomerRopository = new CustomerRepositoryImpl();

    private Map<String, String> validate(Customer customer) {
        Map<String, String> map = new HashMap<>();
        //validate
        return map;
    }

    @Override
    public List<CustomerDTO> getList() {
        return iCustomerRopository.getList();
    }

    @Override
    public Customer findById(Integer id) {
        return iCustomerRopository.findById(id);
    }

    @Override
    public List<CustomerDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return iCustomerRopository.search(fieldSearch1,fieldSearch2,fieldSearch3, searchKey1,searchKey2,searchKey3);
    }

    @Override
    public Map<String, String> save(Customer customer) {

        Map<String, String> map = validate(customer);
        if (map.isEmpty()) {
            iCustomerRopository.save(customer);
        }

        return map;
    }

    @Override
    public Map<String, String> update(Customer customer) {
        Map<String, String> map = validate(customer);

        if (map.isEmpty()) {
            boolean checkUpdate = iCustomerRopository.update(customer);
            if (!checkUpdate) {
                map.put("message", "Something's wrong, can't update!");
            }
        }
        return map;
    }

    @Override
    public boolean remove(Integer id) {
        return iCustomerRopository.remove(id);
    }
}
