package service;

import dto.EmployeeDTO;
import models.Employee;

import java.util.List;

public interface IEmployeeService extends ICRUDService<Employee> {
    List<EmployeeDTO> getList();

    Employee findById(Integer id);

    List<EmployeeDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3);
}
