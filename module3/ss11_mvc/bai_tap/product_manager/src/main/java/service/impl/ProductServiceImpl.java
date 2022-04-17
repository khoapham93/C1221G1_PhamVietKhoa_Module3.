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
    private static final String NAME_REGEX = "^\\p{L}+[0-9]*( (\\p{L}|[0-9])+)*$";

    @Override
    public List<Product> getList() {
        return iProductRepository.getList();
    }

    private Map<String, String> validate(Product product) {
        Map<String, String> messageMap = new HashMap<>();
        if ("".equals(product.getName())) {
            messageMap.put("name", "Name can't empty");
        } else if (!product.getName().matches(NAME_REGEX)) {
            messageMap.put("name", "Name is invalid!");
        }

        if (product.getPrice() == null) {
            messageMap.put("price", "Price must be a number");
        } else if (product.getPrice() < 0) {
            messageMap.put("price", "Price must be greater than 0");
        }


        return messageMap;
    }

    @Override
    public Map<String, String> save(Product product) {
        Map<String, String> map = validate(product);
        if (map.isEmpty()) {
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
