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

    private static final String NAME_REGEX = "^\\p{L}+[0-9]*( (\\p{L}|[0-9])+)*$";
    private static final String PHONE_REGEX = "^(090|091|\\(84\\)90|\\(84\\)91)\\d{7}$";
    private static final String IDCARD_REGEX = "^d{9}|d{12}$";
    private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@[a-z]+\\.(\\w+)(\\.\\w{2,3})?";

    private Map<String, String> validate(Employee employee) {
        Map<String, String> map = new HashMap<>();


        if (employee.getName() == null) {
            map.put("name", "Name can't empty");
        } else if (!employee.getName().matches(NAME_REGEX)) {
            map.put("name", "Name is invalid!");
        }
        if (!employee.getPhone().matches(PHONE_REGEX)) {
            map.put("phone", "Phone is begin with 090 or 091 or (84)...");
        }
        if (!employee.getIdCard().matches(IDCARD_REGEX)) {
            map.put("idCard", "Id card include 9 or 12 numbers");
        }
        if (!employee.getEmail().matches(EMAIL_REGEX)) {
            map.put("email", "Email invalid");
        }
        if (employee.getBirthday() == null) {
            map.put("birthday", "Birthday invalid");
        }
        if (employee.getSalary() == null) {
            map.put("salary", "Salary must be a number!");
        } else if (employee.getSalary() < 0) {
            map.put("salary", "Salary must be a positive!");
        }
        if (employee.getPositionId() == null) {
            map.put("position", "Position invalid");
        }
        if (employee.getAcademicId() == null) {
            map.put("academicLevel", "Academic invalid");
        }
        if (employee.getDepartmentId() == null) {
            map.put("department", "Department invalid");
        }
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
        return iEmployeeRepository.search(fieldSearch1, fieldSearch2, fieldSearch3, searchKey1, searchKey2, searchKey3);
    }
}
