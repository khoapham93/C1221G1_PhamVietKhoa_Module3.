package repository.impl;

import dto.EmployeeDTO;
import models.Customer;
import models.Employee;
import repository.BaseRepository;
import repository.IEmployeeRepository;

import java.util.List;

public class EmployeeRepositoryImpl implements IEmployeeRepository {
    private BaseRepository baseRepository = new BaseRepository();

    @Override
    public List<EmployeeDTO> getList() {
        return null;
    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }

    @Override
    public List<EmployeeDTO> search(String fieldSearch, String searchKey) {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }
}
