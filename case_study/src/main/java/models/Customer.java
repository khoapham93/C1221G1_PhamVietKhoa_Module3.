package models;

import java.time.LocalDate;

public class Customer extends Person {
    private String customerCode;
    private Integer customerType;
    private Integer gender;

    public Customer() {
    }

    public Customer(Integer id, String name, LocalDate birthday, String idCard, String phone, String email, String address, String customerCode, Integer customerType, Integer gender) {
        super(id, name, birthday, idCard, phone, email, address);
        this.customerCode = customerCode;
        this.customerType = customerType;
        this.gender = gender;
    }

    public Customer(String name, LocalDate birthday, String idCard, String phone, String email, String address, String customerCode, Integer customerType, Integer gender) {
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

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
