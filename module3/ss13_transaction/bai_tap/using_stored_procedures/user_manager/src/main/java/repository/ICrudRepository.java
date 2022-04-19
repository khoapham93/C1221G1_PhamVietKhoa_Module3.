package repository;

import model.User;

import java.util.List;

public interface ICrudRepository<E> {
    List<E> getList();

    void save(E e);

    boolean update (E e);

    E findById(Integer id);

    boolean remove(Integer id);

    List<E> search(String keyWord);

    List<E> getListUsingSP();

    boolean updateUsingSP (E e);

    boolean removeUingSP(Integer id);
}
