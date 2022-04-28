package model;

import java.time.LocalDate;

public class Motel {
    private Integer id;
    private String code;
    private String name;
    private String phone;
    private LocalDate startDate;
    private Integer rentTypeId;
    private String description;

    public Motel(Integer id, String code, String name, String phone, LocalDate startDate, Integer rentTypeId, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.rentTypeId = rentTypeId;
        this.description = description;
    }

    public Motel() {
    }

    public Motel(String name, String phone, LocalDate startDate, Integer rentTypeId, String description) {
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.rentTypeId = rentTypeId;
        this.description = description;
    }

    public Motel(String code, String name, String phone, LocalDate startDate, Integer rentTypeId, String description) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.rentTypeId = rentTypeId;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getRentTypeId() {
        return rentTypeId;
    }

    public void setRentTypeId(Integer rentTypeId) {
        this.rentTypeId = rentTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Motel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", startDate=" + startDate +
                ", rentTypeId=" + rentTypeId +
                ", description='" + description + '\'' +
                '}';
    }
}
