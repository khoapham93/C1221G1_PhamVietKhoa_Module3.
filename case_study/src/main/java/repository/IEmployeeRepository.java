package repository;

import dto.EmployeeDTO;
import models.Employee;

import java.util.List;

public interface IEmployeeRepository extends ICRUDRepository<Employee> {

    List<EmployeeDTO> getList();

    Employee findById(Integer id);

    List<EmployeeDTO> search(String fieldSearch, String searchKey);
}
