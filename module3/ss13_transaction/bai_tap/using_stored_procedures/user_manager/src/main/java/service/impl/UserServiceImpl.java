package service.impl;

import model.User;
import repository.IUserRepository;
import repository.impl.UserRepositoryImpl;
import service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {
    private IUserRepository iUserRepository = new UserRepositoryImpl();
    private static final String NAME_REGEX = "^\\p{L}+[0-9]*( (\\p{L}|[0-9])+)*$";
    private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@[a-z]+\\.(\\w+)(\\.\\w{2,3})?";

    @Override
    public List<User> getList() {
//        return iUserRepository.getList();
        return iUserRepository.getListUsingSP();
    }

    private Map<String, String> validate(User user) {
        Map<String, String> messageMap = new HashMap<>();
        if ("".equals(user.getName())) {
            messageMap.put("name", "Name can't empty");
        } else if (!user.getName().matches(NAME_REGEX)) {
            messageMap.put("name", "Name is invalid!");
        }
        if ("".equals(user.getEmail())) {
            messageMap.put("email", "Email can't empty");
        } else if (!user.getEmail().matches(EMAIL_REGEX)) {
            messageMap.put("email", "Email is invalid!");
        }
        if ("".equals(user.getCountry())) {
            messageMap.put("country", "Country can't empty");
        } else if (!user.getCountry().matches(NAME_REGEX)) {
            messageMap.put("country", "Country is invalid!");
        }
        return messageMap;
    }

    @Override
    public Map<String, String> save(User user) {
        Map<String, String> map = validate(user);

        if (map.isEmpty()) {
            iUserRepository.save(user);
        }

        return map;
    }

    @Override
    public Map<String, String> update(User user) {

        Map<String, String> map = validate(user);
        if (map.isEmpty()) {
//            boolean checkUpdate = iUserRepository.update(user);
            boolean checkUpdate = iUserRepository.updateUsingSP(user);
            if (!checkUpdate){
                map.put("message","Something's wrong, can't update!");
            }
        }
        return map;
    }

    @Override
    public User findById(Integer id) {
        return iUserRepository.findById(id);
    }

    @Override
    public boolean remove(Integer id) {
//        return iUserRepository.remove(id);
        return iUserRepository.removeUingSP(id);
    }

    @Override
    public List<User> search(String keyWord) {
        return iUserRepository.search(keyWord);

    }
}
