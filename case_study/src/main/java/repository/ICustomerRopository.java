package repository;

import dto.CustomerDTO;
import models.Customer;

import java.util.List;

public interface ICustomerRopository extends ICRUDRepository<Customer> {

    List<CustomerDTO> getList();

    Customer findById(Integer id);

    List<CustomerDTO> search(String fieldSearch, String searchKey);
}
