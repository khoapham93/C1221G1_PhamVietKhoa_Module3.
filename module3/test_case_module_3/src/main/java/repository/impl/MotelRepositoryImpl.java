package repository.impl;

import dto.MotelDTO;
import model.Motel;
import repository.BaseRepository;
import repository.IMotelRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MotelRepositoryImpl implements IMotelRepository {
    private BaseRepository baseRepository = new BaseRepository();

    private static final String SELECT_ALL_MOTEL_DTO = "SELECT mt.id,\n" +
            "      mt.`name`,\n" +
            "      mt.phone,\n" +
            "      mt.date_start,\n" +
            "      rt.rent_type_name,\n" +
            "      mt.description,\n" +
            "      mt.code\n" +
            "FROM motel mt\n" +
            "         INNER JOIN rent_type rt ON mt.rent_type_id = rt.rent_type_id;";

    private static final String INSERT_MOTEL = "INSERT INTO motel ( `name`, phone, date_start, rent_type_id, description) VALUES (?,?,?,?,?);";
    private static final String DELETE_MOTEL = "DELETE FROM motel WHERE id = ?;";
    private static final String DELETE_MOTEL_MULTIPLE = "DELETE FROM motel WHERE id IN";

    private static final String SEARCH_MOTEL_DTO = "SELECT mt.id,\n" +
            "       mt.`name`,\n" +
            "       mt.phone,\n" +
            "       mt.date_start,\n" +
            "       rt.rent_type_name,\n" +
            "       mt.description,\n" +
            "       mt.code\n" +
            "FROM motel mt\n" +
            "         INNER JOIN rent_type rt ON mt.rent_type_id = rt.rent_type_id\n" +
            "WHERE `name` LIKE ?\n" +
            "  AND phone LIKE ?;";

    @Override
    public List<MotelDTO> getList() {
        List<MotelDTO> motelDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_MOTEL_DTO);
            ResultSet resultSet = preparedStatement.executeQuery();

            Integer id;
            String code;
            String name;
            String phone;
            LocalDate startDate;
            String rentType;
            String description;

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                code = resultSet.getString("code");
                phone = resultSet.getString("phone");
                startDate = resultSet.getDate("date_start").toLocalDate();
                rentType = resultSet.getString("rent_type_name");
                description = resultSet.getString("description");

                motelDTOList.add(new MotelDTO(id, code, name, phone, startDate, rentType, description));

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

        return motelDTOList;
    }

    @Override
    public void save(Motel motel) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(INSERT_MOTEL);
            preparedStatement.setString(1, motel.getName());
            preparedStatement.setString(2, motel.getPhone());
            preparedStatement.setDate(3, Date.valueOf(motel.getStartDate()));
            preparedStatement.setInt(4, motel.getRentTypeId());
            preparedStatement.setString(5, motel.getDescription());
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
    public boolean remove(Integer id) {
        boolean rowUpdated = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(DELETE_MOTEL);
            preparedStatement.setInt(1, id);

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
    public List<MotelDTO> search(String code, String name, String phone) {
        List<MotelDTO> motelDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SEARCH_MOTEL_DTO);
//            preparedStatement.setString(1,code);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + phone + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            Integer id;
            String codeSearch;
            String nameSearch;
            String phoneSearch;
            LocalDate startDate;
            String rentType;
            String description;

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                nameSearch = resultSet.getString("name");
                codeSearch = resultSet.getString("code");
                phoneSearch = resultSet.getString("phone");
                startDate = resultSet.getDate("date_start").toLocalDate();
                rentType = resultSet.getString("rent_type_name");
                description = resultSet.getString("description");

                motelDTOList.add(new MotelDTO(id, codeSearch, nameSearch, phoneSearch, startDate, rentType, description));

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

        return motelDTOList;
    }

    @Override
    public boolean removeMultiple(int[] idList) {
        boolean rowUpdated = false;
        StringBuilder query = new StringBuilder(DELETE_MOTEL_MULTIPLE + "(?");
        for (int  i=1; i<idList.length;i++){
            query.append(",?");
        }
        query.append(")");
        String querySql = query.toString();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(querySql);
            for (int  i=0; i<idList.length;i++){
                preparedStatement.setInt((i+1), idList[i]);
            }
            System.out.println(preparedStatement);
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
}
