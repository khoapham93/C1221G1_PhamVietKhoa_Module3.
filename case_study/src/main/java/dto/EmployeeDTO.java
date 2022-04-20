package dto;

import models.Person;

import java.time.LocalDate;

public class EmployeeDTO extends Person {
    private Double salary;
    private String position;
    private String academic;
    private String department;

    public EmployeeDTO(Integer id, String name, LocalDate birthday, String idCard, String phone, String email, String address, Double salary, String position, String academic, String department) {
        super(id, name, birthday, idCard, phone, email, address);
        this.salary = salary;
        this.position = position;
        this.academic = academic;
        this.department = department;
    }

    public EmployeeDTO(String name, LocalDate birthday, String idCard, String phone, String email, String address, Double salary, String position, String academic, String department) {
        super(name, birthday, idCard, phone, email, address);
        this.salary = salary;
        this.position = position;
        this.academic = academic;
        this.department = department;
    }

    public EmployeeDTO() {

    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
