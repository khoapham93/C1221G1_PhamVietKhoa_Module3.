package service.impl;

import dto.CustomerDTO;
import repository.ICustomerRopository;
import repository.impl.CustomerRepositoryImpl;
import service.ICustomerService;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private ICustomerRopository iCustomerRopository = new CustomerRepositoryImpl();
    @Override
    public List<CustomerDTO> getList() {
        return iCustomerRopository.getList();
    }
}
