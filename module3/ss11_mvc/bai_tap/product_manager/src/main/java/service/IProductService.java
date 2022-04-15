package service;

import model.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {

    List<Product> getList();

    Map<String, String> save(Product product);

    Product findByid(Integer id);

    void remove(Integer id);
}
