package service.impl;

import models.FacilityType;
import repository.IFacilityTypeRepository;
import repository.impl.FacilityTypeRepositoryImpl;
import service.IFacilityTypeService;

import java.util.List;

public class FacilityTypeServiceImpl implements IFacilityTypeService {
    private IFacilityTypeRepository iFacilityTypeRepository = new FacilityTypeRepositoryImpl();

    @Override
    public List<FacilityType> getList() {
        return iFacilityTypeRepository.getList();
    }
}
