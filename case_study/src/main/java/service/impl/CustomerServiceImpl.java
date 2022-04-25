package service.impl;

import dto.CustomerDTO;
import models.Customer;
import repository.ICustomerRopository;
import repository.impl.CustomerRepositoryImpl;
import service.ICustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService {
    private ICustomerRopository iCustomerRopository = new CustomerRepositoryImpl();

    private static final String NAME_REGEX = "^\\p{L}+[0-9]*( (\\p{L}|[0-9])+)*$";
    private static final String CUSTOMER_CODE_REGEX = "^KH-\\d{4}$";
    private static final String PHONE_REGEX = "^(090|091|\\(84\\)90|\\(84\\)91)\\d{7}$";
    private static final String IDCARD_REGEX = "^(\\d{9}|\\d{12})$";
    private static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@[a-z]+\\.(\\w+)(\\.\\w{2,3})?";

    private Map<String, String> validate(Customer customer) {

        Map<String, String> map = new HashMap<>();

        if (customer.getName() == null) {
            map.put("name", "Name can't empty");
        } else if (!customer.getName().matches(NAME_REGEX)) {
            map.put("name", "Name is invalid!");
        }

        if(customer.getCustomerCode() == null){
            map.put("customerCode","Customer code can't null!");
        }else if (!customer.getCustomerCode().matches(CUSTOMER_CODE_REGEX)){
            map.put("customerCode","Customer code format KH-XXXX!");
        }
        if (!customer.getPhone().matches(PHONE_REGEX)){
            map.put("phone","Phone is begin with 090 or 091 or (84)...");
        }
        if (!customer.getIdCard().matches(IDCARD_REGEX)){
            map.put("idCard","Id card include 9 or 12 numbers");
        }
        if (!customer.getEmail().matches(EMAIL_REGEX)){
            map.put("email","Email invalid");
        }
        if (customer.getBirthday() == null){
            map.put("birthday","Birthday invalid");
        }
        if (customer.getCustomerType() == null){
            map.put("customerType","Customer type invalid");
        }

        return map;
    }

    @Override
    public List<CustomerDTO> getList() {
        return iCustomerRopository.getList();
    }

    @Override
    public Customer findById(Integer id) {
        return iCustomerRopository.findById(id);
    }

    @Override
    public List<CustomerDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return iCustomerRopository.search(fieldSearch1,fieldSearch2,fieldSearch3, searchKey1,searchKey2,searchKey3);
    }

    @Override
    public Map<String, String> save(Customer customer) {

        Map<String, String> map = validate(customer);
        if (map.isEmpty()) {
            iCustomerRopository.save(customer);
        }

        return map;
    }

    @Override
    public Map<String, String> update(Customer customer) {
        Map<String, String> map = validate(customer);
        if (map.isEmpty()) {
            boolean checkUpdate = iCustomerRopository.update(customer);
            if (!checkUpdate) {
                map.put("message", "Something's wrong, can't update!");
            }
        }
        return map;
    }

    @Override
    public boolean remove(Integer id) {
        return iCustomerRopository.remove(id);
    }
}
