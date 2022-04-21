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

    private Map<String, String> validate(Facility facility){
        Map<String, String> map = new HashMap<>();
        //validate
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
        if (map.isEmpty()){
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
