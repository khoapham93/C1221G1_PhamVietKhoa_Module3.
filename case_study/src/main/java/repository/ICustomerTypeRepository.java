package repository;

import models.CustomerType;

import java.util.List;

public interface ICustomerTypeRepository{
    List<CustomerType> getList();
}
