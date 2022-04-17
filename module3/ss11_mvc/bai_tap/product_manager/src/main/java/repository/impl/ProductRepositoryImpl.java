package repository.impl;

import model.Product;
import repository.IProductRepository;

import java.util.*;

public class ProductRepositoryImpl implements IProductRepository {
    private static Map<Integer, Product> productMap;

    static {
        productMap = new TreeMap<>();
        productMap.put(1, new Product(1, "Omo", 8000.0, "2021", "Unilever"));
        productMap.put(2, new Product(2, "Clearmen", 50000.0, "Clear for men", "Clear"));
        productMap.put(3, new Product(3, "HP Envy", 22000000.0, "Envy 15", "HP"));
        productMap.put(4, new Product(4, "iMac M1", 20000000.0, "RAM 8Gb", "Apple"));
        productMap.put(5, new Product(5, "Macbook Air M1", 8000.0, "2021 RAM 16Gb", "Apple"));
        productMap.put(6, new Product(6, "HP Probook", 16000000.0, "2019 RAM 8Gb", "HP"));
    }

    @Override
    public List<Product> getList() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public Product findByid(Integer id) {
        return productMap.get(id);
    }

    @Override
    public void remove(Integer id) {
        productMap.remove(id);
    }

    @Override
    public List<Product> search(String keyWord) {
        List<Product> resultSearch = new ArrayList<>();
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(keyWord)){
                resultSearch.add(entry.getValue());
            }
        }
        return resultSearch;
    }
}
