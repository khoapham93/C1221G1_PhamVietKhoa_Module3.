package service.impl;

import models.Department;
import repository.IDepartmentRepository;
import repository.impl.DepartmentRepositoryImpl;
import service.IDepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository iDepartmentRepository = new DepartmentRepositoryImpl();
    @Override
    public List<Department> getList() {
        return iDepartmentRepository.getList();
    }
}
