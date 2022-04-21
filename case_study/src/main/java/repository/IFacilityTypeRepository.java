package repository;

import models.FacilityType;
import models.Position;

import java.util.List;

public interface IFacilityTypeRepository {
    List<FacilityType> getList();
}
