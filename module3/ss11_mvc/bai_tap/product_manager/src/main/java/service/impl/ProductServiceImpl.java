package service.impl;

import model.Product;
import repository.IProductRepository;
import repository.impl.ProductRepositoryImpl;
import service.IProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements IProductService {
    private IProductRepository iProductRepository = new ProductRepositoryImpl();
    @Override
    public List<Product> getList() {
        return iProductRepository.getList();
    }

    @Override
    public Map<String, String> save(Product product) {
        Map<String, String>map = new HashMap<>();
        if("".equals(product.getName())) {
            map.put("name","Name can't empty");
        } else if(!product.getName().matches("^[a-zA-Z ]+$")) {
            map.put("name","Name is invalid!");
        }

        if(map.isEmpty()) {
            iProductRepository.save(product);
        }
        return map;
    }

    @Override
    public Product findByid(Integer id) {
        return iProductRepository.findByid(id);
    }

    @Override
    public void remove(Integer id) {
        iProductRepository.remove(id);
    }

    @Override
    public List<Product> search(String keyWord) {
        return iProductRepository.search(keyWord);

    }
}
