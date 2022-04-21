package service;

import dto.EmployeeDTO;
import dto.FacilityDTO;
import models.Employee;
import models.Facility;

import java.util.List;

public interface IFacilityService extends ICRUDService<Facility>{
    List<FacilityDTO> getList();

    Facility findById(Integer id);

    List<FacilityDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3);

}
