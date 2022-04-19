package repository.impl;

import model.User;
import repository.BaseRepository;
import repository.IUserRepository;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRepositoryImpl implements IUserRepository {
    private BaseRepository baseRepository = new BaseRepository();

//    private static Map<Integer, User> userMap = new HashMap<>();

    private static final String INSERT_USERS_SQL = "INSERT INTO users (`name`, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id,`name`,email,country FROM users WHERE id =?;";
    private static final String SELECT_ALL_USERS = "SELECT id,`name`,email,country FROM users;";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET `name` = ?,email= ?, country =? WHERE id = ?;";
    private static final String SELECT_USERS_BY_COUNTRY = "SELECT id,`name`,email,country FROM users WHERE LOWER(country) LIKE ?;";

//    static {
//        userMap = new TreeMap<>();
//        userMap.put(1, new User(1, "Omo", "2021", "Unilever"));
//        userMap.put(2, new User(2, "Clearmen",  "Clear for men", "Clear"));
//        userMap.put(3, new User(3, "HP Envy", "Envy 15", "HP"));
//        userMap.put(4, new User(4, "iMac M1", "RAM 8Gb", "Apple"));
//        userMap.put(5, new User(5, "Macbook Air M1", "2021 RAM 16Gb", "Apple"));
//        userMap.put(6, new User(6, "HP Probook",  "2019 RAM 8Gb", "HP"));
//    }

    @Override
    public List<User> getList() {
        List<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnectionJavaToDB().prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();

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
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void save(User user) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnectionJavaToDB().prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(User user) {
        boolean rowUpdated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnectionJavaToDB().prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowUpdated;
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnectionJavaToDB().prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            String name;
            String email;
            String country;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                country = resultSet.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public boolean remove(Integer id) {
        boolean rowDeleted = false;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnectionJavaToDB().prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1, id);
            //preparedStatement.executeUpdate();
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rowDeleted;
//        userMap.remove(id);
    }

    @Override
    public List<User> search(String keyWord) {

        List<User> resultSearch = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnectionJavaToDB().prepareStatement(SELECT_USERS_BY_COUNTRY);
            preparedStatement.setString(1, "%" + keyWord.toLowerCase() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            Integer id;
            String name;
            String email;
            String country;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                country = resultSet.getString("country");
                resultSearch.add(new User(id, name, email, country));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
//            if (entry.getValue().getName().toLowerCase().contains(keyWord)) {
//                resultSearch.add(entry.getValue());
//            }
//        }

        return resultSearch;
    }

    @Override
    public void insertUserStore(User user) {
        String query = "{CALL insert_user(?,?,?)}";
        CallableStatement callableStatement = null;
        try {
            callableStatement = this.baseRepository.getConnectionJavaToDB().prepareCall(query);
            callableStatement.setString(1, user.getName());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getCountry());
            callableStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert callableStatement != null;
                callableStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        String query = "{CALL get_user_by_id(?)}";
        CallableStatement callableStatement = null;

        try {
            callableStatement = this.baseRepository.getConnectionJavaToDB().prepareCall(query);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            String name;
            String email;
            String country;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                country = resultSet.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert callableStatement != null;
                callableStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return user;
    }
}
