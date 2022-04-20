package repository.impl;

import dto.CustomerDTO;
import models.Customer;
import repository.BaseRepository;
import repository.ICustomerRopository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements ICustomerRopository {
    private BaseRepository baseRepository = new BaseRepository();

    //    private static final String CALL_SELECT_ALL_CUSTOMER_DTO = "{CALL select_all_customer_dto()}";
    private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM customer WHERE customer_id = ?;";
    private static final String UPDATE_CUSOMTER = "UPDATE customer\n" +
            "SET name             = ?,\n" +
            "    customer_code= ?,\n" +
            "    customer_type_id = ?,\n" +
            "    birthday         = ?,\n" +
            "    gender           = ?,\n" +
            "    id_card          = ?,\n" +
            "    phone            = ?,\n" +
            "    email            = ?,\n" +
            "    address          = ?\n" +
            "WHERE customer_id = ?;";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT customer_id, " +
            "customer_code, " +
            "customer_type_id, " +
            "`name`, " +
            "birthday, " +
            "gender, " +
            "id_card, " +
            "phone, " +
            "email, " +
            "address\n" +
            "FROM customer WHERE customer_id = ?;";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer (customer_code,\n" +
            "                      customer_type_id,\n" +
            "                      `name`,\n" +
            "                      birthday,\n" +
            "                      gender,\n" +
            "                      id_card,\n" +
            "                      phone,\n" +
            "                      email,\n" +
            "                      address)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_CUSTOMER_DTO =
            "SELECT cu.customer_id,\n" +
                    "       cu.name,\n" +
                    "       cu.birthday,\n" +
                    "       cu.gender,\n" +
                    "       cu.id_card,\n" +
                    "       cu.phone,\n" +
                    "       cu.email,\n" +
                    "       cu.address,\n" +
                    "       ct.value\n," +
                    "       cu.customer_code\n" +
                    "FROM customer AS cu\n" +
                    "         LEFT JOIN customer_type AS ct " +
                    "ON cu.customer_type_id = ct.customer_type_id;";

    private static String SELECT_CUSTOMER_DTO =
                    "SELECT cu.customer_id,\n" +
                            "       cu.name,\n" +
                            "       cu.birthday,\n" +
                            "       cu.gender,\n" +
                            "       cu.id_card,\n" +
                            "       cu.phone,\n" +
                            "       cu.email,\n" +
                            "       cu.address,\n" +
                            "       ct.value,\n" +
                            "       cu.customer_code\n" +
                            "FROM customer AS cu\n" +
                            "         LEFT JOIN customer_type AS ct\n" +
                            "        ON cu.customer_type_id = ct.customer_type_id";
    @Override
    public List<CustomerDTO> getList() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_CUSTOMER_DTO);
            ResultSet resultSet = preparedStatement.executeQuery();

            Integer id;
            String customerCode;
            String name;
            LocalDate birthday;
            String genderString;
            Integer gender;
            String idCard;
            String phone;
            String email;
            String address;
            String type;

            while (resultSet.next()) {
                id = resultSet.getInt("customer_id");
                name = resultSet.getString("name");
                birthday = resultSet.getDate("birthday").toLocalDate();
                genderString = resultSet.getString("gender");
                if (genderString == null) {
                    gender = null;
                } else {
                    gender = Integer.valueOf(genderString);
                }
                idCard = resultSet.getString("id_card");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                address = resultSet.getString("address");
                type = resultSet.getString("value");
                customerCode = resultSet.getString("customer_code");

                customerDTOList.add(new CustomerDTO(id, name, birthday, idCard, phone, email, address, customerCode, type, gender));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customerDTOList;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            String customerCode;
            String name;
            LocalDate birthday;
            String genderString;
            Integer gender;
            String idCard;
            String phone;
            String email;
            String address;
            Integer type;

            while (resultSet.next()) {
                name = resultSet.getString("name");
                birthday = resultSet.getDate("birthday").toLocalDate();
                genderString = resultSet.getString("gender");
                if (genderString == null) {
                    gender = null;
                } else {
                    gender = Integer.valueOf(genderString);
                }
                idCard = resultSet.getString("id_card");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                address = resultSet.getString("address");
                type = resultSet.getInt("customer_type_id");
                customerCode = resultSet.getString("customer_code");
                customer = new Customer(id, name, birthday, idCard, phone, email, address, customerCode, type, gender);
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
        return customer;
    }

    @Override
    public List<CustomerDTO> search(String fieldSearch, String searchKey) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        SELECT_CUSTOMER_DTO+= " WHERE "+fieldSearch+" LIKE ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_CUSTOMER_DTO);
            preparedStatement.setString(1,"%"+searchKey+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String customerCode;
            String name;
            LocalDate birthday;
            String genderString;
            Integer gender;
            String idCard;
            String phone;
            String email;
            String address;
            String type;

            while (resultSet.next()) {
                id = resultSet.getInt("customer_id");
                name = resultSet.getString("name");
                birthday = resultSet.getDate("birthday").toLocalDate();
                genderString = resultSet.getString("gender");
                if (genderString == null) {
                    gender = null;
                } else {
                    gender = Integer.valueOf(genderString);
                }
                idCard = resultSet.getString("id_card");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                address = resultSet.getString("address");
                type = resultSet.getString("value");
                customerCode = resultSet.getString("customer_code");

                customerDTOList.add(new CustomerDTO(id, name, birthday, idCard, phone, email, address, customerCode, type, gender));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customerDTOList;
    }
    @Override
    public void save(Customer customer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setString(1, customer.getCustomerCode());
            preparedStatement.setInt(2, customer.getCustomerType());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setDate(4, Date.valueOf(customer.getBirthday()));
            preparedStatement.setInt(5, customer.getGender());
            preparedStatement.setString(6, customer.getIdCard());
            preparedStatement.setString(7, customer.getPhone());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public boolean update(Customer customer) {
        boolean rowUpdated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(UPDATE_CUSOMTER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getCustomerCode());
            preparedStatement.setInt(3, customer.getCustomerType());
            preparedStatement.setDate(4, Date.valueOf(customer.getBirthday()));
            preparedStatement.setInt(5, customer.getGender());
            preparedStatement.setString(6, customer.getIdCard());
            preparedStatement.setString(7, customer.getPhone());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getAddress());

            preparedStatement.setInt(10, customer.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(Integer id) {
        boolean rowUpdated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(DELETE_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rowUpdated;
    }
}
