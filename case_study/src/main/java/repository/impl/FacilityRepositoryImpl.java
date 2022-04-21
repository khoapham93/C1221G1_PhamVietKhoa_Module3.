package repository.impl;

import dto.EmployeeDTO;
import dto.FacilityDTO;
import models.Facility;
import repository.BaseRepository;
import repository.IFacilityRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityRepositoryImpl implements IFacilityRepository {
    private BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ALL_FACILITY_DTO = "SELECT fa.facility_id,\n" +
            "       fa.facility_code,\n" +
            "       fa.facility_name,\n" +
            "       fa.floor_square,\n" +
            "       fa.rental_fee,\n" +
            "       fa.maximum_people,\n" +
            "       fa.room_standard,\n" +
            "       fa.description,\n" +
            "       fa.pool_square,\n" +
            "       fa.floor,\n" +
            "       ft.value,\n" +
            "       rt.rent_type_name\n" +
            "FROM facility fa\n" +
            "         LEFT JOIN facility_type ft ON fa.facility_type_id = ft.facility_type_id\n" +
            "         LEFT JOIN rent_type rt ON fa.rent_type_id = rt.rent_type_id";

    private static final String INSERT_FACILITY = "INSERT INTO facility " +
            "(                     facility_code,\n" +
            "                      facility_name,\n" +
            "                      floor_square,\n" +
            "                      rental_fee,\n" +
            "                      maximum_people,\n" +
            "                      facility_type_id,\n" +
            "                      rent_type_id,\n" +
            "                      room_standard,\n" +
            "                      description,\n" +
            "                      pool_square,\n" +
            "                      floor)\n" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public List<FacilityDTO> getList() {
        List<FacilityDTO> facilityDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_FACILITY_DTO);

            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String facilityType;
            String rentType;
            String code;
            String name;
            Double floorSquare;
            Double rentalFee;
            Integer maximumPeople;
            String roomStandard;
            String description;
            Double poolSquare;
            Integer numberFloor;
            while (resultSet.next()) {
                id = resultSet.getInt("facility_id");
                facilityType = resultSet.getString("value");
                rentType = resultSet.getString("rent_type_name");
                code = resultSet.getString("facility_code");
                name = resultSet.getString("facility_name");
                floorSquare = resultSet.getDouble("floor_square");
                rentalFee = resultSet.getDouble("rental_fee");
                maximumPeople = resultSet.getInt("maximum_people");
                roomStandard = resultSet.getString("room_standard");
                description = resultSet.getString("description");
                poolSquare = resultSet.getDouble("pool_square");
                numberFloor = resultSet.getInt("floor");

                facilityDTOList.add(new FacilityDTO(id,
                        facilityType,
                        rentType,
                        code,
                        name,
                        floorSquare,
                        rentalFee,
                        maximumPeople,
                        roomStandard,
                        description,
                        poolSquare, numberFloor));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return facilityDTOList;
    }

    @Override
    public Facility findById(Integer id) {
        return null;
    }

    @Override
    public List<FacilityDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return null;
    }

    @Override
    public void save(Facility facility) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(INSERT_FACILITY);
            preparedStatement.setString(1, facility.getCode());
            preparedStatement.setString(2, facility.getName());
            preparedStatement.setDouble(3, facility.getFloorSquare());
            preparedStatement.setDouble(4, facility.getRentalFee());
            preparedStatement.setInt(5, facility.getMaximumPeople());
            preparedStatement.setInt(6, facility.getFacilityTypeId());
            preparedStatement.setInt(7, facility.getRentTypeId());
            preparedStatement.setString(8, facility.getRoomStandard());
            preparedStatement.setString(9, facility.getDescription());
            preparedStatement.setDouble(10, facility.getPoolSquare());
            preparedStatement.setInt(11, facility.getNumberFloor());
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
    public boolean update(Facility facility) {
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }
}
