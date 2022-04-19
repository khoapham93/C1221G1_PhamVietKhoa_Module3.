package repository;

import java.util.List;

public interface ICrudRepository<E> {
    List<E> getList();

    void save(E e);

    boolean update (E e);

    E findById(Integer id);

    boolean remove(Integer id);

    List<E> search(String keyWord);

    void insertUserStore(E e);

    E getUserById(Integer id);
}
