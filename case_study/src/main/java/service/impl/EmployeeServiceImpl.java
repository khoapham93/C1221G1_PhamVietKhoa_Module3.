package service.impl;

import dto.EmployeeDTO;
import models.Customer;
import models.Employee;
import repository.IEmployeeRepository;
import repository.impl.EmployeeRepositoryImpl;
import service.IEmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements IEmployeeService {
    private IEmployeeRepository iEmployeeRepository = new EmployeeRepositoryImpl();

    private Map<String, String> validate(Employee employee) {
        Map<String, String> map = new HashMap<>();
        //validate
        return map;
    }
    @Override
    public Map<String, String> save(Employee employee) {
        Map<String, String> map = validate(employee);
        if (map.isEmpty()) {
            iEmployeeRepository.save(employee);
        }

        return map;
    }

    @Override
    public Map<String, String> update(Employee employee) {

        Map<String, String> map = validate(employee);
        if (map.isEmpty()) {
            boolean checkUpdate = iEmployeeRepository.update(employee);
            if (!checkUpdate) {
                map.put("message", "Something's wrong, can't update!");
            }
        }
        return map;
    }

    @Override
    public boolean remove(Integer id) {
        return iEmployeeRepository.remove(id);
    }

    @Override
    public List<EmployeeDTO> getList() {
        return iEmployeeRepository.getList();
    }

    @Override
    public Employee findById(Integer id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public List<EmployeeDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return iEmployeeRepository.search(fieldSearch1,fieldSearch2,fieldSearch3, searchKey1,searchKey2,searchKey3);
    }
}
