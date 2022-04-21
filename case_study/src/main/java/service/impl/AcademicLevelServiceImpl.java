package service.impl;

import models.AcademicLevel;
import repository.IAcademicLevelRepository;
import repository.impl.AcademicLevelRepositoryImpl;
import service.IAcademicLevelService;

import java.util.List;

public class AcademicLevelServiceImpl implements IAcademicLevelService {
  private IAcademicLevelRepository iAcademicLevelRepository = new AcademicLevelRepositoryImpl();
    @Override
    public List<AcademicLevel> getList() {
        return iAcademicLevelRepository.getList();
    }
}
