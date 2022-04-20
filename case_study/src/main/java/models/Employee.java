package models;

import java.time.LocalDate;

public class Employee extends Person {
    private Double salary;
    private Integer positionId;
    private Integer academicId;
    private Integer departmentId;

    public Employee(Integer id, String name, LocalDate birthday, String idCard, String phone, String email, String address, Double salary, Integer positionId, Integer academicId, Integer departmentId) {
        super(id, name, birthday, idCard, phone, email, address);
        this.salary = salary;
        this.positionId = positionId;
        this.academicId = academicId;
        this.departmentId = departmentId;
    }

    public Employee(String name, LocalDate birthday, String idCard, String phone, String email, String address, Double salary, Integer positionId, Integer academicId, Integer departmentId) {
        super(name, birthday, idCard, phone, email, address);
        this.salary = salary;
        this.positionId = positionId;
        this.academicId = academicId;
        this.departmentId = departmentId;
    }

    public Employee() {

    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Integer academicId) {
        this.academicId = academicId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
