package repository;

import dto.MotelDTO;
import model.Motel;

import java.util.List;

public interface IMotelRepository {
    List<MotelDTO> getList();

    void save(Motel motel);

    boolean remove(Integer id);

    List<MotelDTO> search(String code, String name, String phone);

    boolean removeMultiple(int[] idList);
}
