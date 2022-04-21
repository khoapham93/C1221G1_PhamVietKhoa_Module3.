package repository.impl;

import dto.CustomerDTO;
import dto.EmployeeDTO;
import models.Customer;
import models.Employee;
import repository.BaseRepository;
import repository.IEmployeeRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements IEmployeeRepository {
    private BaseRepository baseRepository = new BaseRepository();

    private static final String SELECT_ALL_EMPLOYEE_DTO =
            "SELECT em.employee_id,\n" +
                    "       em.name,\n" +
                    "       em.birthday,\n" +
                    "       em.id_card,\n" +
                    "       em.salary,\n" +
                    "       em.phone,\n" +
                    "       em.email,\n" +
                    "       em.address,\n" +
                    "       p.position_name,\n" +
                    "       al.level_name,\n" +
                    "       d.department_name\n" +
                    "FROM employee em\n" +
                    "         LEFT JOIN academic_level al ON em.academic_id = al.academic_id\n" +
                    "         LEFT JOIN department d ON em.department_id = d.department_id\n" +
                    "         LEFT JOIN position p ON em.position_id = p.position_id";

    private static final String SELECT_EMPLOYEE_BY_ID =
            "SELECT employee_id,\n" +
                    "       name,\n" +
                    "       birthday,\n" +
                    "       id_card,\n" +
                    "       salary,\n" +
                    "       phone,\n" +
                    "       email,\n" +
                    "       address,\n" +
                    "       position_id,\n" +
                    "       academic_id,\n" +
                    "       department_id\n" +
                    "FROM employee\n" +
                    "WHERE employee_id = ?;";

    private static final String INSERT_EMPLOYEE = "INSERT INTO employee (`name`,\n" +
            "                      birthday,\n" +
            "                      id_card,\n" +
            "                      salary,\n" +
            "                      phone,\n" +
            "                      email,\n" +
            "                      address,\n" +
            "                      position_id,\n" +
            "                      academic_id,\n" +
            "                      department_id)\n" +
            "VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET `name` = ?,\n" +
            "                    birthday = ?,\n" +
            "                    id_card = ?,\n" +
            "                    salary = ?,\n" +
            "                    phone = ?,\n" +
            "                    email = ?,\n" +
            "                    address = ?,\n" +
            "                    position_id = ?,\n" +
            "                    academic_id = ?,\n" +
            "                    department_id = ?\n" +
            "WHERE employee_id = ?;";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE employee_id = ?;";

    @Override
    public List<EmployeeDTO> getList() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_EMPLOYEE_DTO);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String name;
            LocalDate birthday;
            String idCard;
            String phone;
            String email;
            String address;
            Double salary;
            String position;
            String academic;
            String department;

            while (resultSet.next()) {
                id = resultSet.getInt("employee_id");
                name = resultSet.getString("name");
                birthday = resultSet.getDate("birthday").toLocalDate();
                idCard = resultSet.getString("id_card");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                address = resultSet.getString("address");
                salary = resultSet.getDouble("salary");
                position = resultSet.getString("position_name");
                academic = resultSet.getString("level_name");
                department = resultSet.getString("department_name");

                employeeDTOList.add(new EmployeeDTO(id, name, birthday, idCard, phone, email, address, salary, position, academic, department));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return employeeDTOList;
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String name;
            LocalDate birthday;
            String idCard;
            String phone;
            String email;
            String address;
            Double salary;
            Integer positionId;
            Integer academicId;
            Integer departmentId;

            while (resultSet.next()) {
                name = resultSet.getString("name");
                birthday = resultSet.getDate("birthday").toLocalDate();
                idCard = resultSet.getString("id_card");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                address = resultSet.getString("address");
                salary = resultSet.getDouble("salary");
                positionId = resultSet.getInt("position_id");
                academicId = resultSet.getInt("academic_id");
                departmentId = resultSet.getInt("department_id");

                employee = new Employee(id, name, birthday, idCard, phone, email, address, salary, positionId, academicId, departmentId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return employee;
    }

    @Override
    public List<EmployeeDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        String searchQuery = SELECT_ALL_EMPLOYEE_DTO + " WHERE `" + fieldSearch1 + "` LIKE ? " +
                "AND `" + fieldSearch2 + "` LIKE ? " +
                "AND `" + fieldSearch3 + "` LIKE ? ;";

        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(searchQuery);
            preparedStatement.setString(1, "%" + searchKey1 + "%");
            preparedStatement.setString(2, "%" + searchKey2 + "%");
            preparedStatement.setString(3, "%" + searchKey3 + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            Integer id;
            String name;
            LocalDate birthday;
            String idCard;
            String phone;
            String email;
            String address;
            Double salary;
            String position;
            String academic;
            String department;
            while (resultSet.next()) {
                id = resultSet.getInt("employee_id");
                name = resultSet.getString("name");
                birthday = resultSet.getDate("birthday").toLocalDate();
                idCard = resultSet.getString("id_card");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                address = resultSet.getString("address");
                salary = resultSet.getDouble("salary");
                position = resultSet.getString("position_name");
                academic = resultSet.getString("level_name");
                department = resultSet.getString("department_name");

                employeeDTOList.add(new EmployeeDTO(id, name, birthday, idCard, phone, email, address, salary, position, academic, department));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return employeeDTOList;
    }

    @Override
    public void save(Employee employee) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(INSERT_EMPLOYEE);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDate(2, Date.valueOf(employee.getBirthday()));
            preparedStatement.setString(3, employee.getIdCard());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setInt(8, employee.getPositionId());
            preparedStatement.setInt(9, employee.getAcademicId());
            preparedStatement.setInt(10, employee.getDepartmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(Employee employee) {
        boolean rowUpdated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDate(2, Date.valueOf(employee.getBirthday()));
            preparedStatement.setString(3, employee.getIdCard());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setInt(8, employee.getPositionId());
            preparedStatement.setInt(9, employee.getAcademicId());
            preparedStatement.setInt(10, employee.getDepartmentId());

            preparedStatement.setInt(11, employee.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(Integer id) {
        boolean rowUpdated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setInt(1, id);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowUpdated;
    }
}
