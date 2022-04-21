package dto;

public class FacilityDTO {
    private Integer id;
    private String facilityType;
    private String rentType;
    private String code;
    private String name;
    private Double floorSquare;
    private Double rentalFee;
    private Integer maximumPeople;
    private String roomStandard;
    private String description;
    private Double poolSquare;
    private Integer numberFloor;

    public FacilityDTO(Integer id, String facilityType, String rentType, String code, String name, Double floorSquare, Double rentalFee, Integer maximumPeople, String roomStandard, String description, Double poolSquare, Integer numberFloor) {
        this.id = id;
        this.facilityType = facilityType;
        this.rentType = rentType;
        this.code = code;
        this.name = name;
        this.floorSquare = floorSquare;
        this.rentalFee = rentalFee;
        this.maximumPeople = maximumPeople;
        this.roomStandard = roomStandard;
        this.description = description;
        this.poolSquare = poolSquare;
        this.numberFloor = numberFloor;
    }

    public FacilityDTO(String facilityType,
                       String rentType,
                       String code,
                       String name,
                       Double floorSquare,
                       Double rentalFee,
                       Integer maximumPeople,
                       String roomStandard,
                       String description,
                       Double poolSquare,
                       Integer numberFloor) {
        this.facilityType = facilityType;
        this.rentType = rentType;
        this.code = code;
        this.name = name;
        this.floorSquare = floorSquare;
        this.rentalFee = rentalFee;
        this.maximumPeople = maximumPeople;
        this.roomStandard = roomStandard;
        this.description = description;
        this.poolSquare = poolSquare;
        this.numberFloor = numberFloor;
    }

    public FacilityDTO() {
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

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
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

    public Double getFloorSquare() {
        return floorSquare;
    }

    public void setFloorSquare(Double floorSquare) {
        this.floorSquare = floorSquare;
    }

    public Double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public Integer getMaximumPeople() {
        return maximumPeople;
    }

    public void setMaximumPeople(Integer maximumPeople) {
        this.maximumPeople = maximumPeople;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPoolSquare() {
        return poolSquare;
    }

    public void setPoolSquare(Double poolSquare) {
        this.poolSquare = poolSquare;
    }

    public Integer getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(Integer numberFloor) {
        this.numberFloor = numberFloor;
    }
}
