package service.impl;

import models.Position;
import repository.IPositionRepository;
import repository.impl.PositionRepositoryImpl;
import service.IPositionService;

import java.util.List;

public class PositionServiceImpl implements IPositionService {
    private IPositionRepository iPositionRepository = new PositionRepositoryImpl();
    @Override
    public List<Position> getList() {
        return iPositionRepository.getList();
    }
}
