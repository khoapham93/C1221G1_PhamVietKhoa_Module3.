package service;

import dto.MotelDTO;
import model.Motel;

import java.util.List;
import java.util.Map;

public interface IMotelService {
    List<MotelDTO> getList();

    Map<String, String> save(Motel motel);

    boolean remove(Integer id);

    List<MotelDTO> search(String code, String name, String phone);

    boolean removeMultiple(int[] idList);
}
