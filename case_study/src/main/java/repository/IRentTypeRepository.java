package repository;

import models.Position;
import models.RentType;

import java.util.List;

public interface IRentTypeRepository {
    List<RentType> getList();
}
