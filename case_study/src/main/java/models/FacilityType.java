package models;

public class FacilityType {
    private Integer id;
    private String facilityType;

    public FacilityType(Integer id, String facilityType) {
        this.id = id;
        this.facilityType = facilityType;
    }

    public FacilityType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }
}
