package repository.impl;

import models.Customer;
import models.CustomerType;
import repository.BaseRepository;
import repository.ICustomerTypeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepositoryImpl implements ICustomerTypeRepository {
    private BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ALL_CUSTOMER_TYPE = "SELECT customer_type_id,`value` FROM customer_type;";

    @Override
    public List<CustomerType> getList() {
        List<CustomerType> customerTypes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_CUSTOMER_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String type;
            while (resultSet.next()) {
                id = resultSet.getInt("customer_type_id");
                type = resultSet.getString("value");
                customerTypes.add(new CustomerType(id, type));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return customerTypes;
    }

}
