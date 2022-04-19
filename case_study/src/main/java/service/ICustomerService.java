package service;

import dto.CustomerDTO;
import models.Customer;

import java.util.List;

public interface ICustomerService extends ICRUDService<Customer>{
    List<CustomerDTO> getList();
}
