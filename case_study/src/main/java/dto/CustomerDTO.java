package dto;

import models.Person;

import java.time.LocalDate;

public class CustomerDTO extends Person {
    private String customerType;
    private Integer gender;

    public CustomerDTO(Integer id, String name, LocalDate birthday, String idCard, String phone, String email, String address, String customerType, Integer gender) {
        super(id, name, birthday, idCard, phone, email, address);
        this.customerType = customerType;
        this.gender = gender;
    }

    public CustomerDTO(String name, LocalDate birthday, String idCard, String phone, String email, String address, String customerType, Integer gender) {
        super(name, birthday, idCard, phone, email, address);
        this.customerType = customerType;
        this.gender = gender;
    }

    public CustomerDTO() {

    }
}
