package service.impl;

import dto.MotelDTO;
import model.Motel;
import repository.IMotelRepository;
import repository.impl.MotelRepositoryImpl;
import service.IMotelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotelServiceImpl implements IMotelService {
    private IMotelRepository iMotelRepository = new MotelRepositoryImpl();

    private static final String NAME_REGEX = "^\\p{L}+( (\\p{L})+)*$";
    private static final String CUSTOMER_CODE_REGEX = "^KH-\\d{4}$";
    private static final String PHONE_REGEX = "^\\d{10}$";
    private static final String IDCARD_REGEX = "^(\\d{9}|\\d{12})$";
    private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@[a-z]+\\.(\\w+)(\\.\\w{2,3})?";

    private Map<String, String> validate(Motel motel) {
        Map<String, String> map = new HashMap<>();
        if (motel.getName() == null){
            map.put("name","Name can't empty");
        }else if (motel.getName().length()<5 || motel.getName().length()>50){
            map.put("name","Name length must from 5 to 50 characters");
        }else if(!motel.getName().matches(NAME_REGEX)){
            map.put("name","Name's invalid");
        }

        if (motel.getStartDate() == null){
            map.put("startDate","Date Start is invalid");
        }

        if (motel.getPhone() == null){
            map.put("phone","Phone's invalid");
        }else if (!motel.getPhone().matches(PHONE_REGEX)){
            map.put("phone","Phone must be a String of 10 numbers");
        }

        if (motel.getRentTypeId() == null){
            map.put("rentTypeId","Payment's invalid");
        }

        if (motel.getDescription()!=null){
            if (motel.getDescription().length()>200){
                map.put("description","Description's over 200 character");
            }
        }

        return map;
    }
    @Override
    public List<MotelDTO> getList() {
        return iMotelRepository.getList();
    }

    @Override
    public Map<String, String> save(Motel motel) {
        Map<String, String> map = validate(motel);
        if (map.isEmpty()) {
            iMotelRepository.save(motel);
        }

        return map;
    }

    @Override
    public boolean remove(Integer id) {
        return iMotelRepository.remove(id);
    }

    @Override
    public List<MotelDTO> search(String code, String name, String phone) {
        return iMotelRepository.search(code,name,phone);
    }

    @Override
    public boolean removeMultiple(int[] idList) {
        return iMotelRepository.removeMultiple(idList);
    }

}
