package models;

import java.time.LocalDate;

public class Customer extends Person {
    private Integer customerType;
    private Integer gender;

    public Customer(Integer id, String name, LocalDate birthday, String idCard, String phone, String email, String address, Integer customerType, Integer gender) {
        super(id, name, birthday, idCard, phone, email, address);
        this.customerType = customerType;
        this.gender = gender;
    }

    public Customer(String name, LocalDate birthday, String idCard, String phone, String email, String address, Integer customerType, Integer gender) {
        super(name, birthday, idCard, phone, email, address);
        this.customerType = customerType;
        this.gender = gender;
    }

    public Customer() {
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
