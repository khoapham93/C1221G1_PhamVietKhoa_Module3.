package repository.impl;

import models.Department;
import org.w3c.dom.ls.LSInput;
import repository.BaseRepository;
import repository.IDepartmentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    private BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ALL_DEPARTMENT = "SELECT department_id, department_name FROM department;";

    @Override
    public List<Department> getList() {
        List<Department> departments = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_DEPARTMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String department;
            while (resultSet.next()){
                id = resultSet.getInt("department_id");
                department = resultSet.getString("department_name");

                departments.add(new Department(id,department));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return departments;
    }
}
