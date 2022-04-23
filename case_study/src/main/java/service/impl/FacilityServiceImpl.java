package service.impl;

import dto.FacilityDTO;
import models.Facility;
import repository.IFacilityRepository;
import repository.impl.FacilityRepositoryImpl;
import service.IFacilityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilityServiceImpl implements IFacilityService {
    private IFacilityRepository iFacilityRepository = new FacilityRepositoryImpl();

    private static final String NAME_REGEX = "^\\p{L}+[0-9]*( (\\p{L}|[0-9])+)*$";
    private static final String FACILITY_CODE_REGEX = "^DV-\\d{4}$";
    private static final String PHONE_REGEX = "^(090|091|\\(84\\)90|\\(84\\)91)\\d{7}$";
    private static final String IDCARD_REGEX = "^d{9}|d{12}$";
    private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@[a-z]+\\.(\\w+)(\\.\\w{2,3})?";
    private static final String POSITIVE_INTEGER_REGEX = "^[+]?\\d+$";

    private Map<String, String> validate(Facility facility) {

        Map<String, String> map = new HashMap<>();

        if (facility.getName() == null) {
            map.put("name", "Name can't empty");
        } else if (!facility.getName().matches(NAME_REGEX)) {
            map.put("name", "Name is invalid!");
        }
        if (facility.getFacilityTypeId() == null) {
            map.put("facilityType", "Facility type invalid!");
        }

        if (facility.getCode() == null) {
            map.put("code", "Facility code can't null!");
        } else if (!facility.getCode().matches(FACILITY_CODE_REGEX)) {
            map.put("code", "Facility code format DV-XXXX!");
        }

        if (facility.getFloorSquare() == null) {
            map.put("floorSquare", "Floor Square Can't null");
        } else if (facility.getFloorSquare() < 0) {
            map.put("floorSquare", "Floor Square must be positive");
        }

        if (facility.getRentalFee() == null) {
            map.put("rentalFee", "Rental Fee Can't null");
        } else if (facility.getRentalFee() < 0) {
            map.put("rentalFee", "Rental Fee must be positive");
        }
        if (facility.getMaximumPeople() == null) {
            map.put("maximumPeople", "People Can't null");
        } else if (facility.getMaximumPeople() < 0) {
            map.put("maximumPeople", "People must be positive");
        }
        if (facility.getRentTypeId() == null) {
            map.put("rentTypeId", "Rent Type invalid!");
        }

        if (facility.getFacilityTypeId()<3){
            if (facility.getNumberFloor() == null) {
                map.put("numberFloor", "Number Floor must be a number");
            } else if (facility.getNumberFloor() < 0) {
                map.put("numberFloor", "Number Floor must be positive");
            }
        }
        if (facility.getFacilityTypeId()<2){
            if (facility.getPoolSquare() == null) {
                map.put("poolSquare", "Square must be a number");
            } else if (facility.getPoolSquare() < 0) {
                map.put("poolSquare", "Square must be positive");
            }
        }

//        if (facility.getNumberFloor() == null) {
//            map.put("numberFloor", "Number Floor must be a number");
//        } else if (facility.getNumberFloor() < 0) {
//            map.put("numberFloor", "Number Floor must be positive");
//        }
//        if (facility.getPoolSquare() == null) {
//            map.put("poolSquare", "Square must be a number");
//        } else if (facility.getPoolSquare() < 0) {
//            map.put("poolSquare", "Square must be positive");
//        }

        return map;
    }

    @Override
    public List<FacilityDTO> getList() {
        return iFacilityRepository.getList();
    }

    @Override
    public Facility findById(Integer id) {
        return null;
    }

    @Override
    public List<FacilityDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return null;
    }

    @Override
    public Map<String, String> save(Facility facility) {
        Map<String, String> map = validate(facility);
        if (map.isEmpty()) {
            iFacilityRepository.save(facility);
        }
        return map;
    }

    @Override
    public Map<String, String> update(Facility facility) {
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }
}
