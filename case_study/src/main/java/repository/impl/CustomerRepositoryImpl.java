package repository.impl;

import dto.CustomerDTO;
import repository.BaseRepository;
import repository.ICustomerRopository;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements ICustomerRopository {
    private BaseRepository baseRepository = new BaseRepository();

    private static final String CALL_SELECT_ALL_CUSTOMER_DTO = "{select_all_customer_dto()}";
//    private static final String SELECT_ALL_CUSTOMER_DTO = "SELECT id,`name`,email,country FROM users;";

    @Override
    public List<CustomerDTO> getList() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        CallableStatement callableStatement = null;
        try {
            callableStatement = this.baseRepository.getConnection().prepareCall(CALL_SELECT_ALL_CUSTOMER_DTO);
            ResultSet resultSet = callableStatement.executeQuery();

            Integer id;
            String name;
            String email;
            String country;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                country = resultSet.getString("country");
                users.add(new User(id, name, email, country));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert callableStatement != null;
                callableStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customerDTOList;
    }
}
