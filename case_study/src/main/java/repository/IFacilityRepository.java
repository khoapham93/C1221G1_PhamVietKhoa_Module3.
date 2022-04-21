package repository;

import dto.FacilityDTO;
import models.Facility;

import java.util.List;

public interface IFacilityRepository extends ICRUDRepository<Facility>{
    List<FacilityDTO> getList();

    Facility findById(Integer id);

    List<FacilityDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3);

}
