package repository.impl;

import models.RentType;
import repository.BaseRepository;
import repository.IRentTypeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentTypeRepositoryImpl implements IRentTypeRepository {
    private BaseRepository baseRepository = new BaseRepository();

    private static final String SELECT_ALL_RENTTYPE = "SELECT rent_type_id, rent_type_name FROM rent_type";

    @Override
    public List<RentType> getList() {
        List<RentType> rentTypes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_RENTTYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String rentType;
            while (resultSet.next()) {
                id = resultSet.getInt("rent_type_id");
                rentType = resultSet.getString("rent_type_name");
                rentTypes.add(new RentType(id, rentType));
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
        return rentTypes;
    }
}
