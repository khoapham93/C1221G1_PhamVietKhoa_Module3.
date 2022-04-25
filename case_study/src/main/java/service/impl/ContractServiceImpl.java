package service.impl;

import dto.ContractDTO;
import dto.FacilityDTO;
import models.Contract;
import models.Facility;
import repository.IContractRepository;
import repository.IFacilityRepository;
import repository.impl.ContractRepositoryImpl;
import repository.impl.FacilityRepositoryImpl;
import service.IContractService;
import service.IFacilityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractServiceImpl implements IContractService {
    private IContractRepository iContractRepository = new ContractRepositoryImpl();

    private Map<String, String> validate(Contract contract) {

        Map<String, String> map = new HashMap<>();

        if (contract.getStartDate() == null) {
            map.put("startDate", "Start Date must be a date");
        }

        if (contract.getEndDate() == null) {
            map.put("endDate", "End Date must be a date");
        }else if (contract.getStartDate() != null){
            if(contract.getEndDate().minusDays(2).compareTo(contract.getStartDate())<0){
                map.put("endDate", "End Date must be greater than Start at least 2 days");
            }
        }
        if (contract.getDeposit() == null){
            map.put("deposit", "Deposit must be a number");
        }else if(contract.getDeposit() < 0){
            map.put("deposit", "Deposit must be a positive");
        }

        if (contract.getCustomerId() == null) {
            map.put("customerId", "Customer invalid!");
        }
        if (contract.getEmployeeId() == null) {
            map.put("employeeId", "employee invalid!");
        }
        if (contract.getFacilityId() == null) {
            map.put("facilityId", "Facility invalid!");
        }
        return map;
    }

    @Override
    public List<ContractDTO> getList() {
        return iContractRepository.getList();
    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public List<ContractDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return null;
    }

    @Override
    public Map<String, String> save(Contract contract) {
        Map<String, String> map = validate(contract);
        if (map.isEmpty()){
            iContractRepository.save(contract);
        }
        return map;
    }
    @Override
    public Map<String, String> update(Contract contract) {
        return null;
    }
    @Override
    public boolean remove(Integer id) {
        return false;
    }
}
