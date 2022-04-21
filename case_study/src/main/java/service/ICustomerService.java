package service;

import dto.CustomerDTO;
import models.Customer;

import java.util.List;

public interface ICustomerService extends ICRUDService<Customer> {
    List<CustomerDTO> getList();

    Customer findById(Integer id);

    List<CustomerDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3);

}
