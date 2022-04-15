package repository;

import model.Product;

import java.util.List;

public interface ICrudRepository<E> {
    List<Product> getList();

    void save(Product product);

    E findByid(Integer id);

    void remove(Integer id);
}
