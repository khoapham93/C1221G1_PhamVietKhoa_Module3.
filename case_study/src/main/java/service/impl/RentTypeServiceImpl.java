package service.impl;

import models.RentType;
import repository.IRentTypeRepository;
import repository.impl.RentTypeRepositoryImpl;
import service.IRentTypeService;

import java.util.List;

public class RentTypeServiceImpl implements IRentTypeService {
    private IRentTypeRepository iRentTypeRepository = new RentTypeRepositoryImpl();
    @Override
    public List<RentType> getList() {
        return iRentTypeRepository.getList();
    }
}
