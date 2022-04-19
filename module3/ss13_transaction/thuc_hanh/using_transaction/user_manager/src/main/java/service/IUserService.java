package service;

import model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<User> getList();

    Map<String, String> save(User user);

    Map<String, String> update(User user);

    User findById(Integer id);

    boolean remove(Integer id);

    List<User> search(String keyWord);

    void addUserTransaction(User user, int[] permisions);
}
