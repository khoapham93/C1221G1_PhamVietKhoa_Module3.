package dto;

import java.time.LocalDate;

public class MotelDTO {
    private Integer id;
    private String code;
    private String name;
    private String phone;
    private LocalDate startDate;
    private String rentType;
    private String description;

    public MotelDTO(Integer id, String code, String name, String phone, LocalDate startDate, String rentType, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.rentType = rentType;
        this.description = description;
    }

    public MotelDTO(String code, String name, String phone, LocalDate startDate, String rentType, String description) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.rentType = rentType;
        this.description = description;
    }

    public MotelDTO(String name, String phone, LocalDate startDate, String rentType, String description) {
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.rentType = rentType;
        this.description = description;
    }

    public MotelDTO() {
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

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MotelDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", startDate=" + startDate +
                ", rentType='" + rentType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
