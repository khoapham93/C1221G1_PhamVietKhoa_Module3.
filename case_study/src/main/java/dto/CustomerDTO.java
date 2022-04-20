package dto;

import models.Person;

import java.time.LocalDate;

public class CustomerDTO extends Person {
    private String customerCode;
    private String customerType;
    private Integer gender;

    public CustomerDTO(Integer id, String name, LocalDate birthday, String idCard, String phone, String email, String address, String customerCode, String customerType, Integer gender) {
        super(id, name, birthday, idCard, phone, email, address);
        this.customerCode = customerCode;
        this.customerType = customerType;
        this.gender = gender;
    }

    public CustomerDTO(String name, LocalDate birthday, String idCard, String phone, String email, String address, String customerCode, String customerType, Integer gender) {
        super(name, birthday, idCard, phone, email, address);
        this.customerCode = customerCode;
        this.customerType = customerType;
        this.gender = gender;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerDTO() {
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
