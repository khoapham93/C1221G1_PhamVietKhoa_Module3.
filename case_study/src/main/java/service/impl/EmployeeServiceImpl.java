package service.impl;

import dto.EmployeeDTO;
import models.Employee;
import service.IEmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements IEmployeeService {
    @Override
    public Map<String, String> save(Employee employee) {
        return null;
    }

    @Override
    public Map<String, String> update(Employee employee) {
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

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
}
