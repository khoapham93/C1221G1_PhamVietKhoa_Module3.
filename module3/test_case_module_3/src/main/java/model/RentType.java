package model;

public class RentType {
    private Integer id;
    private String rentType;

    public RentType(Integer id, String rentType) {
        this.id = id;
        this.rentType = rentType;
    }

    public RentType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }
}
