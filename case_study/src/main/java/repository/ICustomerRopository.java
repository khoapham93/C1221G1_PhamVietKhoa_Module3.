package repository;

import dto.CustomerDTO;
import models.Customer;

import java.util.List;

public interface ICustomerRopository extends ICRUDRepository<Customer> {

    List<CustomerDTO> getList();
}
