package models;

import java.time.LocalDate;

public class Contract {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double deposit;
    private Double total;
    private Integer employeeId;
    private Integer customerId;
    private Integer facilityId;

    public Contract(Integer id, LocalDate startDate, LocalDate endDate, Double deposit, Double total, Integer employeeId, Integer customerId, Integer facilityId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.total = total;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.facilityId = facilityId;
    }

    public Contract(LocalDate startDate, LocalDate endDate, Double deposit, Double total, Integer employeeId, Integer customerId, Integer facilityId) {

        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.total = total;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.facilityId = facilityId;
    }

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }
}
