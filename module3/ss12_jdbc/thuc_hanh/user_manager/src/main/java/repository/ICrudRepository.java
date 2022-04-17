package repository;

import model.User;

import java.util.List;

public interface ICrudRepository<E> {
    List<User> getList();

    void save(User user);

    boolean update (User user);

    E findById(Integer id);

    boolean remove(Integer id);

    List<E> search(String keyWord);
}
