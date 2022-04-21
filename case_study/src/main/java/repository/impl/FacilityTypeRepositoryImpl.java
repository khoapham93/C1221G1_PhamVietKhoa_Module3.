package repository.impl;

import models.FacilityType;
import repository.BaseRepository;
import repository.IFacilityTypeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityTypeRepositoryImpl implements IFacilityTypeRepository {
    private BaseRepository baseRepository = new BaseRepository();

    private static final String SELECT_ALL_FACILITYTYPE = "SELECT facility_type_id, `value` FROM facility_type";

    @Override
    public List<FacilityType> getList() {
        List<FacilityType> facilityTypes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_FACILITYTYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String facilityType;
            while (resultSet.next()) {
                id = resultSet.getInt("facility_type_id");
                facilityType = resultSet.getString("value");
                facilityTypes.add(new FacilityType(id, facilityType));
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
        return facilityTypes;
    }
}
